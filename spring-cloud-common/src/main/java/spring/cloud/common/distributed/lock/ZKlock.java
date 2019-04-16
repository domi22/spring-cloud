package spring.cloud.common.distributed.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZKlock implements Lock {

    private ZkClient zkClient;
    private String rootPath;
    private String currentPath;
    private String beforePath;

    public ZKlock(String url, String rootPath){
        this.rootPath = rootPath;
        zkClient = new ZkClient(url);
        if (!zkClient.exists(rootPath)) {
            try {
                zkClient.createPersistent(rootPath);
            } catch (RuntimeException e) {
            }
        }
    }

    @Override
    public boolean tryLock() {
        if (currentPath == null) {
            currentPath = zkClient.createEphemeralSequential(rootPath + "/","aaa");
        }
        List<String> childrens = zkClient.getChildren(rootPath);
        Collections.sort(childrens);
        if (currentPath.equals(rootPath + "/" + childrens.get(0))) {
            return true;
        }else{
            int curIndex = childrens.indexOf(currentPath.substring(rootPath.length() + 1));
            beforePath = rootPath + "/" + childrens.get(curIndex - 1);
        }
        return false;
    }

    @Override
    public void lock() {
        if (!tryLock()) {
            waiForLock();
            lock();
        }
    }

    @Override
    public void unlock() {
        zkClient.delete(currentPath);
    }

    private void waiForLock(){
        CountDownLatch cdl = new CountDownLatch(1);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
                cdl.countDown();
            }
        };
        zkClient.subscribeDataChanges(beforePath,listener);
        if (zkClient.exists(beforePath)) {
            try {
                cdl.await();
            } catch (InterruptedException e) {
            }
        }
        zkClient.unsubscribeDataChanges(beforePath,listener);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
