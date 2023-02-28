package com.donggl.common.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName HelloWorldClient
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:09
 * @Version 1.0
 */
@Component
public class HelloWorldClient {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));
    private final EventLoopGroup group = new NioEventLoopGroup();
    private ChannelFuture mChannelFuture = null;
    private final ThreadLocal<Channel> mChannel = new ThreadLocal<>();

    @Resource
    private HelloWorldClientHandler helloWorldClientHandler;

    public void startClient(String host, int port) {
        // Configure the client.
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(helloWorldClientHandler);
                        }
                    });
            mChannelFuture = b.connect(host, port).addListener(future -> {
                logger.info(String.format("客户端启动成功，并监听端口：%s ", port));
            });
        } catch (Exception e) {
            logger.error("启动 netty 客户端出现异常", e);
        }
    }

    /**
     * 客户端通过 Channel 对象向服务器端发送数据
     * @param data 文本数据
     */
    public void send(String data) {
        try {
            if (mChannel.get() == null) {
                mChannel.set(mChannelFuture.channel());
            }
            mChannel.get().writeAndFlush(data);
        } catch (Exception e) {
            logger.error(this.getClass().getName().concat(".send has error"), e);
        }
    }


    // 客户端启动，并连上服务器端
    @PostConstruct
    public void init() {
        ForkJoinPool.commonPool().submit(() -> startClient("127.0.0.1", 19080));
    }

    @PreDestroy
    public void destroy() {
        group.shutdownGracefully();
    }


}
