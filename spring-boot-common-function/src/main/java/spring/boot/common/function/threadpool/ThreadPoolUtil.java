package spring.boot.common.function.threadpool;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class ThreadPoolUtil {
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private String poolNamePre;
    private double taskResponseTimeSeconds;
    private double taskWaitTimeSeconds;
    private double taskTimeSeconds;
    private int tasksParSecond;
    private double uCPU;
    private long keepAliveTime;
    private int rejectedType;
    private int queueType;
    private int unitType;


    public ThreadPoolUtil(double taskTimeSeconds,int tasksParSecond,double uCPU,double taskResponseTimeSeconds,
                          double taskWaitTimeSeconds, long keepAliveTime, int unitType,int queueType,int rejectedType,String poolNamePre){
      this.poolNamePre = poolNamePre;
      this.taskTimeSeconds = taskTimeSeconds;
      this.tasksParSecond = tasksParSecond;
      this.uCPU = uCPU;

      this.taskResponseTimeSeconds = taskResponseTimeSeconds;
      this.taskWaitTimeSeconds = taskWaitTimeSeconds;

      this.keepAliveTime = keepAliveTime;
      this.unitType = unitType;
      this.queueType = queueType;
      this.rejectedType = rejectedType;
    }


    public ThreadPoolExecutor getThreadPoolExecutorForIO() {
        return getThreadPoolExecutor(2);
    }

    public ThreadPoolExecutor getThreadPoolExecutorForCPU() {
        return getThreadPoolExecutor(1);

    }

    private ThreadPoolExecutor getThreadPoolExecutor(int executorForType) {
        int corePoolSize = getCorePoolSize();
        int maxPoolSize = getMaxPoolSize(corePoolSize,1);
        TimeUnit timeUnit = getTimeUnit(unitType);
        BlockingQueue queue = getBlockingQueue(queueType,corePoolSize);
        System.out.println("创建线程池的参数为corePoolSize=" + corePoolSize + "maxPoolSize = " + maxPoolSize + "queueSize=" + getQueueSize(corePoolSize));
        ThreadPoolExecutor warmThreadPoolExecutor = new WarmThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit,
                queue, new WarmThreadFactory(poolNamePre), new AbortPolicyWithReport());
        return warmThreadPoolExecutor;
    }

    private int getMaxPoolSize(int corePoolSize,int executorForType) {
        int maxPoolSize;
        if (executorForType == 1) {
            maxPoolSize = getMaxCorePoolSizeForCPUtask();
        } else {
            maxPoolSize = getMaxCorePoolSizeForIOtask();
        }
        return maxPoolSize < corePoolSize ? corePoolSize : maxPoolSize;
    }


    private TimeUnit getTimeUnit(int timeUnit){
        TimeUnit resultTimeUnit = TimeUnit.MILLISECONDS;
        switch (timeUnit){
            case 1:
                resultTimeUnit =  TimeUnit.SECONDS;
                break;
            case 2:
                resultTimeUnit =  TimeUnit.MICROSECONDS;
                break;
            case 3:
                resultTimeUnit =  TimeUnit.NANOSECONDS;
                break;
            case 4:
                resultTimeUnit =  TimeUnit.MINUTES;
                break;
            case 5:
                resultTimeUnit =  TimeUnit.HOURS;
                break;
            case 6:
                resultTimeUnit =  TimeUnit.DAYS;
                break;
            default:
                break;
        }
        return resultTimeUnit;
    }

    private BlockingQueue getBlockingQueue(int queueType, int corePoolSize){
        BlockingQueue queue = new ArrayBlockingQueue(getQueueSize(corePoolSize));
        switch (queueType){
            case 1:
                queue =  new LinkedBlockingQueue();
                break;
            case 2:
                queue =  new SynchronousQueue();
                break;
            default:
                break;
        }
        return queue;
    }

    private RejectedExecutionHandler getRejectedExecutionHandler(int rejectedType){
        RejectedExecutionHandler rejectedExecutionHandler = new AbortPolicyWithReport();
        switch (rejectedType) {
            case 1:
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
                break;
            case 2:
                rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
                break;
            case 3:
                rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
                break;
            default:
                break;
        }
        return rejectedExecutionHandler;
    }

    private int getCorePoolSize(){
        double size = tasksParSecond * taskTimeSeconds;
        return getIntFromDouble(size);
    }

    private int getMaxCorePoolSizeForCPUtask(){
        return PROCESSORS + 1;
    }

    private int getMaxCorePoolSizeForIOtask(){
        if (uCPU <= 0 || taskTimeSeconds <= 0 || taskWaitTimeSeconds <= 0) {
            return 2 * PROCESSORS;
        }
        double size = PROCESSORS * uCPU * (taskWaitTimeSeconds / taskTimeSeconds);
        return getIntFromDouble(size);
    }

    private int getQueueSize(int corePoolSize){
        double size = corePoolSize / taskTimeSeconds * taskResponseTimeSeconds;
        return getIntFromDouble(size);
    }

    private int getIntFromDouble(double size) {
        BigDecimal bsize = new BigDecimal(size).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bsize.toString());
    }



}
