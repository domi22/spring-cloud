package spring.boot.common.function.main.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    /**
     * 测试类容：putIfAbsent()方法的返回值
     *
     * @param args
     */
    public static void main(String[] args) {
//        testNull();
//        List<String> list = new ArrayList<>();
//        for (String s : list) {
//            System.out.println(s.length());
//        }
//        System.out.println("123456");

        testTry();
    }

    private static void testNull() {
        ConcurrentHashMap<String, Object> cmap = new ConcurrentHashMap<>();
        Object o = cmap.putIfAbsent("nihao", "true");
        //存在返回旧值；不存在则put进去并返回null
    }

    private static void testTry() {
        System.out.println("try");
        try {
            System.out.println(3 / 0);
        } finally {
            System.out.println("fianlly3333");
        }
    }
}
