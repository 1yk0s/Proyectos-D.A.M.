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
public class HiloThread extends Thread {

    /**
     * @param args the command line arguments
     */
    
    //Clase que extiende a Thread con 2 constructores
    String nombre = "Hilo_derviaThread";
    
    public HiloThread(String nb) {
        //Constructor
        nombre = nb;
    }
    
    public HiloThread() {
        //Constructor 2
    }
    
    @Override
    public void run() {
        //Se redefine run() con el código asociado al hilo
        for (int i = 1; i <= 5; i++) {
            System.out.println(nombre);
        }
    }
}
