package spring.boot.common.function.multi.datasource;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DynamicDataSourceRegister.class})
public @interface EnableMultiDataSource {
}
