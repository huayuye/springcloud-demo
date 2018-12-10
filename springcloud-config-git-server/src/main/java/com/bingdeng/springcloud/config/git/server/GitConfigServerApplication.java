package com.bingdeng.springcloud.config.git.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Fran
 * @Date: 2018/12/10
 * @Desc:
 **/
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class GitConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitConfigServerApplication.class,args);
    }
}
