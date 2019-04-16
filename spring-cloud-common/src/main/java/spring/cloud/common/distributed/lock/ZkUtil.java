package spring.cloud.common.distributed.lock;


import org.I0Itec.zkclient.ZkClient;

public class ZkUtil {

    public static ZkClient getZkClient(String zkService,int connectionTimeout){
        return new ZkClient(zkService,connectionTimeout);
    }


}
