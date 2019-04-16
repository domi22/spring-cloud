package spring.boot.common.function.annotation.func;

import org.springframework.stereotype.Component;
import spring.boot.common.function.annotation.dto.AnnotationClass;

import java.util.List;

@Component("testFunc")
public class BusinessFuncImpl implements IBusinessFunc {

    @Override
    public void executeFunc(BusinessMethodParam businessMethodParam) throws Exception {
        Object[] args = businessMethodParam.getArgs();
        List<AnnotationClass> result = (List<AnnotationClass>)businessMethodParam.getResult();
        result.add(new AnnotationClass());
    }
}
