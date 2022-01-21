/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.config;

import com.pgt360.exception.ExceptionPayment;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
        System.out.print("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ! ID:"+incoming.id()+"\n");
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        channels.remove(incoming);
        System.out.print("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECTÓ ID:"+incoming.id()+"\n");
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        ByteBuf buf = (ByteBuf)msg;
        String text = buf.toString(Charset.defaultCharset());
        System.out.println("MESSAGE["+incoming.id()+"]"+buf.readableBytes());
        for(int i = 0; i < buf.readableBytes();i++){
            byte b = buf.getByte(i);
            char c = (char)(b);
            System.out.println("Data:"+c);
        }
        //System.out.println("Data:"+msg);
        //ctx.write(msg);
        //String s = buf.readCharSequence(buf., Charset.forName("utf-8")).toString();
        // actual length of received packet
        /*for (int i = 0; i < buf.capacity(); i++) {
            byte b = buf.getByte(i);
            System.out.print("-->"+(char)b);
        }*/
        /*byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println("EXITO:"+new String(bytes));*/
        /*String s = "";
        if(!buf.hasArray()){
            int length = buf.readableBytes();
            byte[] array = new byte[length];
            buf.getBytes(buf.readerIndex(), array);
            s = new String(array, StandardCharsets.UTF_8);
            System.out.println("Resultado: "+s);
        }
        incoming.write(s);*/
        
        /*String s = buf.readCharSequence(buf.readInt(), Charset.forName("utf-8")).toString();
        System.out.println(s);*/
        /*for(Channel channel : channels){
            if(channel != incoming){
                channel.write("["+incoming.remoteAddress()+"]"+msg+"\n");
            }
        }*/
        /*System.out.println("Llegando datos...");
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
        buf.release(); // (6)*/
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel()); // (7)
        if(ctx.channel().isWritable()){
            //ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Israel", CharsetUtil.UTF_8));
            
            //ctx.writeAndFlush(Unpooled.copiedBuffer("06", CharsetUtil.UTF_8));
            
            /*StringBuffer sb = new StringBuffer();
            char ch[] = "02001736303030303030303030313030303030300323".toCharArray();
            for(int i=0;i<ch.length;i++){
                String hexString = Integer.toHexString(ch[i]);
                sb.append(hexString);
            }
            String result = sb.toString();*/
            String    HEXES    = "0123456789ABCDEF";
            byte[] data = "02001736303030303030303030313030303030300323".getBytes();
            StringBuilder hex = new StringBuilder(2 * data.length);
            for (final byte b : data) {
                hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
            }
            /*Scanner sc = new Scanner(System.in);
            String entrada = sc.next();*/
            System.out.println("Enviando mensajes");
            System.out.println("Resultado:"+hex);
            ctx.writeAndFlush(Unpooled.copiedBuffer(data));
            System.out.println("Mensaje enviado");
            
            //ctx.writeAndFlush(Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
            
            
        }else{
            System.out.println("No se puedo enviar mensaje");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel()); // (8)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
