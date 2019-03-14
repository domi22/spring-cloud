package spring.cloud.service.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.common.context.UserInfoContext;
import spring.cloud.common.vo.User;
import spring.cloud.service.entity.RefundBillLine;
import spring.cloud.service.service.BillLineService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/service")
public class BillLineController {

    /** 开始时间截 (2015-01-01) */
    @Autowired
    BillLineService billLineService;

    @GetMapping("/bills")
    public String queryBill(HttpServletRequest request,String param){
        User user = UserInfoContext.getUser();
        ConcurrentHashMap map = new ConcurrentHashMap(32);
        Object o = map.computeIfAbsent("12",k -> {return "123";});
        List<RefundBillLine> refundBillLines = billLineService.queryAll();
        return CollectionUtils.isEmpty(refundBillLines) ? "" : JSON.toJSONString(refundBillLines);
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file){
        return file.getOriginalFilename();
    }






}
