/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 *
 * @author Home
 */
public class NettyInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel c) throws Exception {
        ChannelPipeline pipeline = c.pipeline();
        //pipeline.addLast(new DelimiterBasedFrameDecoder(1024*1024, Delimiters.lineDelimiter()));
        /*pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(nettyServerHandler);*/
        //pipeline.addLast(new NettyDecoder());
        pipeline.addLast(new NettyServerHandler());
    }
    
}
