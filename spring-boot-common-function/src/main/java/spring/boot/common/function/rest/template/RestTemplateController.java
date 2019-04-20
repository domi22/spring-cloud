package spring.boot.common.function.rest.template;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {


    @GetMapping("/test")
    public String testRestTemplate(){

        return null;
    }

}
