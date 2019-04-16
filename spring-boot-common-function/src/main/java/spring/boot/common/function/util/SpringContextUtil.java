package spring.boot.common.function.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具类
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * spring应用上下文
     */
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取Bean对象
     * @param calssz bean类
     * @return bean实例
     */
    public static <T> T getBean(Class<T> calssz){
        return applicationContext.getBean(calssz);
    }

    /**
     * 获取Bean对象
     * @param name beanName
     * @return bean对象
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

}
