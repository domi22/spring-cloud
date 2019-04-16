package spring.cloud.common.distributed.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class TestZKWatch {

    public static void main(String[] args) {

        ZkClient zkClient = ZkUtil.getZkClient("127.0.0.1:2181", 3000);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(Thread.currentThread().getName() + "线程收到上一个节点被删除的消息");
            }
            @Override
            public void handleDataDeleted(String s) throws Exception {
            }
        };
        zkClient.subscribeDataChanges("/dd",listener);
    }
}
