package spring.boot.common.function.rest.template;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * spring5 推荐使用webClient
 */
@RestController
@RequestMapping("/syn")
public class AsyncRestTemplateController {

    @GetMapping("/get")
    public String getSyn() {

        return null;
    }
}
