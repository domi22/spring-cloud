package spring.boot.common.function.multi.datasource.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import spring.boot.common.function.multi.datasource.DataSource;
import spring.boot.common.function.multi.datasource.config.DynamicDataSourceContextHolder;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(2)
public class DynamicDataSourceAspect {

    @Around("@annotation(spring.boot.common.function.multi.datasource.DataSource)")
    public Object changeDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
        DataSource dataSource = method.getAnnotation(DataSource.class);
        String sourceEL = dataSource.value();
        if (StringUtils.isBlank(sourceEL)) {
            return joinPoint.proceed();
        }
        String realSource = getSource(joinPoint, method, sourceEL);
        try {
            DynamicDataSourceContextHolder.setDataSourceRouterKey(realSource);
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new Throwable(throwable);
        } finally {
            DynamicDataSourceContextHolder.removeDataSourceRouterKey();
        }
    }


    private String getSource(ProceedingJoinPoint joinPoint, Method method, String sourceEL) {
        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(sourceEL);
        //设置解析上下文
        EvaluationContext context = new StandardEvaluationContext();
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i].toString());
        }
        //解析
        return expression.getValue(context).toString();
    }
}
