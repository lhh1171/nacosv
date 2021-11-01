package com.ease.arch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class TestController {

//    @Value("${title}")
//    private String title;
//
//    @Value("${version}")

//    private String version;


    @GetMapping("/consumer")
    public String hello() {

        return "title" + "+++++" + "version";
    }

}
