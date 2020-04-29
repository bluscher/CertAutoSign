/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.experian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Properties;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author e10934a
 */
public class CertAutoSIgn {

    /**
     * @param args the command line arguments
     */
  
    /*-ubicacion del archivo para local test y desde carpeta del instalable*/
    private static final String PATHSYSTEM = System.getProperty("user.dir");
  
    private static final Logger log = Logger.getLogger(CertAutoSIgn.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
         //Gestion archivo de propiedad
        Properties prop = new Properties(System.getProperties());
        try {
           FileInputStream propFile = new FileInputStream(PATHSYSTEM + File.separator + "conf" + File.separator + "system.properties");
            try {
              prop.load(propFile);
              log.info("Carga system.properties [OK]");
            } catch (IOException ex) {
                log.error("No se puede arbir el archivo de propiedades.", ex);
            }
                
        } catch (FileNotFoundException ex) {
            log.error("No se encuentra el archivo de propiedades {system.properites}.", ex);
        }
       
        
    
        //Recupero Informacion del archivo de propiedad
        String alias = prop.getProperty("alias");
        String bodykeystore = "CN="+prop.getProperty("CN")+",O="
                +prop.getProperty("O")+",OU="
                +prop.getProperty("OU")+",L="
                +prop.getProperty("L")+",ST="
                +prop.getProperty("ST")+",C="
                +prop.getProperty("C");
        
        Certificado cert = new Certificado();
        
        /* ##Test generacion 1##
        //Creo nuevo keystore con certificado inyectado
        cert.cargarKeystoreNEW(alias, bodykeystore);
        //Creo solo el certificado
        cert.saveCertAutoSign(bodykeystore);*/
        
        cert.cargarKeystoreNEW2(alias, bodykeystore);
        
        pressAnyKeyToContinue();
}
   
    //metodo para pausar ventana esperando interaccion con usuario
    static public void pressAnyKeyToContinue()
      { 
          String seguir;
         
          System.out.println("Pulsar una tecla para continuar...");
          Scanner teclado = new Scanner(System.in);
          seguir = teclado.nextLine();
             
     }
    
}
