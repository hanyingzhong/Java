package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by destiny.m on 15/11/23.
 */
public class ServerMain {
	static final int PORT = Integer.parseInt(System.getProperty("port", "8009"));

	public static void main(String[] args) throws Exception {

		// Configure Server
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			final SharedHander sharedHandler = new SharedHander();
			b.group(bossGroup, workerGroup).option(ChannelOption.SO_BACKLOG, 100).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						public void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline p = socketChannel.pipeline();
							p.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
							p.addLast(sharedHandler);
						}

					});

			ChannelFuture f = b.bind(PORT).sync();

			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
}
