/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearhilothread;

/**
 *
 * @author kevin
 */
public class CrearHiloThread extends Thread {

    /**
     * @param args the command line arguments
     */

    //Clase que extiende a Thread
    @Override
    public void run() {
    //Se redefine el método run() con el código asociado al hilo
        System.out.println("¡Saludo desde un hilo extendiendo thread!");
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic
        
        CrearHiloThread hilo1 = new CrearHiloThread();
        //Se crea un objeto Thread, el hilo hilo1
        
        hilo1.start();
        //invoca a start() y pone en marcha el hilo hilo1
        
    }
    
}
