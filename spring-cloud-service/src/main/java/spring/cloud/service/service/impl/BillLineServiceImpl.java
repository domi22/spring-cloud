package spring.cloud.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.cloud.service.entity.RefundBillLine;
import spring.cloud.service.mapper.BillLineMapper;
import spring.cloud.service.service.BillLineService;
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
