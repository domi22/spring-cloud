package spring.cloud.common.multi.datasource.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spring.cloud.common.multi.datasource.config.DynamicDataSourceContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DynamicDataSourceInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(HandlerInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //TODO 从用户信息中获取UserInfo，放入请求头holder中，从userId获取数据源，放入数据源holder中
        String dsId = httpServletRequest.getHeader("ds");
        if (DynamicDataSourceContextHolder.dataSourceIds.contains(dsId)) {
            //todo 降级方案 : 在业务方法上通过传播机制新开启一个事务，从事实现切换）
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
        } else {
            logger.info("数据源[{}]不存在，使用默认数据源 >{}", dsId);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        DynamicDataSourceContextHolder.finalRemove();
        //清除用户请求头信息 && 清除用户数据源信息
        //如果这里抛异常，会请求/error路劲，preHandle还会执行一次。
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
