/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elhiloprincipal;

/**
 *
 * @author kevin
 */
public class ElHiloPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("\n¡Hola mundo!");
        //Imprime ¡Hola mundo! en la salida
        
        Thread miHilo = Thread.currentThread();
        
        /*
            Obtiene el hilo donde se está ejecutando este método
            mediante la función .currentThread() y lo almacena en 
            la variable local miHilo
        */
        
        System.out.println("Por defecto, el hilo que ejecuta el método main() "
            + "de mi programa se llama " + miHilo.getName() + " \n");
        
    }
    
}
