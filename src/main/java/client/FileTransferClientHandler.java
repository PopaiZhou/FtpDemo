/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import server.model.RequestFile;
import server.model.ResponseFile;
import server.model.SecureModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author zhoujp
 * @version FileTransferClientHandler.java, v 0.1 2020-12-31 11:42 zhoujp
 */
public class FileTransferClientHandler extends ChannelInboundHandlerAdapter {
    private int byteRead;
    private volatile long start = 0;
    private RandomAccessFile randomAccessFile;
    private RequestFile request;
    private final int minReadBufferSize = 8192;


    public FileTransferClientHandler(RequestFile ef) {
        if (ef.getFile().exists()) {
            if (!ef.getFile().isFile()) {
                System.out.println("Not a file :" + ef.getFile());
                return;
            }
        }
        this.request = ef;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SecureModel secure = new SecureModel();
        secure.setToken("2222222222222");
        ctx.writeAndFlush(secure);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof SecureModel){
            try {
                randomAccessFile = new RandomAccessFile(request.getFile(), "r");
                randomAccessFile.seek(request.getStarPos());
                byte[] bytes = new byte[minReadBufferSize];
                if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                    request.setEndPos(byteRead);
                    request.setBytes(bytes);
                    request.setFileSize(randomAccessFile.length());
                    ctx.writeAndFlush(request);
                } else {
                    System.out.println("文件已经读完");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException i) {
                i.printStackTrace();
            }
            return ;
        }

        if (msg instanceof ResponseFile) {
            ResponseFile response = (ResponseFile)msg;
            System.out.println(response.toString());
            if(response.isEnd()){
                randomAccessFile.close();
            }else{
                start = response.getStart();
                if (start != -1) {
                    randomAccessFile = new RandomAccessFile(request.getFile(), "r");
                    randomAccessFile.seek(start);
                    int a = (int) (randomAccessFile.length() - start);
                    int sendLength = minReadBufferSize;
                    if (a < minReadBufferSize) {
                        sendLength = a;
                    }
                    byte[] bytes = new byte[sendLength];
                    if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
                        request.setEndPos(byteRead);
                        request.setBytes(bytes);
                        try {
                            ctx.writeAndFlush(request);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        randomAccessFile.close();
                        ctx.close();
                    }
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}