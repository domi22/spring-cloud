package spring.cloud.common.annotation;

import org.springframework.context.annotation.Import;
import spring.cloud.common.config.EnableUserInfoTransmitterAutoConfiguration;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableUserInfoTransmitterAutoConfiguration.class})
public @interface EnableUserInfoTransmitter {
}
