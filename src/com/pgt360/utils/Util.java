/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Home
 */
public class Util {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes){
        byte[] hexChars = new byte[bytes.length * 2];
        for(int j = 0; j < bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = (byte) HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = (byte) HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
    public static String hex2a(String hexx){
        String result = new String();
        char[] charArray = hexx.toCharArray();
        for(int i = 0; i<charArray.length; i=i+2){
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
        return result;
    }
}
