package spring.cloud.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import spring.cloud.feign.config.FeignMultipartSupportConfig;

@FeignClient(name = "SPRING-CLOUD-SERVICE",configuration = FeignMultipartSupportConfig.class)
@RequestMapping("/spring-cloud-service")
public interface FeignService {

    @GetMapping(value = "/service/bills")
    String getBill();

    @PostMapping(value = "/service/upload",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart(value = "file")MultipartFile file);

}
