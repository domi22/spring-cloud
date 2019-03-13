package spring.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import spring.cloud.common.annotation.EnableUserInfoTransmitter;


@SpringBootApplication(scanBasePackages={"spring.cloud.*"})
@EnableFeignClients
@EnableUserInfoTransmitter
public class SpringCloudFeignApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFeignApp.class,args);
    }
}
