package spring.cloud.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.redis.service.RedisService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RedisService redisService;

    @GetMapping("/name")
    public String getString(@RequestParam("name") String name) throws Exception {
        return redisService.getService(name);
    }
}
