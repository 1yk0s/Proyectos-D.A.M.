/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contadorseguro;

/**
 *
 * @author kevin
 */
public class PruebaMonitor {
    
    public static void main(String[] args) throws InterruptedException {
        
        ContadorSeguro c = new ContadorSeguro();
        
        //Hilo 1
        Thread h1 = new Thread(new Runnable() {
            
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    c.incrementar();
                }
            }
        });
        
        //Hilo 2
        Thread h2 = new Thread(new Runnable() {
            
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    c.incrementar();
                }
            }
        });
        
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        
        System.out.println("Resultado con Monitor: " + c.getCuenta());   
    }
}


/*

1. Encapsulamiento: El hilo no tiene que saber cómo se sincronizan
    los datos, solo llama al método incrementar().

2. Seguridad: Evitas errores.
    Si olvidas el synchronized en un hilo pero lo pones en otro,
    podrías tener problemas. SI está en el método del objeto, siempre está protegido.

*/