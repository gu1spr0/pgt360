/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.utils;

import com.pgt360.config.NettyServerHandler;
import com.pgt360.dto.ChannelDto;
import com.pgt360.repository.ChannelRepository;
import io.netty.channel.ChannelId;
import java.nio.ByteBuffer;

/**
 *
 * @author Home
 */
public class CommunicationPos {
    private static ChannelRepository channelRepository = new ChannelRepository();
    public String sendAck(){
        String msg = "06";
        return msg;
    }
    public String sendConnectionChip(){
        String msg = "02001736303030303030303030313030303030300323";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendConnectionCtl(){
        String msg = "02001736303030303030303030313030363030300325";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendTransRevNo(){
        String msg = "02002436303030303030303030313030303030311C3438000258580303";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendTipoTarjetaCtl(){
        String msg = "02001736303030303030303030313030363030310324";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendSolicitudCierre(){
        String msg = "02001736303030303030303030313030313030300322";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendSolicitudAnulacion(){
        String msg = "02001736303030303030303030313030353030300326";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendConfirmarAnulacion(){
        String msg = "02002436303030303030303030313030353030321C3438000230300305";
        SendMessageToPOS(msg);
        return msg;
    }
    public String sendSolicitudInicializar(ChannelId channelId){
        String msg = "02001736303030303030303030313030323030300321";
        System.out.println("Inicializando Pos: "+msg);
        SendMessageToPOS(msg);
        ChannelDto channelDto = channelRepository.get(channelId);
        channelDto.setFlujo("");
        return msg;
    }  
    public void SendMessageToPOS(String msg){
        byte[] bytes = msg.getBytes();
        String hex = Util.bytesToHex(bytes);
        ByteBuffer buffer = ByteBuffer.wrap(hex.getBytes());
        System.out.println("Mensaje a enviar al POS: "+buffer);
        NettyServerHandler.sendMessage(msg);
    }
   
}
