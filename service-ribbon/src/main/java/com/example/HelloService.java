package com.example;

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

    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-CUSTOMER/hi?name="+name,String.class);
    }
}
