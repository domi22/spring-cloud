package spring.cloud.common.multi.datasource.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.cloud.common.multi.datasource.DataSource;

@Service
public class ServiceB {

//    @Autowired
//    DataSourceMapper dataSourceMapper;

    @Autowired
    ServiceC serviceC;

    @DataSource(value = "#dataSource")
    public String serverB(String dataSource,String dataSourceC) {
//        String b = dataSourceMapper.getName();
        //切换数据C
        String c = serviceC.serverC(dataSourceC);
        return c;
    }

    @DataSource(value = "#dataSource")
    @Transactional(rollbackFor = Exception.class , propagation = Propagation.REQUIRES_NEW)
    public String serverBTx(String dataSource,String dataSourceC) {
//        dataSourceMapper.addShangp("增加B元");
//        String b = dataSourceMapper.getName();
        //切换数据C
        String c = null;
        try {
            c = serviceC.serverCTx(dataSourceC);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("C元异常");
        }
        System.out.println(3/0);
        return c;
    }


}
