package spring.cloud.common.multi.datasource.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.common.function.mapper.DataSourceMapper;
import spring.cloud.common.multi.datasource.DataSource;

@Service
public class ServiceC {

    @Autowired
    DataSourceMapper dataSourceMapper;

    @DataSource(value = "#dataSource")
    public String serverC(String dataSource) {
        return dataSourceMapper.getName();
    }


    @DataSource(value = "#dataSource")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public String serverCTx(String dataSource) {
        dataSourceMapper.addShangp("增加C元");
        return dataSourceMapper.getName();
    }
}
