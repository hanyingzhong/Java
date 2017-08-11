package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Created by destinym on 15/11/23.
 */

@ChannelHandler.Sharable
public class SharedHander extends ChannelInboundHandlerAdapter {

	private static final InternalLogger logger = InternalLoggerFactory.getInstance(SharedHander.class);
	private final AttributeKey<String> clientKey  = AttributeKey.valueOf("client");
	private final AttributeKey<String> clientKey2 = AttributeKey.valueOf("clientAddress");

	@SuppressWarnings("deprecation")
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		Channel ch = ctx.channel();
		
		//use channel's attr
		Attribute<String> attr2 = ch.attr(clientKey2);
		//use ctx's attr,but deprecated
		Attribute<String> attr  = ctx.attr(clientKey);
		
		if(ch.hasAttr(clientKey2) == true){
			System.out.println("channel AttributeKey clientAddress");
			attr2.setIfAbsent(ctx.channel().remoteAddress().toString());
		}

		if(attr.get() == null)
		{			
			attr.set(ctx.channel().remoteAddress().toString());
		}
		System.out.println(attr.get());		
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

	// @Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

		// System.out.println("s=="+msg);
		// logger.info("receive===="+msg);
		// logger.info("receive" +ctx.channel());
		// Attribute<String> attr = ctx.attr(clientKey);
		// //Channel ch = ctx.channel();
		//
		// String s = (String)msg;
		// if (s.contains("firstMessage")) {
		// client = s;
		// attr.set(s);
		//
		// } else {
		// logger.info("client" + client + " receive:"+ s);
		// logger.info("client-key:" + attr.get()+" receive:"+s);
		//
		// }

	}
}
