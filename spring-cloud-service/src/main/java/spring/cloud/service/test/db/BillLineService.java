package spring.cloud.service.test.db;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@FunctionalInterface
public interface BillLineService {

    /**
     * 查询所有记录
     */
    List<RefundBillLine> queryAll();

    /**
     * 不能和其他类的方法同名，最好加上业务标识
     * @return
     */
    default int default1() {
        System.out.println("默认方法");
        return 5;
    }

    @Transactional(rollbackFor = Exception.class)
    default int default3() {
        System.out.println("默认方法");
        return 5;
    }


    static int default2() {
        return 5;
    }

    static int default4() {
        return 5;
    }
}
