/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contador;

/**
 *
 * @author kevin
 */
public class ProblemaCritico {
    
    public static void main(String[] args) throws InterruptedException {
        
        Contador c = new Contador(); //Hilo que suma 1000
        Thread h1 = new Thread(new Runnable() {
            
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    
                     c.cuenta++;
                }
            }
            
        });
        
        //Hilo 2
        Thread h2 = new Thread(new Runnable() {
           
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                 c.cuenta++;
                }
            }
        });
        
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        
        System.out.println("Resultado final: " + c.cuenta);   
    }
}

/*

El experimento:

Se ha subido el bucle a 100.000.
Si ejecuto el código, lo más probable es que el resultado NO sea
200.000, sino algo como 184.232 o 192.110.

Esto pasa porque los hilos se están "pisando al intentar actualziar la misma variable
al mismo tiempo.
Esto se llama Condición de Carrera.

Este es el ejemplo de por qué la programación multihilo es peligrosa si no se controla.


*/