/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzador;

import java.io.IOException;

/**
 *
 * @author kevin
 */
public class Lanzador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
            1. Se obtiene el entorno de ejecución Runtime
            entorno = Runtime.getRuntime();
        
        */
        
        Runtime entorno = Runtime.getRuntime();
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("Estás trabajando en: " + os);
        
        try {
            
            /*
            
            2. Se define el comando según el sistema operativo
            
            */
            
            if (os.contains("win")) {
                
                entorno.exec("notepad.exe");
            } else {
                
                entorno.exec("gedit");
                //O el que tengas en Linux (nano, mousepad...)
            }
            
            System.out.println("Proceso lanzado con éxito");
            
        } catch (IOException e) {
            System.err.println("Error al lanzar el proceso: " +
                    e.getMessage());
        }
        
    }
    
}
