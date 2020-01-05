package spring.boot.common.function.rest.template.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import spring.boot.common.function.rest.template.aop.HttpHeaderInterceptor;
import spring.boot.common.function.rest.template.aop.TransmitUserInfoFilter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;


/**
 * 如何实现resttemplate的调用时，请求头透传
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    HttpHeaderInterceptor httpHeaderInterceptor;

    @Value("${remote.maxTotalConnect:0}")
    private int maxTotalConnect; //连接池的最大连接数默认为0

    @Value("${remote.maxConnectPerRoute:200}")
    private int maxConnectPerRoute; //单个主机的最大连接数

    @Value("${remote.connectTimeout:2000}")
    private int connectTimeout; //连接超时默认2s

    @Value("${remote.readTimeout:30000}")
    private int readTimeout; //读取超时默认30s

    //集成注册中心之后可以使用如下方式获取url:
    //ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
    //String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()+"/msg");

    //集成ribbon之后可以使用@LoadBalanced实现负载均衡
    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.createFactory());
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

        //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        //加入FastJson转换器
        converterList.add(new FastJsonHttpMessageConverter4());

        //添加拦截器，进行header透传 ClientHttpRequestInterceptor
        restTemplate.setInterceptors(Collections.singletonList(httpHeaderInterceptor));
        //或者restTemplate.getInterceptors().add(httpHeaderInterceptor);
        return restTemplate;
    }


    //创建HTTP客户端工厂
    private ClientHttpRequestFactory createFactory() {
//        if (this.maxTotalConnect <= 0) {
//            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//            factory.setConnectTimeout(this.connectTimeout);
//            factory.setReadTimeout(this.readTimeout);
//            return factory;
//        }
//        HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(this.maxTotalConnect)
//                .setMaxConnPerRoute(this.maxConnectPerRoute).build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
//                httpClient);
//        factory.setConnectTimeout(this.connectTimeout);
//        factory.setReadTimeout(this.readTimeout);
        return null;
    }


    //https://blog.csdn.net/ygfamily73/article/details/85933908
    @Bean
    public AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }

    @Bean
    public TransmitUserInfoFilter getFilter() {
        return new TransmitUserInfoFilter();
    }


}
