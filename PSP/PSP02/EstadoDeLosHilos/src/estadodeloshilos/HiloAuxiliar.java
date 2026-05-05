/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadodeloshilos;

/**
 *
 * @author kevin
 */
public class HiloAuxiliar extends Thread {
    
    //Código del hilo
    @Override
    public void run() {
        for(int i = 10; i >= 1; i--) {
            System.out.println(i+",");
        } 
    }
}
