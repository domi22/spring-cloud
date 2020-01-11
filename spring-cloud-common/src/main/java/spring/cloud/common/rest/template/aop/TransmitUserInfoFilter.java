package spring.cloud.common.rest.template.aop;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import spring.boot.common.function.rest.template.beans.UserInfoContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class TransmitUserInfoFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TransmitUserInfoFilter.class);
    public TransmitUserInfoFilter() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.initUserInfo((HttpServletRequest)request);
        chain.doFilter(request,response);
    }

    private void initUserInfo(HttpServletRequest request){
        String userJson = request.getHeader(UserInfoContext.USERINFO_KEY);
        if (StringUtils.isNotBlank(userJson)) {
            UserInfoContext.setUser(userJson);
        }
    }

    @Override
    public void destroy() {
    }
}
