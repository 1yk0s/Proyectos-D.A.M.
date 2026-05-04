/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearhilorunnable;

/**
 *
 * @author kevin
 */
public class CrearHiloRunnable implements Runnable {

    /**
     * @param args the command line arguments
     */
    
    //Clase que implementa a la clase Runnable
    
    @Override
    public void run() {
        //Se redefine el método run() con el código asociado al hilo
        System.out.println("¡Saludos desde un hilo creado con Runnable");
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        CrearHiloRunnable miRunnable = new CrearHiloRunnable();
        //Se crea objeto CrearHiloRunnable con identificador miRunnable
        
        Thread hilo1 = new Thread(miRunnable);
        /*
            Se crea un objeto Thread (el hilo hilo1) pasando como argumento
            al constructor un objeto CrearHiloRunnable
        */
        
        hilo1.start();
        
        //Se invoca al método start del hilo hilo1.
    }
    
}
