package spring.cloud.common.header.transmit;

import spring.cloud.common.auth.User;

public class UserContextHolder {
    private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

    public static User get() {
        return CONTEXT.get();
    }
    public static void set(User user) {
        CONTEXT.set(user);
    }
    public static void remove() {
        CONTEXT.remove();
    }
}