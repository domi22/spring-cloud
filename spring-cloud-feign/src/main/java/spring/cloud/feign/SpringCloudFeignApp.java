package spring.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages={"spring.cloud.*"})
@EnableFeignClients
public class SpringCloudFeignApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFeignApp.class,args);
    }
}
