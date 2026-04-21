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

/*

    Voy a crear dos hilos que "compitan" por escribir en la consola.
    Esto servirá para ver cómo el PLanificador (Scheduler) del
    SO decide a quién le toca usar la CPU.

*/



public class MiHilo extends Thread {
    
    private String nombre;
    
    
    public MiHilo(String nombre) {
        this.nombre = nombre;
    }
    
    
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            
            System.out.println("Hilo " + nombre + " trabajando... paso " + i);
            
            try {
                //Fuerzo una pequeña pausa para que el SO cambie de hilo 
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("--- Hilo " + nombre + " TERMINADO --- ");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


/*


1. El Main no espera:
    El mensaje "El hilo principal (Main) ha terminado..."
    salió antes que el trabajo de los hilos.
    Eso es porque el main lanza los hilos y sigue su camino; no se queda bloqueado.

2. Entrelazado:
    Los hilos A y B se han ido turnando (B1, A1, B2, A2...).
    Esto es el Planificador del Sistema Operativo haciendo un Round - Robin
    dándole un poquito de tiempo de CPU a cada uno.


*/