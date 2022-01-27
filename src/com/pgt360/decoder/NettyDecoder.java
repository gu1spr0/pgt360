/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.decoder;

import com.pgt360.dto.FlowQueryDto;
import com.pgt360.dto.VentaDto;
import com.pgt360.utils.ProcessPos;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author Home
 */
public class NettyDecoder extends ByteToMessageDecoder{
    public ProcessPos processPos = new ProcessPos();
    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf bb, List<Object> list) throws Exception {
        String message = "";
        System.out.println("Decodificando mensaje:"+bb.readableBytes());
        if(bb.readableBytes()<1){
            return;
        } else if(bb.readableBytes() == 1){
            //String s = bb.readCharSequence(bb.readableBytes(),Charset.forName("utf-8")).toString();
            //list.add(s);
            String text = bb.toString(Charset.defaultCharset());   // (3)
            System.out.println("El mensaje recibido del POS es:"+text);
        } else{
            /*ByteBuf buf = (ByteBuf)bb;
            String text = buf.toString(Charset.defaultCharset());
            VentaDto ventaDto = processPos.respuestaHosInicializacion(text);
            System.out.println("CodigoRespuesta:"+ventaDto.getCodRespuesta());*/
            byte[] bytes = new byte[1024];
            for(int i = 0; i < bb.readableBytes();i++){
                bytes[i] = bb.getByte(i);
            }
            FlowQueryDto vFlowQueryDto = SerializationUtils.deserialize(bytes);
            System.out.println(vFlowQueryDto);
        }
        
        /*int strLen = bb.readInt();
        String s = bb.readCharSequence(strLen, charset).toString();
        list.add(s);*/
        /*String result = "";
        Channel incoming = chc.channel();
        ByteBuf buf = (ByteBuf)bb;
        String text = buf.toString(Charset.defaultCharset());
        System.out.println("MESSAGE["+incoming.id()+"]"+buf.readableBytes());
        for(int i = 0; i < buf.readableBytes();i++){
            byte b = buf.getByte(i);
            char c = (char)(b);
            result = result + c;
        }
        list.add(result);*/
        //ByteBuf buf =(ByteBuf)msg;    // (2)
        /*String text = bb.toString(Charset.defaultCharset());   // (3)
        System.out.println("El mensaje decodificado es:"+text);
        list.add(text);*/
    }
    
}
