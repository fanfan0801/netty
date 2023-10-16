package com.fan.boottest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * netty服务端
 */
@Component
public class NettyServer {

    //开启两个线程组
    private final EventLoopGroup boosGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    public ChannelFuture start(String host,int port) throws Exception {
        ChannelFuture channelFuture = null;
        try {
        ServerBootstrap bootstrap = new ServerBootstrap();//创建服务器的启动对象 配置参数
        bootstrap.group(boosGroup, workGroup)//设置两个线程组
                .channel(NioServerSocketChannel.class)//作为服务器的通道实现
                .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动的连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象
                    //给pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        //绑定一个端口并且 同步，生成了一个ChannelFuture对象
        channelFuture = bootstrap.bind(host, port).sync();
        //对关闭通道进行监听
        channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

        return channelFuture;
    }

}
