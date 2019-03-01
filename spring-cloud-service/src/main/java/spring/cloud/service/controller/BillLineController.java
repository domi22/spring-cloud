package spring.cloud.service.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.service.entity.RefundBillLine;
import spring.cloud.service.service.BillLineService;
import spring.cloud.service.service.open.InitDataListener;

import java.net.InetAddress;
import java.util.List;

@RestController
@RequestMapping("/service")
public class BillLineController {

    /** 开始时间截 (2015-01-01) */
    @Autowired
    BillLineService billLineService;

    @GetMapping("/bills")
    public String queryBill() throws Exception{
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        RLock lockName = redisson.getLock("lockName");
        InetAddress localHost = InetAddress.getLocalHost();
        String hostName = localHost.getHostName();
        List<RefundBillLine> refundBillLines1 = InitDataListener.refundBillLines;
        List<RefundBillLine> refundBillLines = billLineService.queryAll();
        return CollectionUtils.isEmpty(refundBillLines) ? "" : JSON.toJSONString(refundBillLines);
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file) throws Exception {
        return file.getOriginalFilename();
    }






}
