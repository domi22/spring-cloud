package spring.boot.common.function.multi.datasource.config;

import org.springframework.context.annotation.Bean;
import spring.boot.common.function.multi.datasource.DynamicDataSourceRegister;

public class MultiDataSourceConfig {

    @Bean
    public DynamicDataSourceRegister getDynamicDataSourceRegister() {
        return new DynamicDataSourceRegister();
    }

}
