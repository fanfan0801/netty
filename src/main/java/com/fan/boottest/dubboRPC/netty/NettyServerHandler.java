package com.fan.boottest.dubboRPC.netty;

import com.fan.boottest.dubboRPC.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户端发送的消息并调用
        System.out.println("msg="+msg);
        //自定义一个协议
        if(msg.toString().startsWith("as-")){
            String result = new HelloServiceImpl().hello(msg.toString().substring(
                    msg.toString().lastIndexOf("-") + 1));
            ctx.writeAndFlush(result);
        }
    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
