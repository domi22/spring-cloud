package spring.boot.common.function.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注业务功能注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessFuc {

    /**
     * 校验功能
     * beanNames,point(before | overwrite | after)
     */
    String[] funcNames();

}
