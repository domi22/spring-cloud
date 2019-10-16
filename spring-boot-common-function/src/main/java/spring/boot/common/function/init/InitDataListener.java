package spring.boot.common.function.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import javax.servlet.ServletContext;

@Service
public class InitDataListener implements InitializingBean,ServletContextAware {

    public static String dbstr;


    @Override
    public void afterPropertiesSet() throws Exception {}

    /**
     * ServeletContextAware继承自Aware接口，当一个类继承了ServletContextAware接口后
     * 它就可以取得servletContex对象，我们可以利用它来给ServletContext初始化对象
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        /**
         * 给上下文设置值
         */
//        PropertyHolder.servletContext = servletContext;
        servletContext.setAttribute("ctx", servletContext.getContextPath());

        /**
         * 大数据量需要分页查询，操作之后需要list.clear(), 清除掉集合的内存
         * 启动的时候查询数据库，只适用于固定的数据，数据修改的时候要做同步处理,
         * 一般是查询之后缓存到redis
         */
        dbstr = "注入mapper去查询数据库获取值";
    }
}
