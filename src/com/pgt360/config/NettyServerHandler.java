/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.config;

import com.pgt360.exception.ExceptionPayment;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.Charset;

/**
 *
 * @author Home
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter{
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        channels.add(incoming);
        System.out.print("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ!\n");
        for(Channel channel : channels){
            System.out.print("ID: "+channel.id()+"\n");
        }
        ctx.write("06");
        ctx.flush();
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        channels.remove(incoming);
        System.out.print("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECTÓ\n");
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws ExceptionPayment{
        /*Channel incoming = ctx.channel();
        for(Channel channel : channels){
            if(channel != incoming){
                channel.write("["+incoming.remoteAddress()+"]"+msg+"\n");
            }
        }*/
        System.out.println("Llegando datos...");
        ByteBuf buf =(ByteBuf)msg;
        String text = buf.toString(Charset.defaultCharset()); // (3)
        System.out.print(text);
        for (Channel channel : channels){  // (4)
            if (!ctx.channel().equals(channel)) {  // (5)
                ByteBuf bufToSend = ctx.alloc().buffer();
                bufToSend.writeCharSequence(text, Charset.defaultCharset());
                channel.writeAndFlush(bufToSend);
            }
        };
        buf.release(); // (6)
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel()); // (7)
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel()); // (8)
    }
}
