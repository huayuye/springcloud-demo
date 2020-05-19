package com.bingdeng.springcloud.comsumer.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @Author: Fran
 * @Date: 2020/5/19
 * @Desc:
 **/
@Component
@Aspect
public class TrackIdAOP {


    @Before(value = "execution(* com.bingdeng.springcloud.comsumer..*.*(..))")
    public void test(){
        MDC.put("bdTrckID",System.currentTimeMillis()+"");
    }

}
