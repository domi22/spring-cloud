package spring.cloud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cloud.common.interceptor.TransmitUserInfoFeighClientInterceptor;
import spring.cloud.common.filter.TransmitUserInfoFilter;

@Configuration
public class EnableUserInfoTransmitterAutoConfiguration {

    public EnableUserInfoTransmitterAutoConfiguration() {
    }

    @Bean
    public TransmitUserInfoFeighClientInterceptor transmitUserInfo2FeighHttpHeader(){
        return new TransmitUserInfoFeighClientInterceptor();
    }

    @Bean
    public TransmitUserInfoFilter transmitUserInfoFromHttpHeader(){
        return new TransmitUserInfoFilter();
    }
}
