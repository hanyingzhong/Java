package com.destinym.nettystudy.handler.sharehandlerdifferentvariable;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.IOException;

/**
 * Created by destinym on 15/11/9.
 */
public class Client implements Runnable {
    static final int PORT = Integer.parseInt(System.getProperty("port", "8009"));
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Client.class);
    Thread t;
    private String HOST;
    private int clientId;
    private String deviceType;

    public Client(int number, String deviceType, String host) {
        clientId = number;
        this.deviceType = deviceType;
        this.HOST = System.getProperty("host", host);
        t = new Thread(this, "" + clientId);
        t.start();
    }

    void startOneClient() throws IOException, InterruptedException {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            //p.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
                            p.addLast(new ClientHandler(clientId, deviceType));
                        }
                    });

            // Start the connection attempt.
            Channel ch = b.connect(HOST, PORT).sync().channel();


            //firstMessage.writeBytes(message.getBytes());
            for (int i = 0; i < 10; i++) {

                String sendMessage = "sendMessage--" + deviceType + clientId;
                ByteBuf firstMessage = Unpooled.buffer(sendMessage.getBytes().length);
                firstMessage.writeBytes(sendMessage.getBytes());
                ch.writeAndFlush(firstMessage);
                logger.info("sendmessage:"+ sendMessage);
                Thread.sleep(5000);
            }
            ch.closeFuture().sync();

        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }

    }

    public void run() {
        try {
            logger.info("run begin");
            startOneClient();
            logger.info("run over");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

