package spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class SpringCloudZuulApp extends SpringBootServletInitializer {
    /*
    @EnableZuulServer注解是高配版本
    @EnableZuulProxy注解是低配版本
    如果不想让高版本多出的过滤器生效，可用低配版本注解
    低配版本注解更适合自定义过滤器，因为经过的过滤器少，性能会比较高
    详见：https://blog.csdn.net/chengqiuming/article/details/81267426
    */
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulApp.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringCloudZuulApp.class);
    }
}
