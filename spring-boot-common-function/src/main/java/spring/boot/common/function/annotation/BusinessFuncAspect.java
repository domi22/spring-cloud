package spring.boot.common.function.annotation;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import spring.boot.common.function.annotation.func.BusinessMethodParam;
import spring.boot.common.function.annotation.func.IBusinessFunc;
import spring.cloud.common.util.SpringContextUtil;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务功能切面处理
 */
@Component
@Aspect
@Order(1)
public class BusinessFuncAspect {

    /**
     * 前置扩展
     */
    public static final String BEFORE_POINT = "before";
    /**
     * 后置扩展
     */
    public static final String AFTER_POINT = "after";
    /**
     * 覆盖扩展
     */
    public static final String OVERWRITE_POINT = "overwrite";
    /**
     * funcName和切面的分隔符
     */
    public static final String FUNC_SEPARATOR = "|";
    /**
     * JS引擎
     */
    public static final String NASHORN_ENGINE = "nashorn";
    /**
     * 业务功能类型 -JAVA
     */
    public static final String JAVA_FUNC = "JAVA";
    /**
     * 业务功能类型 -JS
     */
    public static final String JS_FUNC = "JS";
    /**
     * 业务功能类型 -groovy
     */
    public static final String GROOVY_FUNC = "GROOVY";

    /**
     * 拦截@BusinessFuc注解，执行业务功能
     * @param joinPoint
     * @return
     * @throws Exception
     */
    @Around(value="@annotation(spring.boot.common.function.annotation.BusinessFuc)")
    @Transactional(rollbackFor = Throwable.class)
    public Object businessFunc(ProceedingJoinPoint joinPoint) throws Throwable{
       //获取注解中的Func分组
        Map<String,List<String>> funcGroups =  getAnnotationFuncGroup(joinPoint);
        //触发业务功能
        return executeBusinessFunc(joinPoint,funcGroups);
    }

    /**
     * 执行业务功能
     * @param joinPoint  切面
     * @param funcGroups 功能分组
     * @return
     * @throws Throwable
     */
    private Object executeBusinessFunc(ProceedingJoinPoint joinPoint,Map<String,List<String>> funcGroups) throws Throwable{
        //新增业务方法描述
        BusinessMethodParam businessMethodParam = new BusinessMethodParam(joinPoint.getArgs());
        //before处理
        List<String> beforeFuncs = funcGroups.get(BEFORE_POINT);
        if(!CollectionUtils.isEmpty(beforeFuncs)){
            executeFunc(beforeFuncs,businessMethodParam);
        }
        //overwrite处理
        List<String> overwriteFuncs = funcGroups.get(OVERWRITE_POINT);
        if(!CollectionUtils.isEmpty(overwriteFuncs)){
            //如果有多个功能，只执行最后一个
            int overSize = overwriteFuncs.size();
            executeFunc(overwriteFuncs.subList(overSize - 1,overSize),businessMethodParam);
        }else{
            //没有配置overwrite功能，则执行原业务方法
            Object returnObj = joinPoint.proceed();
            businessMethodParam.setResult(returnObj);
        }
        //after处理
        List<String> afterFuncs = funcGroups.get(AFTER_POINT);
        if(!CollectionUtils.isEmpty(afterFuncs)){
            executeFunc(afterFuncs,businessMethodParam);
        }
       return businessMethodParam.getResult();
    }

    /**
     * 触发功能
     * @param funcs 功能beanName集合
     * @param businessMethodParam 业务方法描述
     */
    private void executeFunc(List<String> funcs,BusinessMethodParam businessMethodParam) throws Throwable{
        for (String funcName : funcs){
            executeFunc(funcName,JAVA_FUNC,businessMethodParam);
        }
    }

    /**
     * 触发功能
     * @param func 业务功能
     * @param func 业务功能类型：JAVA/JS
     * @param businessMethodParam 业务方法描述
     */
    private void executeFunc(String func,String funcType,BusinessMethodParam businessMethodParam) throws Throwable{
        if(JAVA_FUNC.equalsIgnoreCase(funcType)){
            //JAVA功能
            IBusinessFunc businessFunc = (IBusinessFunc) SpringContextUtil.getBean(func);
            businessFunc.executeFunc(businessMethodParam);
        }else if (JS_FUNC.equalsIgnoreCase(funcType)){
              //JS功能
            ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(NASHORN_ENGINE);
            scriptEngine.eval(func);
            Invocable invocable = (Invocable)scriptEngine;
            invocable.invokeFunction("executeFunc",businessMethodParam);
        }else if (GROOVY_FUNC.equalsIgnoreCase(funcType)){
            //执行groovy功能
        }
    }

    /**
     * 读取注解上的func配置
     * @param joinPoint 切面
     * @return func分组，按照before|after|overwrite分组
     */
    private Map<String,List<String>> getAnnotationFuncGroup(ProceedingJoinPoint joinPoint)throws Exception{
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        BusinessFuc businessFuc = methodSignature.getMethod().getAnnotation(BusinessFuc.class);
        List<String> beforeFuncNames = new ArrayList<>();
        List<String> overwriteFuncNames = new ArrayList<>();
        List<String> afterFuncNames = new ArrayList<>();
        for (String func : businessFuc.funcNames()){
            String point = StringUtils.substringAfter(func,FUNC_SEPARATOR);
            if(BEFORE_POINT.equals(point)){
                beforeFuncNames.add(StringUtils.substringBefore(func,FUNC_SEPARATOR));
            }else if(AFTER_POINT.equals(point)){
                afterFuncNames.add(StringUtils.substringBefore(func,FUNC_SEPARATOR));
            }else if(OVERWRITE_POINT.equals(point)){
                overwriteFuncNames.add(StringUtils.substringBefore(func,FUNC_SEPARATOR));
            }else{
                //没有配置point，默认取overwrite
                overwriteFuncNames.add(func);
            }
        }
        Map<String,List<String>> funcGroup = new HashMap<>();
        funcGroup.put(BEFORE_POINT,beforeFuncNames);
        funcGroup.put(AFTER_POINT,afterFuncNames);
        funcGroup.put(OVERWRITE_POINT,overwriteFuncNames);
        return funcGroup;
    }


}
