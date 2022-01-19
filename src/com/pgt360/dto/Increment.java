/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.dto;

import lombok.Data;

/**
 *
 * @author Home
 */
@Data
public class Increment {
    private int number;
    private int step;
    public int getData(){
        return this.number = this.number + step;
    }
}
