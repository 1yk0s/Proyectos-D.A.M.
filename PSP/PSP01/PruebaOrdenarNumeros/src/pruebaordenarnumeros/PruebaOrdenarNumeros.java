/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaordenarnumeros;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kevin
 */
public class PruebaOrdenarNumeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //PASO 1: PREPARAR LA LECTURA DESDE EL TECLADO
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        
        //PASO 2: PREPARAR LISTA PARA ALMACENAR NÚMEROS
        
        ArrayList<Integer> numeros = new ArrayList<>();
        String linea; //Variable para guardar cada línea leída.
        
        //PASO 3: LEER LOS NÚMEROS INTRODUCIDOS POR EL TECLADO
        
        System.out.println("Introduce los números uno a uno");
        System.out.println("Al finalizar, pulsar ENTER");
        
        try {
            while((linea = br.readLine()) != null && !linea.isEmpty()) {
                int numero = Integer.parseInt(linea.trim());
                
                numeros.add(numero); //Se añade el número a la lista.
                System.out.println("Número añadido: " + numero);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Introduce solo números enteros");
            e.printStackTrace();
            
        } catch (Exception e) {
            System.out.println("Error al leer los números");
            e.printStackTrace();
        }
        
        //PASO 4: COMPROBAR QUE SE HAN INTRODUCIDO NÚMEROS
        
        if(numeros.isEmpty()) {
            System.out.println("No se han introducido números");
            return; //Finalizo aquí el programa.
        }
        
        //PASO 5: ORDENAR LA LISTA DE NÚMEROS.
        
        Collections.sort(numeros); //Se ordenan los números
        
        //PASO 6: MOSTRAR LOS NÚMEROS POR CONSOLA
        
        System.out.println("==== NÚMEROS ORDENADOS ====");
        
        for(int numero : numeros) {
            System.out.println(numero); //Se recorre la lista de números ya ordenados
            //Y se muestra por pantalla.
        }
        
        System.out.println("==========================");
        
        //PASO 7: CERRAR EL BUFFEREDREADER
        
        try {
            //Se cierra el BufferedReader
            br.close();
            
        } catch (Exception e) {
            System.err.println("Error al cerrar el lector");
            e.printStackTrace();
        }
    }
}
