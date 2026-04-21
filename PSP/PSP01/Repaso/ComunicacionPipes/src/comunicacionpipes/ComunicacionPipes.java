/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacionpipes;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 *
 * @author kevin
 */
public class ComunicacionPipes {

    /**
     * @param args the command line arguments
     */
    
    /*
        Imagina que un hilo (el Productor) quiere enviarle un mensaje
        a otro hilo (el Consumidor).
        En lugar de usar una variable compartida, uso un flujo de datos.
    */
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //1. Creamos la tubería (entrada y salida)
        final PipedOutputStream salida = new PipedOutputStream();
        final PipedInputStream entrada = new PipedInputStream(salida);
        
        //Hilo Emisor (productor)
        Thread emisor = new Thread(new Runnable() {
            
            public void run() {
                try {
                    String mensaje = "Hola desde el hilo emisor";
                    salida.write(mensaje.getBytes());
                    
                    //Enviamos los bytes
                    salida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        //Hilo Receptor (consumidor)
        Thread receptor = new Thread(new Runnable() {
            public void run() {
                try {
                    int dato;
                    System.out.println("Receptor leyendo: ");
                    while((dato = entrada.read()) != -1) {
                        System.out.print((char) dato);
                        
                        //Se lee letra a letra 
                    }
                    System.out.println();
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        emisor.start();
        receptor.start();
    }
}



/*

1. PipedOutputStream y PipedInputStream: Son como los extremos de un tubo.
    Lo que escribes en uno, sale por el otro.

2. Sincronización automática:
    Lo bueno de las tuberías es que si el Receptor intenta leer y el Emisor
    aún no ha enviado nada, el Receptor se bloquea automáticamente (espera)
    hasta que lleguen datos.
    No es necesario synchronized.

3. Flujos (Streams): Esta es la base de la comunicación entre procesos
    en sistemas tipo Unix/Linux.

*/