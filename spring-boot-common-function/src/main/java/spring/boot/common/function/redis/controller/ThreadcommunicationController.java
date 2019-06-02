package spring.boot.common.function.redis.controller;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试分布式环境下的线程通信
 * 证明Buget对象是不可用的，RCountDownLatch 是可以用的。
 */
@RestController
@RequestMapping("/bg")
public class ThreadcommunicationController {

    @Autowired
    private RedissonClient redissonClient;
    private String lock1 = "lock";


    @GetMapping("/putlock")
    public String putLock(@RequestParam("lock") String lock) {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(lock);
        countDownLatch.trySetCount(1);
        String s = "put" + lock + "ok";
        System.out.println(s);
        return s;
    }

    @GetMapping("/deletelock")
    public String deleteLock(@RequestParam("lock") String lock) {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(lock);
        countDownLatch.delete();
        String s = "delete" + lock + "ok";
        System.out.println(s);
        return s;
    }


    /**
     * 等待被通知的线程
     * @param lock
     * @return
     */
    @GetMapping("/waiting-lock")
    public String getLock(@RequestParam("lock") String lock) {
        System.out.println("thread a  is waiting....");
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(lock);
        try {
            // 这里要防止重复请求（重复请求应该是不同的线程），造成多个线程在等待，唤醒之后会造成逻辑的重复处理。
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("server a  is waiting  exception....");
        }
        String s = "server a  waiting " + lock + "  over";
        System.out.println(s);
        return s;
    }

    /**
     * 可以在其他的JVM线程中执行唤醒操作，有可能唤醒多个等待的线程（重复提交，造成多个线程wait,业务代码确保只有一个线程在等待）
     * @param lock
     * @return
     */
    @GetMapping("/relax-lock")
    public String relaxLock(@RequestParam("lock") String lock) {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(lock);
        countDownLatch.countDown();
        String s = "server b  relax " + lock;
        System.out.println(s);
        return s;
    }


}
