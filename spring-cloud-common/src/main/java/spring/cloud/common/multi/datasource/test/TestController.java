package spring.cloud.common.multi.datasource.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class TestController {

    @Autowired
    TestService testService;

    /**
     * 不开启事务的数据源切换
     * @return
     */
    @GetMapping("/2/{source}/{sourceC}")
    public String getSource2(@PathVariable String source,@PathVariable String sourceC) {
        return testService.getSource2Source(source,sourceC);
    }

    /**
     * 开始事务的数据源切换（各自事务各自回滚?）
     */
    @GetMapping("/3/{source}/{sourceC}")
    public String getSourceByTx(@PathVariable String source,@PathVariable String sourceC) {
        return testService.getSourceByTx(source,sourceC);
    }


}
