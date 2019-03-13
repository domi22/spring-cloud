package spring.cloud.common.interceptor;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.cloud.common.vo.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TransmitUserInfoFeighClientInterceptor implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TransmitUserInfoFeighClientInterceptor.class);
    public TransmitUserInfoFeighClientInterceptor() {}

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //从应用上下文中取出user信息，放入Feign的请求头中
        User user = UserInfoContext.getUser();
        if (user != null) {
            try {
                String userJson = JSON.toJSONString(user);
                requestTemplate.header(UserInfoContext.USERINFO_KEY,new String[]{URLDecoder.decode(userJson,"UTF-8")});
            } catch (UnsupportedEncodingException e) {
                log.error("用户信息设置错误",e);
            }
        }
    }
}
