package spring.boot.common.function.annotation;

import spring.boot.common.function.annotation.validate.TimeBetweenMaxValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 最大相差时间校验
 */
@Constraint(validatedBy = TimeBetweenMaxValidator.class)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeBetweenMax {
    /**
     * 开始时间属性名
     */
    String startTimeFied();

    /**
     * 截止时间属性名
     */
    String endTimeFied();

    /**
     * 最大相差时间
     */
    int length();

    /**
     * 日期单位，同Calender.YEAR, Calender.MONTH
     */
    int unit();

    String message() default "TimeBetweenMax.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        TimeBetweenMax[] value();
    }
}
