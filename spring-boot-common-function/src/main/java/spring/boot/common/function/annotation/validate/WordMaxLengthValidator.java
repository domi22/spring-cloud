package spring.boot.common.function.annotation.validate;

import org.apache.commons.lang.StringUtils;
import spring.boot.common.function.annotation.WordMaxLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;

public class WordMaxLengthValidator implements ConstraintValidator<WordMaxLength,String> {

    int length;


    @Override
    public void initialize(WordMaxLength wordMaxLength) {
        this.length = wordMaxLength.length();
    }

    @Override
    public boolean isValid(String word, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(word)) {
            return Boolean.TRUE;
        }
        try {
            String wordStr = new String(word.getBytes("gb2312"),"iso-8859-1");
            if (wordStr.length() > this.length) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addConstraintViolation();
                return Boolean.FALSE;
            }
        } catch (UnsupportedEncodingException e) {
           throw new RuntimeException(e);
        }

        return Boolean.TRUE;
    }
}
