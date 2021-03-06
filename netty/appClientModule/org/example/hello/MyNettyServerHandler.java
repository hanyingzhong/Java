package org.example.hello;

import java.net.InetAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyNettyServerHandler extends SimpleChannelInboundHandler<Command> {
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception { // (2)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
		}
		channels.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception { // (3)
		Channel incoming = ctx.channel();
		for (Channel channel : channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
		}
		channels.remove(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Command msg) throws Exception {
		// 收到消息直接打印输出
		String body = new String(msg.getBody());
		System.out.println(ctx.channel().remoteAddress() + " Say : " + msg.getType() + body);

		String line = "Received your message !";
		byte[] resBody = line.getBytes();
        Command cmd = new Command(Command.GET_SERVER_LIST_RESPONSE, resBody);
		
        Channel incoming = ctx.channel();
		// 返回客户端消息 - 我已经接收到了你的消息
        incoming.writeAndFlush(cmd);
	}

	/*
	 * 
	 * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
	 * 
	 * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");

		ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

		super.channelActive(ctx);
	}
}
