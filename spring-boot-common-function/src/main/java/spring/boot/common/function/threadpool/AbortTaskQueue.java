package spring.boot.common.function.threadpool;

import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AbortTaskQueue implements Serializable {

    /**
     * 用volatile防止指令重排序
     */
    private volatile static  AbortTaskQueue aborttaskqueue = null;
    private static final ConcurrentLinkedQueue<Runnable> concurrentLinkedQueue = new ConcurrentLinkedQueue();
    /**
     * 用 MAX_LENGTH 判断度列的长度，比用size()方法开销小
     */
    private static final int MAX_LENGTH = 10000;
    //AtomicAdder?
    private final AtomicInteger count = new AtomicInteger(0);

    private AbortTaskQueue() {}

    public Runnable poll() {
        Runnable poll = concurrentLinkedQueue.poll();
        if (poll == null) {
            count.set(0);
        }
        count.decrementAndGet();
        return poll;
    }

    public void add(Runnable r) {
        int total = count.get();
        if (total < MAX_LENGTH) {
            concurrentLinkedQueue.add(r);
            count.getAndIncrement();
        }
    }

    public static AbortTaskQueue newInstance(){
        if (aborttaskqueue == null) {
            synchronized (AbortTaskQueue.class) {
                if (aborttaskqueue == null) {
                    aborttaskqueue = new AbortTaskQueue();
                    /*
                     * 此时singleton非空，已经指向了分配的内存。
                     * 还需要下一步调私有构造方法构造对象。
                     * 调构造方法的时候会释放锁
                     * 因此需要用volatile修饰aborttaskqueue属性
                     */
                }
            }
        }
        return aborttaskqueue;
    }

    public int size(){
        return count.get();
    }
}
