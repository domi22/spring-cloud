package spring.cloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import spring.cloud.common.annotation.EnableUserInfoTransmitter;


//@MapperScan({"spring.cloud.service.mapper","spring.cloud.service.dao"})
@MapperScan("spring.cloud.service.dao")
@EnableEurekaClient
//@EnableUserInfoTransmitter
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
