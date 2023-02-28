package com.donggl.common.netty.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloWorldServerHandler
 * @Description TODO
 * @Author donggl
 * @Date 2023/2/27 21:16
 * @Version 1.0
 */
@Component
@ChannelHandler.Sharable
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServerHandler.class);

    // 服务器端读取到 客户端发送过来的数据，然后通过 Channel 回写数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info(String.format("服务器端读取到从客户端:%s 发送过来的数据：%s", ctx.channel().remoteAddress(), msg.toString()));
        ctx.channel().writeAndFlush(String.format("server write:%s", msg));
    }

    // 捕获到异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
