package spring.boot.common.function.threadpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.boot.common.function.threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Value("${executor.poolNamePre}")
    private String poolNamePre;

    @Value("${executor.taskResponseTimeSeconds}")
    private double taskResponseTimeSeconds;
    @Value("${executor.taskWaitTimeSeconds}")
    private double taskWaitTimeSeconds;

    @Value("${executor.taskTimeSeconds}")
    private double taskTimeSeconds;
    @Value("${executor.tasksParSecond}")
    private int tasksParSecond;
    @Value("${executor.uCPU}")
    private double uCPU;

    @Value("${executor.keepAliveTime}")
    private long keepAliveTime;
    @Value("${executor.rejectedType}")
    private int rejectedType;
    @Value("${executor.queueType}")
    private int queueType;
    @Value("${executor.unitType}")
    private int unitType;

    @Bean("executor")
    public ThreadPoolExecutor getThreadPoolExecutor(){
        ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil(taskTimeSeconds, tasksParSecond, uCPU, taskResponseTimeSeconds,
                taskWaitTimeSeconds,  keepAliveTime,  unitType, queueType, rejectedType,poolNamePre);
        return threadPoolUtil.getThreadPoolExecutorForCPU();
    }
}
