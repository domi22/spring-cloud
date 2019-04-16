package spring.boot.common.function.threadpool;

import com.alibaba.fastjson.JSON;

import java.util.Random;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {
    private static final AbortTaskQueue abortTaskQueue = AbortTaskQueue.newInstance();

    public AbortPolicyWithReport() {}

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        //todo 记录任务至于DB,MQ,等，实现最终一致性.通过Runnabe 的 instance of 类型区分任务的类型， 封装一条记录到DB表
        abortTaskQueue.add(r);
        String param = JSON.toJSONString(r);
        if (new Random().nextInt(6) > 1) {
            Runnable poll = abortTaskQueue.poll();
            System.out.println("我消费了一个");
        }
        System.out.println("线程" + Thread.currentThread().getName() + "丢弃任务，参数为==》" + param);
        throw new RejectedExecutionException("Task-" + r.toString() + " -rejected from- " + e.toString());
    }


}
