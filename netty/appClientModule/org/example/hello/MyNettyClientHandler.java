package org.example.hello;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyNettyClientHandler extends SimpleChannelInboundHandler<Command>{
	
	protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
		String body = new String(msg.getBody());
		
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg.getType() + "-" + body);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client active ");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Client close ");
		super.channelInactive(ctx);
	}
}
