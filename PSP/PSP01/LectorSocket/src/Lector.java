
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Lector {
    public static void main(String[] args) {
        
        Socket canal = null; //Socket para establecer el canal de conexión con Escritor
        BufferedReader entrada = null; //BufferedReader para el stream de lectura
        String valorEntrada = null; //Valores que se van leyendo del canal.
            
        try {
            canal = new Socket("localhost", 12345);
            /*
                Se pide establecer una conexión en el equipo local
                con el puerto 12345 donde debe de estar escuchando
                el proceso Escritor.
            */
            
        } catch(Exception ex) {
            System.err.println("No se ha podido establecer la conexión.");
            System.err.print(ex.toString());
        }
        
        /**
         * Se está pidiendo al sistema operativo si podemos conectarnos a otro
         * proceso que debe estar escuchando por el puerto número 12345.
         * Si no está escuchando ningún proceso, nos contestará que la conexión
         * ha sido rehusada.
         * Recordemos que el número de puerto tiene que coincidir con el número
         * de puerto utlizado por el proceso Escritor.
         */
        
        try {
            entrada = new BufferedReader (new InputStreamReader(canal.getInputStream()));
            /*
                Se obtiene el objeto que representa el stream de entrada en el canal
                lector con buffer, para no perder ningún dato.
            */
            while ((valorEntrada = entrada.readLine()) != null) {
                //Mientras que haya datos que leer
                System.out.println(valorEntrada);
                System.out.println("**");
            }
        } catch (Exception ex) {
            System.err.println("No se ha podido establecer la conexión");
            System.err.println(ex.toString());
        } finally {
            //Me aseguro de que se cierren los recursos que estoy utilizando
            if (entrada != null) { //BufferedReader
                try {
                    entrada.close();
                } catch (IOException ex) {
                    System.err.println("Se ha producido un error al cerrar el InputStreamReader");
                    System.err.println(ex.toString());
                }
            }
            
            if (canal != null) { // Socket
                try {
                    canal.close();
                } catch (IOException ex) {
                    System.err.println("Se ha producido un error al cerrar el Socket");
                    System.err.println(ex.toString());
                }
            }
            
        }
        
    }
    
}
