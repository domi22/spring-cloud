package spring.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.gateway.common.JwtUtil;

@RestController
public class TokenController {

    @GetMapping("/getToken/{name}/{pwd}")
    public String get(@PathVariable("name") String name,@PathVariable("pwd") String pwd)  {
        return JwtUtil.generateToken(name,pwd);
    }

}
