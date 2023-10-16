package com.fan.boottest.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public static List<Channel> channels = new ArrayList<>();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是：" + buf.toString(CharsetUtil.UTF_8));
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("你好客户端",CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Date date = new Date(); SimpleDateFormat dateFormat= new
                SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Channel inChannel = ctx.channel();
        channels.add(inChannel);
        System.out.println("[Server]:"+inChannel.remoteAddress().toString().substring(1)+"上线");
        System.out.println("上线时间为:"+dateFormat.format(date));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Date date = new Date(); SimpleDateFormat dateFormat= new
                SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Channel inChannel=ctx.channel();
        channels.remove(inChannel);
        System.out.println("[Server]:"+inChannel.remoteAddress().toString().substring(1)+"离线");
        System.out.println("离线时间为："+dateFormat.format(date));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
