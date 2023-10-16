package com.fan.boottest.netty;
;
import io.netty.channel.ChannelFuture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import javax.annotation.Resource;

//@SpringBootApplication
public class NioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NioApplication.class, args);
    }

    @Resource
    private NettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        // 开启服务
        ChannelFuture future = nettyServer.start("localhost", 7070);
        future.channel().closeFuture().sync();
    }
}