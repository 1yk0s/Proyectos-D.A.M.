/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mihilo;

/**
 *
 * @author kevin
 */
public class PruebaHilos {
    
    public static void main(String[] args) {
        
        MiHilo hilo1 = new MiHilo("A");
        MiHilo hilo2 = new MiHilo("B");
        
        System.out.println("Lanzando hilos....");
        hilo1.start(); //IMPORTANTE usar start(), no run().
        hilo2.start(); 
        
        System.out.println("El hilo principal (Main) ha terminado de lanzar todo");
    }
}


/*


1. extends Thread: Es la forma clásica de crear hilos en Java.
2. run(): Aquí escribo lo que quiero que haga el hilo.
    Es el "cuerpo" del proceso ligero.

3. start(): ! Ojo aquí: Si llamo a run(), el código se ejecuta de forma normal
    (secuencial).
    Si llamo a start(), Java le pide al Sistema Operativo que cree un hilo
    nuevo en la CPU.

4. Concurrencia: Verás que los mensajes de A y B se mezclan.
    No llevan un orden fijo porque el SO decide cuál ejecutar en cada milisegundo.


*/