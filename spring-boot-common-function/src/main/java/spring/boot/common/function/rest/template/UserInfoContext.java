package spring.boot.common.function.rest.template;

public class UserInfoContext {

    private static ThreadLocal<String> userInfo = new ThreadLocal<>();
    public static final String USERINFO_KEY = "X-AUTO-USER";

    public UserInfoContext() {}

    public static String getUser(){
        return userInfo.get();
    }

    public static void setUser(String user){
        userInfo.set(user);
    }
}
