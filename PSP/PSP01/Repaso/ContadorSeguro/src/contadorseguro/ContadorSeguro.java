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

/*

    En lugar de poner el "candado" manualmente dentro del hilo,
    lo más profesional en Java es que el propio objeto sepa protegerse.
    Voy a escribir la clase Contador para que sea ella quien
    gestione la seguridad

*/



public class ContadorSeguro {

    /**
     * @param args the command line arguments
     */
    
    
    private int cuenta = 0; //Al añadir 'syncronized' al método, Java crea un "Monitor"
    
    //Solo un hilo puede ejecutar este método a la vez para este objeto
    public synchronized void incrementar() {
        cuenta++;
    }
    
    public int getCuenta() {
        return cuenta;
    }
}
