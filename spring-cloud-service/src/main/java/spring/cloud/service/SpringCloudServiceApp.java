package spring.cloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"spring.cloud.*"})
public class SpringCloudServiceApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudServiceApp.class,args);
    }

    /**
     * 加载到外部容器启动
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringCloudServiceApp.class);
    }

}
