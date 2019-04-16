package spring.boot.common.function.threadpool.task;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public interface CancellableTask<V> extends Callable<V> {

    void cancel();
    /**
     * 创建自定义的FutureTask
     */
    RunnableFuture<V> newTask();
}
