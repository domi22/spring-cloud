package spring.cloud.common.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import spring.cloud.common.util.ResultBuilder;
import javax.annotation.Resource;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionConfig {
    @Resource
    private ServerProperties serverProperties;
    @Bean
    public ResultBuilder resultBuilder(){
        return  new ResultBuilder(serverProperties);
    }
}
