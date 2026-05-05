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
public class HiloRunnable implements Runnable {
    
    //Clase que implementa Runnable
    public void run() {
        //Se redefine run() con el código asociado al hilo
        for (int i = 1; i <= 5; i++) {
            System.out.println(" Hilo_Runnable");
        }
    }
}
