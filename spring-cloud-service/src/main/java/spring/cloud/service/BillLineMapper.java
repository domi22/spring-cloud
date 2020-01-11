package spring.cloud.service;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BillLineMapper {

    /**
     * 查询复合条件的所有记录
     * @return
     */
    List<RefundBillLine> queryInfos();
}

