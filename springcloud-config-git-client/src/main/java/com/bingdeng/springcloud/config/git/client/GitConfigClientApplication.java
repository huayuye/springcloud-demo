package com.bingdeng.springcloud.config.git.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Fran
 * @Date: 2018/12/10
 * @Desc:
 **/
@SpringBootApplication
@EnableEurekaClient
public class GitConfigClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(GitConfigClientApplication.class,args);
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//        c.setIgnoreUnresolvablePlaceholders(true);
//        return c;
//    }

}
