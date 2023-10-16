package com.fan.boottest.dubboRPC.provider;

import com.fan.boottest.dubboRPC.netty.NettyServer;

public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",7000);
    }
}
