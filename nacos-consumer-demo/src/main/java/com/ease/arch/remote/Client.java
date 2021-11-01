package com.ease.arch.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("nacos-provider-demo")
public interface Client {

    @GetMapping("/test")
    String helloFeign(@RequestParam(name = "name") String name);
}
