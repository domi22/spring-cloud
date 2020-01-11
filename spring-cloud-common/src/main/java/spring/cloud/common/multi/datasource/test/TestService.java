package spring.cloud.common.multi.datasource.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

//    @Autowired
//    DataSourceMapper dataSourceMapper;

    @Autowired
    ServiceB serviceB;

    public String getSource2Source(String user1slave, String user1slaveC) {
//        String nameA = dataSourceMapper.getName();
        //切换数据源B
        String nameB = serviceB.serverB(user1slave, user1slaveC);
        return nameB;
    }

    @Transactional(rollbackFor = Exception.class)
    public String getSourceByTx(String user1slave, String user1slaveC) {
//        dataSourceMapper.addShangp("增加A元");
//        String nameA = dataSourceMapper.getName();
        //切换数据源B

        String nameB = serviceB.serverBTx(user1slave, user1slaveC);
        return nameB;
    }


}
