package com.ease.arch.controller;

import com.ease.arch.remote.Client;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private Client client;

    @GetMapping("/consumer")
    public String hello(@RequestParam String name) {
        ServiceInstance choose = loadBalancerClient.choose("nacos-provider-demo");
        String uri = choose.getUri() + "/test?name=" + name;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        log.info("consumer result={}", result);
        return "consumer result" + name;
    }

    @GetMapping("feign_consumer")
    public String helloFeign(@RequestParam String name) {
        return "feign consumer result" + client.helloFeign(name);
    }
}
