package spring.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.feign.feign.FeignService;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping("/bills")
    public String getBillByFeign() {
        return feignService.getBill();
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(MultipartFile file) throws Exception {
        return feignService.uploadFile(file);
    }

}
