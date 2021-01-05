/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import server.model.RequestFile;
import util.NettyMessageDecoder;
import util.NettyMessageEncoder;

/**
 * @author zhoujp
 * @version FtpClientServer.java, v 0.1 2020-12-31 11:37 zhoujp
 */
public class FtpClientServer {
    public void connect(int port, String host, final RequestFile echoFile) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ch.pipeline().addLast(new ObjectEncoder());
                    //最大长度
                    ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
                    //设置服务器端的编码和解码
                    ch.pipeline().addLast(new NettyMessageDecoder());
                    ch.pipeline().addLast(new NettyMessageEncoder());
                    ch.pipeline().addLast(new FileTransferClientHandler(echoFile));
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}