package spring.cloud.common.distributed.lock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ZkLockConfig {

    @Value("${zklock.rootPath}")
    private String rootPath;
    @Value("${zklock.url}")
    private String url;

    @Bean
    @Scope("prototype")
    public ZKlock getZKlock(){
        return new ZKlock(url,rootPath);
    }
}
