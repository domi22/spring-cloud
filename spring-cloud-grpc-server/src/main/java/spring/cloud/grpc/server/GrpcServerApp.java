package spring.cloud.grpc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GrpcServerApp {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApp.class,args);
    }
}
