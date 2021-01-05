/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author zhoujp
 * @version NettyMessageEncoder.java, v 0.1 2020-12-31 11:59 zhoujp
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
        out.add(ObjectConvertUtil.request(msg));
    }
}