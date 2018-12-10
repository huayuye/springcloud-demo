package com.bingdeng.sprincloud.eureka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Fran
 * @Date: 2018/12/4
 * @Desc:
 **/
@SpringBootApplication
//提供者需添加这个注解，通过eureka注册服务注册中心
@EnableDiscoveryClient
public class EurekaProducerAppliction {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProducerAppliction.class,args);
    }
}
