package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author Dong.Chao
 * @Classname HelloControler
 * @Description
 * @Date 2021/6/1 15:42
 * @Version V1.0
 */
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @PostConstruct
    public void init(){
        System.out.println();
    }

    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }

    @RequestMapping(value = "/hi2")
    public String hi2(@RequestParam String name){
        return helloService.hiService(name);
    }


}
