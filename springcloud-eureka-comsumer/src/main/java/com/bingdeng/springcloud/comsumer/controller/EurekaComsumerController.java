package com.bingdeng.springcloud.comsumer.controller;

import com.bingdeng.springcloud.comsumer.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Fran
 * @Date: 2018/12/4
 * @Desc:
 **/
@RestController
public class EurekaComsumerController {

    @Autowired
    private HelloRemote helloRemote;

    @Value("${bdpwd}")
    private String bdpwd;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        //方式一（通过Feign消费服务）
        return helloRemote.hello(name);
        //方式二
//        return restTemplate.getForObject("http://localhost:8082/hello?name="+name,String.class);
    }

    @GetMapping("/config")
    public String testConfig() {
        return this.bdpwd;
    }

}
