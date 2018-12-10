package com.bingdeng.sprincloud.eureka.producer.controller;

import com.bingdeng.sprincloud.eureka.producer.utils.SMBFlieUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Fran
 * @Date: 2018/12/4
 * @Desc:
 **/
@RestController
public class EurekaProducerContrller {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name){
        System.out.println("欢迎光临eureka-comsumer:"+name);
        return name;
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam(value = "file",required = true)MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            if(!SMBFlieUtils.writeFile("username","pwd",file.getInputStream(),"smb://192.xx.xx.xxx/share/"+originalFilename)){
                return "file upload fail";
            }
        } catch (IOException e) {
            System.out.println("file upload Exception:"+e.toString());
        }
        return "file upload success";
    }

}
