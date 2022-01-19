/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Home
 */
public class FileProperties {
    private Properties properties;
    
    public Properties getConfiguration() throws IOException{
        String pathFile = System.getProperty("user.dir")+File.separator+"configuracion"+File.separator+"atributosIniciales.properties";
        File file = new File(pathFile);
        this.properties = new Properties();
        this.properties.load(new FileInputStream(file));
        System.out.println("::::::::::::::::>Se ubicó correctamente el archivo de propiedades...");
        System.out.println("host:"+this.properties.getProperty("host"));
        System.out.println("port:"+this.properties.getProperty("port"));
        return this.properties;
    }
}
