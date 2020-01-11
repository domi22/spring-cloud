package spring.cloud.common.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.cloud.common.redis.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping("/name")
    public String getString(@RequestParam("name") String name){
        return redisService.getService(name);
    }



}
