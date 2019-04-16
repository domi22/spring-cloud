package spring.boot.common.function.threadpool;

import org.apache.commons.lang3.StringUtils;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class WarmThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    WarmThreadFactory(String poolName) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        poolName = StringUtils.isBlank(poolName) ? "-" : (poolName + "-");
        namePrefix = poolName + "pool-" +  poolNumber.getAndIncrement() +  "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String rName = r.toString();
        String threadName = namePrefix + threadNumber.getAndIncrement() + "-" + rName.substring((rName.indexOf("$") + 1),rName.indexOf("["));
        Thread t = new Thread(group, r,threadName,0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
