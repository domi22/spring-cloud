package spring.cloud.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.cloud.common.interceptor.RestTemplateUserContextInterceptor;
import spring.cloud.common.interceptor.UserContextInterceptor;

/**
 * 详见 https://www.cnblogs.com/duanxz/p/4875153.html
 */
@Configuration
@EnableWebMvc
public class CommonConfiguration implements WebMvcConfigurer {

    /**
     * 请求拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("--------------UserContextInterceptor-------------");
        registry.addInterceptor(new UserContextInterceptor());
        //可以指定拦截器的路劲
        //registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
        //registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }

    /***
     * RestTemplate 拦截器，在发送请求前设置鉴权的用户上下文信息
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        System.out.println("--------------resttemplate-------------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RestTemplateUserContextInterceptor());
        return restTemplate;
    }

}
