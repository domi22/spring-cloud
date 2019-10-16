package spring.cloud.service.test.db;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.common.transdto.ResultInfo;
import spring.cloud.common.transdto.ResultUtil;

@Api(value="/test1", tags="测试接口模块")
@RestController
@RequestMapping("/service")
public class BillLineController{

    /** 开始时间截 (2015-01-01) */
    @Autowired
    BillLineService billLineService;


    @ApiOperation(value="获取用户列表value", notes="获取用户列表notes")
    @GetMapping("/bills")
    public ResultInfo queryBill(){
        return ResultUtil.getSuccessResult(billLineService.queryAll());
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file){
        return file.getOriginalFilename();
    }








}
