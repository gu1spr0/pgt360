/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.utils;

import com.pgt360.dto.ResponseDto;
import com.pgt360.dto.VentaDto;

/**
 *
 * @author Home
 */
public class ProcessPos {
    public VentaDto respuestaHostVenta(String respHost){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodAutoriz(respHost.substring(50,12));
        responseDto.setMontoCompra(respHost.substring(72, 24));
        responseDto.setNumRecibo(respHost.substring(106, 12));
        responseDto.setRrn(respHost.substring(128, 24));
        responseDto.setTerminalId(respHost.substring(162,16));
        responseDto.setFechaTransac(respHost.substring(188, 8));
        responseDto.setHoraTransac(respHost.substring(206,8));
        responseDto.setCodRespuesta(respHost.substring(224, 4));
        responseDto.setTipoCuenta(respHost.substring(238,4));
        responseDto.setNumCuotas(respHost.substring(252, 4));
        responseDto.setUltFourDigits(respHost.substring(266, 8));
        responseDto.setMsgError(respHost.substring(284, 138));
        responseDto.setBinTarjeta(respHost.substring(432,12));
        
        VentaDto ventaDto = new VentaDto();
        ventaDto.setCodAutoriz(Util.hex2a(responseDto.getCodAutoriz()));
        ventaDto.setMontoCompra(Double.parseDouble(Util.hex2a(responseDto.getMontoCompra())));
        ventaDto.setNumRecibo(Util.hex2a(responseDto.getNumRecibo()));
        ventaDto.setRrn(Util.hex2a(responseDto.getRrn()));
        ventaDto.setTerminalId(Integer.parseInt(Util.hex2a(responseDto.getTerminalId())));
        ventaDto.setFechaTransac(Util.hex2a(responseDto.getFechaTransac()));
        ventaDto.setHoraTransac(Util.hex2a(responseDto.getHoraTransac()));
        ventaDto.setCodRespuesta(Integer.parseInt(Util.hex2a(responseDto.getCodRespuesta())));
        ventaDto.setTipoCuenta(Util.hex2a(responseDto.getTipoCuenta()));
        ventaDto.setNumCuotas(Integer.parseInt(Util.hex2a(responseDto.getNumCuotas())));
        ventaDto.setUltFourDigits(Integer.parseInt(Util.hex2a(responseDto.getUltFourDigits())));
        ventaDto.setMsgError(Util.hex2a(responseDto.getMsgError()));
        ventaDto.setBinTarjeta(Integer.parseInt(Util.hex2a(responseDto.getBinTarjeta())));
        return ventaDto;
    }
    public VentaDto respuestaHosInicializacion(String respHost){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodRespuesta(respHost.substring(50,4));
        VentaDto ventaDto = new VentaDto();
        ventaDto.setCodRespuesta(Integer.parseInt(Util.hex2a(responseDto.getCodRespuesta())));
        return ventaDto;
    }
}
