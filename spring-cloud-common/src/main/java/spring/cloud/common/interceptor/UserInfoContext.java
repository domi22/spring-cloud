package spring.cloud.common.interceptor;

import spring.cloud.common.vo.User;

public class UserInfoContext {
    private static ThreadLocal<User> userInfo = new ThreadLocal<>();
    public static String USERINFO_KEY = "X-AUTO-USER";

    public UserInfoContext() {}

    public static User getUser(){
        return userInfo.get();
    }

    public static void setUser(User user){
        userInfo.set(user);
    }
}
