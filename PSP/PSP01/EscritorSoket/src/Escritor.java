

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1yk0s
 */
public class Escritor {
    
    public static void main(String[] args) {
        
        //Iniciar la creación del canal de comunicación
        
        ServerSocket conexion = null; //Socket para aceptar conexiones
        Socket canal = null; //Socket que establece el canal de comunicación.
        PrintWriter streamSalida = null; //PrintWriter a partir del canal de comunicación. canal.getOutputStream())
        try {
            conexion = new ServerSocket(12345);
            /*
                Solicitamos al Sistema Operativo que abra un puerto de escucha
                de conexiones.
                El número del puerto es 12345.
            */
        } catch (IOException ex) {
            System.err.println("No se ha podido abrir el puerto de escucha");
            System.err.println(ex.toString());
        }
        
        //Esperar la conexión del lector del canal
        //Mandar el mensaje al proceso lector
            
        try {
            System.out.println("Proceso escritor, esperando " + 
                    "la conexión del proceso lector ...");
            canal = conexion.accept();
            /*
            Se espera hasta que se produzca la conexión al puerto.
            El método ServerSocket.accept(); bloquea (hace dormir)
            el proceso hasta que se produce una conexión.
             */
            streamSalida = new PrintWriter(canal.getOutputStream());
            /*
            Creo un objeto PrintWriter a partir del stream de salida
            del socket o del canal de comunicación.
            El objeto PrintWriter, me permitirá utilizar los métodos
            print() y write() para mandar datos al proceso que está
            escuchando al otro lado del canal.
            */
            System.out.println("Conexión establecida, mandando datos " +
                    "al proceso lector ....");
            
            for(int i = 0; i < 10; i++) {
                streamSalida.println(i); //Mandamos del 0 al 9
                streamSalida.flush(); //Forzamos que mande cada número
            }
            System.out.println("Comunicación finalizada");
        } catch (Exception ex) {
            System.err.println("No se ha podido establecer la conexión, " +
                    "o no ha ocurrido un fallo al escribir en el canal.");
            System.err.print(ex.toString());
        } finally {
            /*
            Me aseguro de que se cierren los recursos
            que estoy utilizando
            */
            if (streamSalida != null) { //PrintWriter
                streamSalida.close(); // Su cierre no genera excepciones
            }

            if (canal != null) { //Socket
                try {
                    canal.close();
                } catch (IOException ex) {
                    System.err.println("Error al cerrar el socket");
                    System.err.print(ex.toString());
                }
            }
            
            if (conexion != null) { //ServerSocket
                try {
                    conexion.close();
                } catch (IOException ex) {
                    System.err.println("Error al cerrar ServerSocket");
                    System.err.print(ex.toString());
                }
            }
        }
    }
}

/**
 * Se está pidiendo al Sistema operativo si podemos escuchar conexiones de 
 * otros procesos por el puerto número 12345.
 * Si no lo está utilizando otro proceso, nos lo concederá.
 * Recordemos que es recomendable que utilicemos los puertos entre 6000 y 65535.
 */