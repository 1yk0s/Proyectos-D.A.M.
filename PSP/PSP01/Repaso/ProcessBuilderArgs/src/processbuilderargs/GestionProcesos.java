/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processbuilderargs;

import java.io.IOException;

/**
 *
 * @author kevin
 */
public class GestionProcesos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {


        //1. Se lanza el proceso hijo (Notepad)
        
        System.out.println("Lanzando Notepad...");
        ProcessBuilder lanzador = new ProcessBuilder("notepad.exe");
        Process procesoHijo = lanzador.start();
        
        //2. Pausamos el programa Java 5 segundos (para que lo vea abierto)
        
        System.out.println("Esperando 5 segundos antes de cerrarlo ...");
        Thread.sleep(5000);
        
        //3. Mato el proceso de forma "limpia" desde Java
        
        procesoHijo.destroy(); //O mejor aún, usando comandos del sistema.
        
        System.out.println("Enviando orden de cierre forzoso ... ");
        ProcessBuilder killer = new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe");
        killer.start();
        
        System.out.println("¡Proceso eliminado!");
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
}
