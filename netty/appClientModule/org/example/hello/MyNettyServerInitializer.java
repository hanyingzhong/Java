package org.example.hello;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyNettyServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		// 以("\n")为结尾分割的 解码器
		//pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

		// 字符串解码 和 编码
		pipeline.addLast("decoder", new NettyDecoder());   //StringDecoder
		pipeline.addLast("encoder", new NettyEncoder());  //StringEncoder

		// 自己的逻辑Handler
		pipeline.addLast("handler", new MyNettyServerHandler());
	}
}
