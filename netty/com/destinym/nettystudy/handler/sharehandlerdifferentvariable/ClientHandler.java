package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(ClientHandler.class);
    private ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public ClientHandler(int clientId, String deviceType) {
        String message ="firstMessage"+deviceType + clientId;
        firstMessage = Unpooled.buffer(1024);
        firstMessage.writeBytes(message.getBytes());
        logger.info("first Message" );
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws InterruptedException {

        ctx.writeAndFlush(firstMessage);
        logger.info("active flush" + firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


}

