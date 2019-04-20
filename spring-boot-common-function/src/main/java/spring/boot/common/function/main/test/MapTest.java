package spring.boot.common.function.main.test;

import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    /**
     * 测试类容：putIfAbsent()方法的返回值
     * @param args
     */
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> cmap = new ConcurrentHashMap<>();
        Object o = cmap.putIfAbsent("nihao", "true");
        //存在返回旧值；不存在则put进去并返回null
    }
}
