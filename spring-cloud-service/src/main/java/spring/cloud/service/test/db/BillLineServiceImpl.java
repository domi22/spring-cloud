package spring.cloud.service.test.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillLineServiceImpl implements BillLineService {

    @Autowired
    BillLineMapper billLineMapper;


    @Override
    public List<RefundBillLine> queryAll() {
        return billLineMapper.queryInfos();
    }
}
