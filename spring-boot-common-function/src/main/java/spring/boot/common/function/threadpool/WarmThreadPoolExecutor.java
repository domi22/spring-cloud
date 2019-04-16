package spring.boot.common.function.threadpool;

import com.test.domi.task.FileLoadTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class WarmThreadPoolExecutor extends ThreadPoolExecutor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public WarmThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                  TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public WarmThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                  TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public WarmThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public WarmThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info(String.format("Thread %s: start at %s", t, r));
        startTime.set(System.nanoTime());
    }

    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info(String.format("Thread %s: end at %s, time = %dns", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    protected void terminated() {
        try {
            log.info(String.format("Terminated: avg time = %dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }
    }


    /**
     * 重写newTaskFor()方法，返回自定义的Future
     */
    @Override
    protected <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
        if (callable instanceof FileLoadTask) {
            return ((FileLoadTask<V>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }
}
