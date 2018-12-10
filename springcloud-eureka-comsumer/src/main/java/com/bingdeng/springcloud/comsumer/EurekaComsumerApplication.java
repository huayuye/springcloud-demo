package com.bingdeng.springcloud.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Fran
 * @Date: 2018/12/4
 * @Desc:
 **/
@SpringBootApplication
//使用方式一（Feign的方式）消费服务时，消费者需添加这个注解@EnableFeignClients,开启Feign
//方式二（RestTemplate）时，不用
@EnableFeignClients //开启Feign
@EnableEurekaClient // 配置本应用将使用服务注册和服务发现
public class EurekaComsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaComsumerApplication.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//        @Bean
//    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
//        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
//        c.setIgnoreUnresolvablePlaceholders(true);
//        return c;
//    }
}
