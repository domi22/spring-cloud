package spring.cloud.common.filter;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.cloud.common.context.UserInfoContext;
import spring.cloud.common.vo.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
            try {
                userJson = URLDecoder.decode(userJson,UserInfoContext.USERINFO_DECODER);
                User userInfo = (User) JSON.parseObject(userJson,User.class);
                UserInfoContext.setUser(userInfo);
            } catch (UnsupportedEncodingException e) {
                log.error("get userInfo from feign request header error",e);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
