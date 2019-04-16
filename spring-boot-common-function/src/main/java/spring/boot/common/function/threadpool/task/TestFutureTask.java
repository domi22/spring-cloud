package spring.boot.common.function.threadpool.task;

import java.util.concurrent.Callable;

/**
 * 如何设计一个任务:
 * 1- 是否具备可取消操作
 * 2- 是否能响应中断，中断是实现取消的最佳实践
 * 3- call方法中要允许线程的退出
 */
public class TestFutureTask<V> implements Callable {
    private static final String TASK_NAME = "TEST_TASK";
    //取消标志位
    private volatile boolean cancelled;
    private String param1;
    private String param2;

    public TestFutureTask(String param1,String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public String call() throws Exception {
        //TODO 设置允许线程退出的方法
        System.out.println("call任务执行完毕了");
        Thread.sleep(3000);
        return param2 + param1;
    }

    public void cancel() {
        cancelled = true;
    }

    public String getParam() {
        return param1 + param2;
    }

}
