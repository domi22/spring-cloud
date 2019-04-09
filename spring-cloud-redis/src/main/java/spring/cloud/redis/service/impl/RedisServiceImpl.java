package spring.cloud.redis.service.impl;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.cloud.redis.service.RedisService;
import spring.cloud.redis.vo.Person;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedissonClient redissonClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getService(String name) {
        TransactionOptions defaults = TransactionOptions.defaults();
        defaults.timeout(1L,TimeUnit.SECONDS);
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keys3 = keys.getKeys(5);
        Iterable<String> keysByPattern1 = keys.getKeysByPattern("m*");


        Iterator<String> iterator2 = keys3.iterator();
        int i = 0;
        while (iterator2.hasNext()) {
            System.out.println(i + iterator2.next() + "");
            i++;
        }

        Iterator<String> iterator3 = keysByPattern1.iterator();
        int j = 0;
        while (iterator3.hasNext()) {
            System.out.println(j + iterator3.next() + "");
            j++;
        }

        Iterable<String> keysByPattern = redissonClient.getKeys().getKeysByPattern("*");
        Iterable<String> keysByPattern2 = redissonClient.getKeys().getKeysByPattern("*", 2);
        Iterator<String> iterator1 = keysByPattern.iterator();

        Iterator<String> iterator = keysByPattern2.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            RBucket<Object> bucket = redissonClient.getBucket(next);
            System.out.println("1234");
        }

        redissonClient.createBatch(BatchOptions.defaults());
        RTransaction transaction = redissonClient.createTransaction(defaults);
        RBucket<Person> rname = redissonClient.getBucket("person455");
        Person person = new Person();
        person.setId(11);
        person.setName(name);
        person.setNum(new BigDecimal(11.456));
        person.setUadteTime(new Date());
        rname.set(person);
        //此时取出来的person是一个字符串的形式
        Person person1 = rname.get();
        String name1 = person1.getName();
        Date uadteTime = person.getUadteTime();

        RAtomicLong atomicLong = redissonClient.getAtomicLong("myAtomicLong222");
        atomicLong.set(3);
        long lwert = atomicLong.incrementAndGet();
        long l1werty = atomicLong.get();

        RAtomicDouble atomicDouble = redissonClient.getAtomicDouble("myAtomicDouble222");
        atomicDouble.set(2.81);
        double vwr = atomicDouble.addAndGet(4.11);
        double v1wert = atomicDouble.get();

        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("sample222");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("field1Value");
        bloomFilter.add("field5Value");
        boolean field1Value = bloomFilter.contains("field1Value");


        RHyperLogLog<Integer> log = redissonClient.getHyperLogLog("log222");
        log.add(1);
        log.add(2);
        log.add(3);
        long count = log.count();


        //据统计其性能最高比分布式AtomicLong对象快 12000 倍。完美适用于分布式统计计量场景。
        RLongAdder atomicLong222 = redissonClient.getLongAdder("myLongAdder222");
        atomicLong222.add(12);
        atomicLong222.increment();
        atomicLong222.decrement();
        long sum = atomicLong222.sum();
        atomicLong222.destroy();


//        RLongDouble atomicDouble22 = redissonClient.getLongDouble("myLongDouble");
//        atomicDouble22.add(12);
//        atomicDouble22.increment();
//        atomicDouble22.decrement();
//        atomicDouble22.sum();

        RList<Object> ni = redissonClient.getList("ni");
        Iterable<String> persion = redissonClient.getKeys().getKeysByPattern("persion", 10);
        RKeys keys1 = redissonClient.getKeys();
        try {
            System.out.println(3 / 0);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return "ok";
    }
}
