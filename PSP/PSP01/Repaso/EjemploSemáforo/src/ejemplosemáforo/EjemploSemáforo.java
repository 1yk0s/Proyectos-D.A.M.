/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplosemáforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author kevin
 */
public class EjemploSemáforo {

    /**
     * @param args the command line arguments
     */
    
    /*
    
        Un semáforo es como un controlador de aforo.
        Si tienes un recurso que solo pueden usar N hilos a la vez (por ejemplo
        una impresora o 3 plazas de aparcamiento), usas un semáforo.
    
        En el siguiente código 5 hilos intentan entrar en un sitio donde solo
        caben 2.
    
    */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Solo permitimos 2 hilos a la vez (permisos)
        
        final Semaphore parking = new Semaphore(2);
        
        /*
            Un semáforo es como un contador de permisos disponibles.
            En este caso el parking tiene 2 plazas, por lo que el semáforo
            empieza con 2 permisos.
        */
        
        
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("Coche " + id + " intentando aparcar...");
                        parking.acquire(); //Pide permiso(si no hay, espera)
                        System.out.println(">>> Coche " + id + "HA APARCADO");
                        Thread.sleep(2000); //Se queda un rato
                        System.out.println("<<< Coche " + id + " sale del parking");
                        parking.release(); //Libera el permiso para otro 
                        
                    } catch (InterruptedException e) {
                        /*
                            Lanza InterruptedException porque mientras el hilo
                            está esperando a que haya un permiso disponible, otro hilo
                            podría interrumpirlo.
                        */
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

/*

    1. acquire(): Es el wait() del semáforo.
        Si el contador es > 0, resta uno y pasa.
        Si es 0, el hilo se queda esperando.

    2. release(): Es el signal().
        Suma uno al contador y avisa a los que están esperando.

    3. Diferencia con synchronized: el synchronized es como un semáforo
        de una sola plaza (Semáforo Binario).
        El Semaphore te permite configurar cuántos hilos entran.

*/