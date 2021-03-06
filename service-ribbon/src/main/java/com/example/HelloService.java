package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dong.Chao
 * @Classname HelloService
 * @Description
 * @Date 2021/6/1 15:39
 * @Version V1.0
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-CUSTOMER/hi?name="+name,String.class);
    }


    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
