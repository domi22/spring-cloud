package spring.boot.common.function.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.boot.common.function.redis.service.RedisService;

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
