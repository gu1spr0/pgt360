/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import static java.lang.System.in;
import java.util.List;

/**
 *
 * @author Home
 */
public class NettyDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        System.out.println("DATA:"+bb.toString());
        if(bb.readableBytes()<4){
            return;
        }
        
        int length = in.read();
        System.out.println("length:" + length);
    }
    
}
