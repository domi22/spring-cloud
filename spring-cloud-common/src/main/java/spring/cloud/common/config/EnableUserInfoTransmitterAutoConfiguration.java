package spring.cloud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.cloud.common.interceptor.TransmitUserInfoFeighClientInterceptor;
import spring.cloud.common.interceptor.TransmitUserInfoFilter;

@Configuration
public class EnableUserInfoTransmitterAutoConfiguration {

    public EnableUserInfoTransmitterAutoConfiguration() {
    }

    @Bean
    public TransmitUserInfoFeighClientInterceptor transmitUserInfo2FeighHttpHeader(){
        System.out.println("-------TransmitUserInfoFeighClientInterceptor-------");
        return new TransmitUserInfoFeighClientInterceptor();
    }

    @Bean
    public TransmitUserInfoFilter transmitUserInfoFromHttpHeader(){
        System.out.println("-------TransmitUserInfoFilter-------");
        return new TransmitUserInfoFilter();
    }
}
