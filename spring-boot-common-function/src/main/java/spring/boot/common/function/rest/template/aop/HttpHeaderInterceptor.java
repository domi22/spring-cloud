package spring.boot.common.function.rest.template.aop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import spring.boot.common.function.rest.template.UserInfoContext;
import java.io.IOException;

@Component
public class HttpHeaderInterceptor implements ClientHttpRequestInterceptor   {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(UserInfoContext.USERINFO_KEY,UserInfoContext.getUser());
        return execution.execute(request, body);
    }
}
