package spring.cloud.grpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GrpcClientApp {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApp.class, args);
    }
}
