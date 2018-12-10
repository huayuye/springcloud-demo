package com.bingdeng.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Fran
 * @Date: 2018/12/5
 * @Desc:
 **/

@Component
public class TokenFilter extends ZuulFilter {
    /**
     *
     * 过滤器的类型:4种
     *
     * 1、PRE过滤器: 在请求被路由之前调用, 可用来实现身份验证、在集群中选择请求的微服务、记录调试信息等;
     *
     * 2、ROUTING过滤器: 在路由请求时候被调用;
     *
     * 3、POST过滤器: 在路由到微服务以后执行, 可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等;
     *
     * 4、ERROR过滤器: 在处理请求过程时发生错误时被调用。
     *
     * 用作登录校验等操作时，会使用pre过滤器
     */
    @Override
    public String filterType() {
        return "pre";
    }
    /**
     * 执行顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *判断是否需要执行该过滤器,true为执行
     */

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    /**
     * 执行的具体过滤动作,如登录校验等
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("执行 run");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        //setSendZuulResponse 为false时：表示不对其进行路由，这个请求最终不会被zuul转发到后端服务器,但是如果当前Filter后面还存在其他Filter,那么其他Filter仍然会被调用到
        //因为其他Filter中的shouldFilter()返回的是true，因此我们最好在shouldFilter()中判断sendZuulResponse值，为true时才return true(如直接返回：return RequestContext.getCurrentContext().sendZuulResponse())
        //ctx.setSendZuulResponse(false);
//        if(accessToken == null) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){}
//
//            return null;
//        }
        return null;
    }
}
