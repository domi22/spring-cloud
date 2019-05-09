package spring.cloud.common.header.transmit;

import spring.cloud.common.vo.User;

public class UserInfoContext {
    private static final ThreadLocal<User> USER_INFO = new ThreadLocal<>();
    public static final String USERINFO_KEY = "X-AUTO-USER";
    public static final String USERINFO_DECODER = "UTF-8";

    public static User getUser(){
        return USER_INFO.get();
    }

    public static void setUser(User user){
        USER_INFO.set(user);
    }
}
