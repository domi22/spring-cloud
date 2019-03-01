package spring.cloud.service.service;

import spring.cloud.service.entity.RefundBillLine;
import java.util.List;

public interface BillLineService {

    /**
     *查询所有记录
     */
    List<RefundBillLine> queryAll();
}
