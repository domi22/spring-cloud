package spring.cloud.gateway.auth;

import java.net.URI;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 参数参考 https://blog.csdn.net/tianyaleixiaowu/article/details/83375246
 * response参考 https://bbs.csdn.net/topics/392412604?list=11074255
 */
@Component
public class AuthFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders header = request.getHeaders();
        HttpMethod method = request.getMethod();
        String token = header.getFirst(JwtUtil.HEADER_AUTH);
        String userId = header.getFirst(JwtUtil.HEADER_USERID);
        PathContainer pathContainer = request.getPath().pathWithinApplication();
        String path = pathContainer.value();

        //2- 处理登录请求
        if (StringUtils.isBlank(token)) {
            //是登录接口则放行，否则返回异常
            if (path.contains(JwtUtil.LOGIN_URL) && HttpMethod.POST.equals(method)) {
                throw new PermissionException("please login");
            }
            return chain.filter(exchange);
        }

        //3- 处理刷新token请求
        if (path.indexOf("refresh") >= 0) {
            //放行去掉刷新接口（在刷新前校验userId和token是否匹配）
            return chain.filter(exchange);
        }

        //4- 处理刷新token请求
        if (path.contains(JwtUtil.LOGOUT_URL) && HttpMethod.DELETE.equals(method)) {
            //放行去掉登出接口（在刷新前校验userId和token是否匹配）
            return chain.filter(exchange);
        }

        //5- 携带token请求其他业务接口
        Map<String, String> validateResultMap = JwtUtil.validateTokenAndUser(token, userId);
        if (validateResultMap == null || validateResultMap.isEmpty()) {
            throw new PermissionException("token 已经失效");
        }
        // TODO 将用户信息存放在请求header中传递给下游业务
        Route gatewayUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        URI uri = gatewayUrl.getUri();
        //表示下游请求对应的服务名如 SPRING-CLOUD-SERVICE  SPRING-CLOUD-GATEWAY
        String serviceName = uri.getHost();

        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("x-user-id", validateResultMap.get("userid"));
        mutate.header("x-user-name", validateResultMap.get("user"));
        mutate.header("x-user-serviceName", serviceName);
        ServerHttpRequest buildReuqest = mutate.build();

        //todo 如果响应中需要放数据，也可以放在response的header中
        //ServerHttpResponse response = exchange.getResponse();
        //response.getHeaders().add("new_token","token_value");
        return chain.filter(exchange.mutate().request(buildReuqest).build());
    }



}
