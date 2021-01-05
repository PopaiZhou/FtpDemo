/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package server.ftp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.SecureModel;

/**
 * @author zhoujp
 * @version SecureServerHandler.java, v 0.1 2020-12-31 11:59 zhoujp
 */
public class SecureServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SecureServerHandler.class);
    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof SecureModel) {
            SecureModel secureModel = (SecureModel) msg;
            if (secureModel.getToken() != null) {
                //TODO  验证 token 是否存在，并且token对应的 ip和 ctx里面来源ip是否一致
                if (true) {
                    log.info("NEW TCP >" + ctx.channel().remoteAddress());
                    log.info("now connection count >" + channels.size());

                    channels.add(ctx.channel());
                    secureModel.setAutoSuccess(true);
                    ctx.writeAndFlush(secureModel);
                    return;
                }
            }
            secureModel.setAutoSuccess(false);
            ctx.writeAndFlush(secureModel);
            ctx.close();
        } else {
            if (!channels.contains(ctx.channel())) {
                SecureModel secureModel = new SecureModel();
                secureModel.setAutoSuccess(false);
                ctx.writeAndFlush(secureModel);
                ctx.close();
            } else {
                //继续执行
                ctx.fireChannelRead(msg);
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}