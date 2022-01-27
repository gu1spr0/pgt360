/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.utils;

/**
 *
 * @author Home
 */
public class FlowProcess {
    int paso = 1;
    int tam = 0;
    String ack = "06";
    Double montoBOB = 0.00;
    String reciboTRA = "";
    String respHost = "";
    String respHostJson = "";
    boolean isAck1;
    boolean isAck2;
    boolean isAck3;
    boolean isAck4;
    boolean isAck5;
    CommunicationPos communicationPos;
    
    public FlowProcess(){
        this.isAck1 = false;
        this.isAck2 = false;
        this.isAck3 = false;
        this.isAck4 = false;
        this.isAck5 = false;
        this.communicationPos = new CommunicationPos();
    }
    
    public void flujoInicializar(String strReply){
        String resp = "";
        switch(paso){
            case 1:
                if(this.isAck1 && tam == 40){
                    resp = this.communicationPos.sendAck();
                    resp = this.communicationPos.sendTransRevNo();
                    paso = 2;
                    tam = 0;
                    break;
                } else if(this.isAck(strReply)){
                    this.isAck1 = true;
                    tam = 0;
                    break;
                } else{
                    this.respHost = strReply;
                    break;
                }
            default:
                break;
        }     
    }
    
    public void flujoChip(String strReply){
        String resp = "";
    }
    public boolean isAck(String str){
        if(str.equals(this.ack)){
            return true;
        } else{
            return false;
        }
    }
}
