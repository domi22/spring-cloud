package spring.cloud.common.config;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import spring.cloud.common.util.id.SnowflakeIdWorker;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * 网上的教程一般存在两个问题：
 * 1. 机器ID（5位）和数据中心ID（5位）配置没有解决，分布式部署的时候会使用相同的配置，任然有ID重复的风险。
 * 2. 使用的时候需要实例化对象，没有形成开箱即用的工具类。
 *
 * 本文针对上面两个问题进行解决，笔者的解决方案是，workId使用服务器hostName生成，
 * dataCenterId使用IP生成，这样可以最大限度防止10位机器码重复，但是由于两个ID都不能超过32，
 * 只能取余数，还是难免产生重复，但是实际使用中，hostName和IP的配置一般连续或相近，
 * 只要不是刚好相隔32位，就不会有问题，况且，hostName和IP同时相隔32的情况更加是几乎不可能
 * 的事，平时做的分布式部署，一般也不会超过10台容器。使用上面的方法可以零配置使用雪花算法，
 * 雪花算法10位机器码的设定理论上可以有1024个节点，生产上使用docker配置一般是一次编译，
 * 然后分布式部署到不同容器，不会有不同的配置，这里不知道其他公司是如何解决的，即使有方法
 * 使用一套配置，然后运行时根据不同容器读取不同的配置，但是给每个容器编配ID，1024个
 * （大部分情况下没有这么多），似乎也不太可能，此问题留待日后解决后再行补充。
 */
@Configuration
public class IdWorkerConfiguration {
    Logger logger = LogManager.getLogger();
    @Bean
    @Primary
    public SnowflakeIdWorker idWorker(){
        return new SnowflakeIdWorker(getWorkId(),getDataCenterId());
    }

    private Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0,31);
        }
    }

    private Long getDataCenterId(){
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }

}
