package spring.boot.common.function.multi.datasource.config;

import org.springframework.context.annotation.Bean;
import spring.boot.common.function.multi.datasource.DynamicDataSourceRegister;

/**
 * 不需要在这里配置DynamicDataSourceRegister，
 * 可以用注解EnableMultiDataSource灵活控制
 */
public class MultiDataSourceConfig {

    @Bean
    public DynamicDataSourceRegister getDynamicDataSourceRegister() {
        return new DynamicDataSourceRegister();
    }

}
