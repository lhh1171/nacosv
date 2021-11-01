package com.ease.arch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TestController {


    @GetMapping("/test")
    @CacheInsert
    @CacheDelete
    public String hello1(@RequestParam String name) {
        return "hello1" + name;
    }

    @GetMapping("/test")
    @CacheInsert
    @CacheDelete
    public String hello2(@RequestParam String name) {
        return "hello2" + name;
    }
}
