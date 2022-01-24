/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.config;

import com.pgt360.dto.ChannelDto;
import com.pgt360.exception.ExceptionPayment;
import com.pgt360.repository.ChannelRepository;
import com.pgt360.utils.CommunicationPos;
import com.pgt360.utils.FlowProcess;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.charset.Charset;

/**
 *
 * @author Home
 */
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter{
    //private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static ChannelRepository channelRepository;
    public static  ChannelHandlerContext ctx;
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        this.ctx = ctx;
        System.out.print("[SERVER]-"+incoming.remoteAddress()+" SE CONECTÓ! ID:"+incoming.id()+"\n");
        
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws ExceptionPayment{
        Channel incoming = ctx.channel();
        this.ctx = null;
        System.out.print("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECTÓ ID:"+incoming.id()+"\n");
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws ExceptionPayment{
        /*String result = "";
        Channel incoming = ctx.channel();
        ByteBuf buf = (ByteBuf)msg;
        String text = buf.toString(Charset.defaultCharset());
        System.out.println(">>>>>>>"+text);
        System.out.println("MESSAGE["+incoming.id()+"]"+buf.readableBytes());
        for(int i = 0; i < buf.readableBytes();i++){
            byte b = buf.getByte(i);
            char c = (char)(b);
            result = result + c;
            
        }
        System.out.print(">>>>>>>"+result);*/
        //ByteBuf buf =(ByteBuf)msg;    // (2)
        //String text = buf.toString(Charset.defaultCharset());   // (3)
        System.out.println("El mensaje leido es:"+msg);
        //buf.release(); // (4)
        int flujo = channelRepository.get(ctx.channel().id()).getNumericFlujo();
        for(ChannelId channelId : channelRepository.getCurrentMap().keySet()){
            System.out.println("ID:"+channelId);
        }
        if(channelRepository.get(ctx.channel().id()).getNumericFlujo() == 1){
            CommunicationPos communicationPos = new CommunicationPos();
            communicationPos.sendSolicitudInicializar(ctx.channel().id());
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        ChannelDto channelDto = new ChannelDto();
        channelDto.setChannel(incoming);
        channelDto.setIdChannel(incoming.id());
        channelDto.setFlujo("inicializar");
        channelDto.setNumericFlujo(1);
        channelDto.setStep(1);
        channelRepository.put(incoming.id(), channelDto);
        //channels.add(ctx.channel()); // (7)
        //if(ctx.channel().isWritable()){
            //ctx.writeAndFlush(Unpooled.copiedBuffer("Hello Israel", CharsetUtil.UTF_8));
            
            //ctx.writeAndFlush(Unpooled.copiedBuffer("06", CharsetUtil.UTF_8));
            
            /*StringBuffer sb = new StringBuffer();
            char ch[] = "02001736303030303030303030313030303030300323".toCharArray();
            for(int i=0;i<ch.length;i++){
                String hexString = Integer.toHexString(ch[i]);
                sb.append(hexString);
            }
            String result = sb.toString();*/
            /*String    HEXES    = "0123456789ABCDEF";
            byte[] data = "02001736303030303030303030313030303030300323".getBytes();
            StringBuilder hex = new StringBuilder(2 * data.length);
            for (final byte b : data) {
                hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
            }*/
            /*Scanner sc = new Scanner(System.in);
            String entrada = sc.next();*/
            //System.out.println("Enviando mensajes");
            //System.out.println("Resultado:"+hex);
            //ctx.writeAndFlush(Unpooled.copiedBuffer(data));
            //sendMessage("02001736303030303030303030313030303030300323");
            //System.out.println("Mensaje enviado");
            
            //ctx.writeAndFlush(Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
            
            
        //}else{
        //    System.out.println("No se puedo enviar mensaje");
        //}
    }

    /*@Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channelRepository.remove(incoming.id());
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
    
    public static void sendMessage(String msg){  // (4)
        if (ctx == null)
            return;
        /*StringBuffer sb = new StringBuffer();
        char ch[] = msg.toCharArray();
        for(int i=0;i<ch.length;i++){
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }*/
        //String result = sb.toString();
        ByteBuf buf = ctx.alloc().buffer();  // (5)
        buf.writeCharSequence(msg,Charset.defaultCharset());
        ctx.write(buf);
        ctx.flush();
    }
}
