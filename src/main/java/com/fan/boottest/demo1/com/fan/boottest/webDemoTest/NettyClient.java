package com.fan.boottest.demo1.com.fan.boottest.webDemoTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    private static final EventLoopGroup group = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 自定义处理程序
                    socketChannel.pipeline().addLast(new ClientHandler());
                }
            });
            // 绑定端口并同步等待
            ChannelFuture channelFuture = bootstrap.connect("localhost", 7070).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
