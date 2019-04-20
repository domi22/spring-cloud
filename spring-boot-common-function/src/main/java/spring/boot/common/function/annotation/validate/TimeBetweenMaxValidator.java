package spring.boot.common.function.annotation.validate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanWrapperImpl;
import spring.boot.common.function.annotation.TimeBetweenMax;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期间隔校验器
 */
public class TimeBetweenMaxValidator implements ConstraintValidator<TimeBetweenMax,Object> {

    /**
     * 开始时间属性名
     */
    private String startTimeField;

    /**
     * 截止时间属性名
     */
    private String endTimeField;

    /**
     * 时间间隔最大值
     */
    private int length;

    /**
     * 时间单位
     */
    private int unit;


    @Override
    public void initialize(TimeBetweenMax timeBetweenMax) {
        this.startTimeField = timeBetweenMax.startTimeFied();
        this.endTimeField = timeBetweenMax.endTimeFied();
        this.length = timeBetweenMax.length();
        this.unit = timeBetweenMax.unit();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(object);
        String startTimeStr = (String)beanWrapper.getPropertyValue(startTimeField);
        String endTimeStr = (String)beanWrapper.getPropertyValue(endTimeField);
        if (StringUtils.isBlank(startTimeStr) || StringUtils.isBlank(endTimeStr) ) {
           return Boolean.TRUE;
        }
        try {
            Date startTime = DateUtils.parseDate(startTimeStr,new String[]{"yyyy/MM/dd","yyyy-MM-dd"});
            Date endTime = DateUtils.parseDate(endTimeStr,new String[]{"yyyy/MM/dd","yyyy-MM-dd"});
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);
            startCalendar.add(this.unit,this.length);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endTime);
            if (startCalendar.compareTo(endCalendar) < 0) {
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode(startTimeField)
                        .addConstraintViolation();
                return Boolean.FALSE;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }
}
