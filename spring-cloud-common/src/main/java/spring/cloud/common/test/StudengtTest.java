package spring.cloud.common.test;

import spring.cloud.common.vo.Student;
import spring.cloud.common.vo.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudengtTest {



    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Student xiaoming = getBean();
        List<String> validate = validate(xiaoming,factory);
        validate.forEach(row -> {
            System.out.println(row);
        });
    }


    private static Student getBean() {
        Student bean = new Student();
        bean.setName("zhangsan");
        bean.setAddress("北京");
        bean.setBirthday(new Date());
        bean.setFriendName(null);
        bean.setWeight(new BigDecimal(30));
        bean.setEmail("xiaogangfan163.com");
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("suerName");
        list.add(user);
        bean.setUserList(list);
        return bean;
    }

    public static <T> List<String> validate(T t,ValidatorFactory factory) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }
}
