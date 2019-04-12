package spring.cloud.service.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
//import spring.cloud.common.context.UserInfoContext;
//import spring.cloud.common.util.ResultInfo;
//import spring.cloud.common.util.ResultUtil;
//import spring.cloud.common.vo.User;
import spring.cloud.service.entity.RefundBillLine;
import spring.cloud.service.mapper.TestDateMapper;
import spring.cloud.service.service.BillLineService;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Api(value="/test1", tags="测试接口模块")
@RestController
@RequestMapping("/service")
public class BillLineController extends BaseController {

    /** 开始时间截 (2015-01-01) */
    @Autowired
    BillLineService billLineService;
    @Autowired
    TestDateMapper testDateMapper;

    @Autowired
    RestTemplate restTemplate;


//    @ApiOperation(value="获取用户列表value", notes="获取用户列表notes")
//    @GetMapping("/bills")
//    public ResultInfo queryBill(HttpServletRequest request,String param){
//        User user = UserInfoContext.getUser();
//        ConcurrentHashMap map = new ConcurrentHashMap(32);
//        Object o = map.computeIfAbsent("12",k -> {return "123";});
//        List<RefundBillLine> refundBillLines = billLineService.queryAll();
//        refundBillLines.get(0).setCreatedDate(new Date());
//        return ResultUtil.getSuccessResult(refundBillLines);
////        return CollectionUtils.isEmpty(refundBillLines) ? "" : JSON.toJSONString(refundBillLines);
//    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file){
        return file.getOriginalFilename();
    }








}
