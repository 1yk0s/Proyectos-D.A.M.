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
public class EstadoDeLosHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //1.- CREACIÓN DEL HILO
        HiloAuxiliar hilo1 = new HiloAuxiliar();
        
        /*
            Se crea una instancia de la clase HiloAuxiliar
            (que debe extender 'Thread' o implementar 'Runnable').
            En este momento, el hilo está en estado **NEW**
            El hilo existe como objeto pero aún no se ha iniciado.
        */
        
        //2.- VERIFICACIÓN DEL ESTADO INICIAL
        
        /*
            - getState(): Devuelve el estado actual del hilo 
                          (Será NEW).
            - isAlive(): Devuelve 'false' porque el hilo no ha comenzado
                         ha ejecutarse.
        */
        
        System.out.println("Hilo Auxiliar Nuevo: Estado = " + hilo1.getState() +
                ", ¿Vivo? isAlive()=" + hilo1.isAlive());
        
        //3.- INICIO DEL HILO
        
        /*
            Se llama a .start(), NO a .run().
            Esto hace que el hilo pase del estado 'NEW' al estado 'RUNNABLE'.
            El sistema operativo programará la ejecución del método .run()
            del hilo.
        */
        
        hilo1.start();
        
        //4.- VERIFICACIÓN DESPUÉS DEL INICIO
        
        /*
            Ahora .getState() probablemente devuelva 'RUNNABLE'
            .isAlive() devuelve 'true' porque el hilo está activo.
        */
        
        System.out.println("Hilo Auxiliar Iniciado: Estado = " + 
                hilo1.getState() + ", ¿Vivo? isAlive() =" + hilo1.isAlive() + "\n");
        
        //5.- ESPERA A QUE TERMINE EL HILO
        
        /*
            - .join(): El hilo principal "se bloquea" hasta que 'hilo1'
                       termine completamente.
            - Es como decir: "Espera aquí hasta que el otro hilo termine".
            - Puede lanzar 'InterruptedException' si el hilo es interrumpido
              mientras espera
        */
        
        try {
            hilo1.join();
            //Espera a que el thread hilo1 muera
            
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        
        //6.- VERIFICACIÓN FINAL
        
        /*
            Después del .join(), el hilo ha terminado.
            .getState() devuelve 'TERMINATED'.
            .isAlive() devuelve 'false'.
        */
        
        System.out.println("\n Hilo Auxiliar Muerto: Estado = " + hilo1.getState()
                    + ", ¿Vivo? isAlive() = " + hilo1.isAlive());
        
        //ESTADOS DEL HILO DEMOSTRADOS:
        
        /*
            1.- **NEW** -> Hilo creado pero no iniciado.
            2.- **RUNNABLE** -> Hilo iniciado y ejecutándose
            3.- **TERMINATED** -> Hilo ha terminado su ejecución.
        */
        
        //SALIDA ESPERADA:
        
        /*
            Hilo Auxiliar Nuevo: Estado = NEW ¿Vivo? isAlive() = false
            Hilo Auxiliar Iniciado: Estado = RUNNABLE, ¿Vivo? isAlive() = true
            [Aquí se ejecutaría el contenido del método run() de HiloAuxiliar]
            Hilo Auxiliar Muerto: Estado=TERMINATED, ¿Vivo? isAlive() = false
        */
    }
}
