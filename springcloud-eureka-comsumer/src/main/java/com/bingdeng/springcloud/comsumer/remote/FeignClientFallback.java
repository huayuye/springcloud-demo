package com.bingdeng.springcloud.comsumer.remote;

import org.springframework.stereotype.Component;

/**
 * @Author: Fran
 * @Date: 2018/12/5
 * @Desc:
 **/
@Component
public class FeignClientFallback implements HelloRemote {
    @Override
    public String hello(String name) {
        System.out.println("access remote server error!");
        return "access remote server error!";
    }
}
