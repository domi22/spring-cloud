package spring.cloud.common.attachment;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileValidator.class)
@Target({ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsFileWanner {

    /**
     * 上传文件的类型
     */
    String[] fileTypes();

    /**
     * 上传的文件大小（0表示不限制）
     */
    long fileSize();

    /**
     * 校验提示信息
     */
    String message() default "不支持上传该类型的文件";

    /**
     * 校验分组
     */
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
