package com.fan.boottest.dubboRPC.customer;

import com.fan.boottest.dubboRPC.netty.NettyClient;
import com.fan.boottest.dubboRPC.publicinterface.HelloService;

public class ClientBootstrap {
    public static final String providerName = "as-";

    public static void main(String[] args) throws Exception {
        NettyClient customer = new NettyClient();
        HelloService service = (HelloService) customer.getBean(HelloService.class, providerName);
        //通过代理对象调用服务者提供的服务
        String res = service.hello("你好 dubboRPC");
        System.out.println("调用的结果res= " +res);
    }
}
