package com.donggl.common.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName HelloWorldServer
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:14
 * @Version 1.0
 */
@Component
public class HelloWorldServer {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServerHandler.class);
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup();

    @Resource
    private HelloWorldServerHandler helloWorldServerHandler;

    public void startServer(int port) {
        try {
            ServerBootstrap sbs = new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast(helloWorldServerHandler);
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收进来的连接
            sbs.bind(port).addListener(future -> {
                logger.info(String.format("服务器启动成功，并监听端口：%s ", port));
            });
        } catch (Exception e) {
            logger.error("启动 netty 服务器端出现异常", e);
        }
    }

    // 服务器端启动，并绑定 19080 端口
    @PostConstruct
    public void init() {
        ForkJoinPool.commonPool().submit(() -> startServer(19080));
    }

    @PreDestroy
    public void destroy() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}


