package spring.boot.common.function.annotation;

import spring.boot.common.function.annotation.validate.WordMaxLengthValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 内容长度校验，中文占2个长度，英文占1个长度
 */
@Constraint(validatedBy = WordMaxLengthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WordMaxLength {

    /**
     * 最大长度
     */
    int length();

    String message() default "WordMaxLength.message";

    /**
     * 支持分组校验
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 有效负荷
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
