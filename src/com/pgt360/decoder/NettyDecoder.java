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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author Home
 */
public class NettyDecoder extends ByteToMessageDecoder{
    private final Charset charset = Charset.forName("UTF-8");
    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        String message = "";
        System.out.println("Decodificando mensaje");
        if(bb.readableBytes()<1){
            return;
        }
        String s = bb.readCharSequence(bb.readableBytes(),Charset.forName("utf-8")).toString();
        list.add(s);
    }
    
}
