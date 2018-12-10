package com.bingdeng.springcloud.comsumer.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**此为方式一（Feign消费服务）
 * @FeignClient指定调用哪个服务
 * 通过feign访问服务,
 *  name:指定服务的名称
 *  fallback：指定断路器
 *  feign默认已经使用ribbion做为负载均衡：详细配置见 FeignRibbonClientAutoConfiguration
 *
 *  方式二：可以直接使用restTemplate调用producer接口（少创建了HelloRemote这样的接口）
 *
 *   * @Author: Fran
 *  * @Date: 2018/12/4
 *  * @Desc:
 */
//@FeignClient(name= "spring-cloud-producer",fallback = FeignClientFallback.class)
@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote {
    //对应指定服务中的api
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name);
}
