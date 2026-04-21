/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contadorcorrecto;

/**
 *
 * @author kevin
 */
public class ProblemaCritico {
    
    public static void main(String[] args) throws InterruptedException {
        
        ContadorCorrecto c = new ContadorCorrecto(); //Variable que suma 100000
        Thread h1 = new Thread(new Runnable() {
          
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    //<--- El candado:
                    synchronized(c) {
                        c.cuenta++;
                    }
                }
            }
            
        });
        
        Thread h2 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    //<---- El candado:
                    synchronized(c) {
                        c.cuenta++;
                    }
                    
                }
            }
        });
        
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        
        System.out.println("Resultado final con sincronización: " + c.cuenta);
    }
    
}
