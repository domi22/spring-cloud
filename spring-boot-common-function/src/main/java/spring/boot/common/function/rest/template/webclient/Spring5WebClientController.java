package spring.boot.common.function.rest.template.webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webc")
public class Spring5WebClientController {


    @GetMapping("/get")
    public String getWeb() {
        return null;
    }



    /*----------下面是模拟远程接口----------*/



}
