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
public class ResponseDto {
    private String codAutoriz;
    private String montoCompra;
    private String numRecibo;
    private String rrn;
    private String terminalId;
    private String fechaTransac;
    private String horaTransac;
    private String codRespuesta;
    private String tipoCuenta;
    private String numCuotas;
    private String ultFourDigits;
    private String msgError;
    private String binTarjeta;
}
