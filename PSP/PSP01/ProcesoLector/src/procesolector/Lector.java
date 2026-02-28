/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesolector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author 1yk0s
 */
public class Lector {
    public static void main(String[] args) {
        /*
            Se va a leer de la entrada estándar del proceso y escribir
            los datos que se reciben en la salida estándar del proceso.
        */
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        /*
            Se obtiene el stream de lectura de la entrada estándar.
            Se utiliza un lector con Buffered, para no perder ningún dato.
        */
        String lineaTeclado = null;
        try {
            while((lineaTeclado = bf.readLine()) != null) {
                //Se van leyendo y mostrando datos
                System.out.println(lineaTeclado);
            }
        } catch (IOException ex) {
            System.err.println("Se ha producido un error de E/S.");
            System.err.println(ex.toString());
        }
    }
}
