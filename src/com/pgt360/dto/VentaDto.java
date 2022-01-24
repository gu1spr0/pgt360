/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Home
 */
@Data
public class VentaDto {
    private String codAutoriz;
    private Double montoCompra;
    private String numRecibo;
    private String rrn;
    private int terminalId;
    private String fechaTransac;
    private String horaTransac;
    private int codRespuesta;
    private String tipoCuenta;
    private int numCuotas;
    private int ultFourDigits;
    private String msgError;
    private int binTarjeta;
}
