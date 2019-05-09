package spring.cloud.common.header.transmit;

import org.springframework.context.annotation.Bean;
import spring.cloud.common.header.transmit.aop.TransmitUserInfoFeighClientInterceptor;
import spring.cloud.common.header.transmit.aop.TransmitUserInfoFilter;

//@Configuration 在业务端通过注解的方式加载
public class EnableUserInfoTransmitterAutoConfiguration {

    @Bean
    public TransmitUserInfoFeighClientInterceptor transmitUserInfo2FeighHttpHeader(){
        System.out.println("-----TransmitUserInfoFeighClientInterceptor");
        return new TransmitUserInfoFeighClientInterceptor();
    }

    @Bean
    public TransmitUserInfoFilter transmitUserInfoFromHttpHeader(){
        System.out.println("-----TransmitUserInfoFilter");
        return new TransmitUserInfoFilter();
    }
}
