/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author zhoujp
 * @version NettyMessageDecoder.java, v 0.1 2020-12-31 11:58 zhoujp
 */
public class NettyMessageDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg,
                          List<Object> out) throws Exception {
        Object outobj = ObjectConvertUtil.convertModle(msg);
        out.add(outobj);
    }
}