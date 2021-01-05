/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package server.ftp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhoujp
 * @version FtpUploadServer.java, v 0.1 2020-12-30 17:45 zhoujp
 */
public class FtpUploadServer {

    private int port;

    public FtpUploadServer(int port) {
        this.port = port;
    }

    public void startup() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new FileServerInitializer());

            Channel ch = b.bind(port).sync().channel();
            System.out.println("File server started at port " + port + '.');
            ch.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}