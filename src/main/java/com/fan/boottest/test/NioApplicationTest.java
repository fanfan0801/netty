package com.fan.boottest.test;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NioApplicationTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NioApplicationTest.class, args);
    }

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void run(String... args) throws Exception {
        // 开启服务
        ChannelFuture future = nettyServer.start("localhost", 7070);
        future.channel().closeFuture().sync();
    }
}