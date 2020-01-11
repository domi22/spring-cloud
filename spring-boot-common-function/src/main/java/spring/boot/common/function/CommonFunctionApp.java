package spring.boot.common.function;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"spring.boot.common.function.mapper"})
public class CommonFunctionApp {

    public static void main(String[] args) {
        SpringApplication.run(CommonFunctionApp.class);
    }
}
