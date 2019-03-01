package spring.cloud.service.mapper;

import spring.cloud.service.entity.RefundBillLine;
import java.util.List;

public interface BillLineMapper {

    /**
     * 查询复合条件的所有记录
     * @return
     */
    List<RefundBillLine> queryInfos();
}
