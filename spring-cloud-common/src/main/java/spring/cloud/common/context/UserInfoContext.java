package spring.cloud.common.context;

import spring.cloud.common.vo.User;

public class UserInfoContext {
    private static ThreadLocal<User> userInfo = new ThreadLocal<>();
    public static final String USERINFO_KEY = "X-AUTO-USER";
    public static final String USERINFO_DECODER = "UTF-8";

    public UserInfoContext() {}

    public static User getUser(){
        return userInfo.get();
    }

    public static void setUser(User user){
        userInfo.set(user);
    }
}
