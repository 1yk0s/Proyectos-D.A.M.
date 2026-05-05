/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiloextendido;

/**
 *
 * @author kevin
 */
public class Main {
    
    public static void main(String[] args) {
        
        //Creo 2 hilos del tipo HiloThread con 2 constructores diferentes
        Thread hilo1 = new HiloThread("Kevin");
        Thread hilo2 = new HiloThread();
        
        //Creo un hilo Runnable en un paso
        Thread hilo3 = new Thread(new HiloRunnable());
        
        //Se pone en marcha los 3 hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
