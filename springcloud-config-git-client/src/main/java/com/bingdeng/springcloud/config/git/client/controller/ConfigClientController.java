package com.bingdeng.springcloud.config.git.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fran
 * @Date: 2018/12/10
 * @Desc:
 **/
@RestController
public class ConfigClientController {

    @Value("${bdpwd}")
    private String bdpwd;

    @GetMapping("/config")
    public String getConfig(){
        return this.bdpwd;
    }

}
