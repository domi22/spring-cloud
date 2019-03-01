package spring.cloud.service.service.open;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import spring.cloud.service.entity.RefundBillLine;
import spring.cloud.service.service.BillLineService;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;

@Service
public class InitDataListener implements InitializingBean,ServletContextAware {

    @Resource
    private BillLineService billLineService;
    public static List<RefundBillLine> refundBillLines;

    @Override
    public void afterPropertiesSet() throws Exception {}

    @Override
    public void setServletContext(ServletContext servletContext) {
        /**
         * 大数据量需要分页查询，操作之后需要list.clear(), 清除掉集合的内存
         */
        refundBillLines = billLineService.queryAll();
    }
}
