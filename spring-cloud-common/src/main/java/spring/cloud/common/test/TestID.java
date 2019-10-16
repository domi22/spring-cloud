package spring.cloud.common.test;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class TestID {

    public static void main(String[] args) {
//        System.out.println("work" + getWorkId());
//        System.out.println("date" + getDataCenterId());
    }

//    private static Long getWorkId(){
//        try {
//            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
//            int[] ints = StringUtils.toCodePoints(hostAddress);
//            int sums = 0;
//            for(int b : ints){
//                sums += b;
//            }
//            return (long)(sums % 32);
//        } catch (UnknownHostException e) {
//            // 如果获取失败，则使用随机数备用
//            return RandomUtils.nextLong(0,31);
//        }
//    }
//
//    private static Long getDataCenterId(){
//        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
//        int sums = 0;
//        for (int i: ints) {
//            sums += i;
//        }
//        return (long)(sums % 32);
//    }
}
