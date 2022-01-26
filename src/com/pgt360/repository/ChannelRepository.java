/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.repository;

import com.pgt360.dto.ChannelDto;
import io.netty.channel.ChannelId;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Home
 */
public class ChannelRepository {
    private ConcurrentMap<ChannelId, ChannelDto> channelCache = new ConcurrentHashMap<>();
    public void put(ChannelId key, ChannelDto channelDto){
        channelCache.put(key, channelDto);
    }
    public ChannelDto get(ChannelId key){
        return channelCache.get(key);
    }
    public void remove(ChannelId key){
        this.channelCache.remove(key);
    }
    public int size(){
        return this.channelCache.size();
    }
}
