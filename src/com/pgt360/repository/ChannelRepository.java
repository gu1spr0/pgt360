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
    private final ConcurrentMap<ChannelId, ChannelDto> channelCache;

    public ChannelRepository() {
        this.channelCache = new ConcurrentHashMap<>();
    }
    public void put(ChannelId channelId, ChannelDto channelDto){
        channelCache.put(channelId, channelDto);
    }
    public ChannelDto get(ChannelId channelId){
        return channelCache.get(channelId);
    }
    public void remove(ChannelId channelId){
        this.channelCache.remove(channelId);
    }
    public int size(){
        return this.channelCache.size();
    }
    public ConcurrentMap<ChannelId, ChannelDto> getCurrentMap(){
        return this.channelCache;
    }
}
