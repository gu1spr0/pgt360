/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.dto;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import lombok.Data;

/**
 *
 * @author Home
 */
@Data
public class ChannelDto {
    private Channel channel;
    private ChannelId idChannel;
    private String flujo;
    private int numericFlujo;
    private int step;
}
