package spring.cloud.common.multi.datasource.config;

import org.springframework.context.annotation.Bean;
import spring.cloud.common.multi.datasource.DynamicDataSourceRegister;

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
