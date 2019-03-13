package spring.cloud.common.test;

import com.alibaba.fastjson.JSON;
import spring.cloud.common.vo.User;

public class Test {

    public static void main(String[] args) {
        User user = new User();
        user.setUserId("13345");
        user.setUserName("zhagdeee");
        String s = JSON.toJSONString(user);
        System.out.println("suer------" + s);
    }
}
