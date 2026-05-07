/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dormirelhilo;

/**
 *
 * @author kevin
 */
public class HiloAuxiliar extends Thread {

    /**
     * @param args the command line arguments
     */
    //Esta clase hereda de la clase Thread.
    
    //Marcador local
    JPanelMarcador miMarcador;
    //variable para controlar si dormimos o no al hilo
    boolean duerme;
    
    //Constructor del hilo
    public HiloAuxiliar(boolean d, JPanelMarcador marcador) {
        duerme = d;
        //Almacena el marcador recibido
        miMarcador = marcador;
    }
    
    @Override
    public void run() {
        //Código del hilo
        miMarcador.valor = 0;
        //Anula la cuenta
        miMarcador.repaint();
        //Solicita el repintado para borrar el marcador
        if(duerme) {
            for (int i = 1; i <= 20; i++) {
                //Se incrementa la cuenta
                miMarcador.valor = i;
                //Solicita el repintado
                miMarcador.repaint();
                try {
                    /*
                        Duerme el hilo actual durante una décima de segundo,
                        para que la petición de repintado del marcador
                        sea atendida
                    */
                    this.sleep(100);
                } catch (InterruptedException ex) {
                    
                }
            }
        } else {
            for (int i = 1; i <= 20; i++) {
                //Incrementa la cuenta
                miMarcador.valor = i;
                //Solicita el repintado
                miMarcador.repaint();
            }
        }
    }
}
