package spring.cloud.common.interceptor;

import spring.cloud.common.vo.User;

public class UserContextHolder {
    public static ThreadLocal<User> context = new ThreadLocal<>();
    public static User currentUser() {
        return context.get();
    }
    public static void set(User user) {
        context.set(user);
    }
    public static void shutdown() {
        context.remove();
    }
}