package spring.cloud.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudCommonApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCommonApp.class);
    }
}
