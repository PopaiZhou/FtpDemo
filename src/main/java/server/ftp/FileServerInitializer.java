/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package server.ftp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import util.NettyMessageDecoder;
import util.NettyMessageEncoder;

/**
 * @author zhoujp
 * @version FileServerInitializer.java, v 0.1 2020-12-31 10:36 zhoujp
 */
public class FileServerInitializer extends ChannelInitializer<Channel> {


    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ObjectEncoder());
        // 最大长度
        ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null)));
        //设置服务器端的编码和解码
        ch.pipeline().addLast(new NettyMessageDecoder());
        ch.pipeline().addLast(new NettyMessageEncoder());

        ch.pipeline().addLast(new SecureServerHandler());
        ch.pipeline().addLast(new FileServerHandler());

    }
}