/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio01psp01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kevin
 */

    /*
        Clase que lee números desde teclado, los ordena
        en forma ascendente y los muestra por pantalla
    
    */

public class ordenarNumeros {
    

    /*
    
        Lee líneas desde la entrada a estándar, convierte
        valores a enteros, los ordena con Array.sort(int[])
        y los imprime.
    
    */
    
    public static void main(String[] args) {
        
        
        //PASO 1: PREPARO LA LECTURA DESDE EL TECLADO.
        
        /*
        
            System.in recibe los bytes del teclado.
            InputStreamReader convierte los bytes a caracteres
            BufferedReader permite leer línea a línea con readLine()
        
        */
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        
        
        //PASO 2: PREPARAR LA LISTA DONDE GUARDAR NÚMEROS
        
        /*
        
            ArrayList es una lista dinámica que crece según
            vamos añadiendo números, sin tamaño fijo.
        
        */
        
        ArrayList<Integer> numeros = new ArrayList<>();
        
        //Variable donde se guarda cada línea leída
        
        String linea;
        
        //PASO 3: LEER LOS NÚMEROS INTRODUCIDOS POR TECLADO.
        
        System.out.println("Introduce los números uno a uno");
        System.out.println("Cuando termines pulsa Enter");
        
        try {
            
            /*
            
                readLine() lee una línea cada vez que el usuario
                pulsa Enter.
                El bucle continúa hasta que:
                - readLine() devuelve null (fin de stream)
                - El usuario pulsa Enter sin escribir nada (línea vacía)
            
            */
            
            while ((linea = br.readLine()) != null && !linea.isEmpty()) {
                
                /*
                
                    Se convierte el texto leído a número entero
                    Integer.parseInt() transforma "42" -> 42.
                    Mediante el método .trim() se eliminan los espacios
                    en blanco al inicio y al final de la cadena linea.
                
                */
                
                int numero = Integer.parseInt(linea.trim());
                
                //Se añade el número a la lista.
                numeros.add(numero);
                
                System.out.println("Número añadido: " + numero);
                
            }
            
            
        } catch (NumberFormatException e) {
            
            /*
                Ocurre si el usuario escribe algo que no es 
                un número, como "abc" en lugar de "42"
                Se utiliza el método printStackTrace() para manejar
                las excepciones de manera más efectiva.
            
            */
            
            System.err.println("Error: Introduzca solo números enteros");
            e.printStackTrace();
            
        } catch (Exception e) {
            
            //Se captura cualquier otro error de lectura
            System.err.println("Error al leer los números");
            e.printStackTrace();
            
        }
        
        
        //PASO 4: COMPROBAR QUE SE INTRODUJO ALGÚN NÚMERO
        
        //Si la lista está vacía, no hay nada que ordenar
        if (numeros.isEmpty()) {
            System.out.println("No se han introducido números");
            return; //Termino el programa aquí
        }
        
        //PASO 5: ORDENAR LA LISTA DE NÚMEROS
        
        /*
        
            Collections.sort() ordena la lista de menor a mayor.
            automáticamente sin necesidad de implementar
            el algoritmo de ordenación.
        
        */
        
        Collections.sort(numeros);
        
        /*
            En caso de que solicite la aparición de los números
            introducidos por teclado ordenados de una manera
            aleatoria, cambio el método sort() de la clase Collections
            por el método shuffle().
            
            Collections.suffle(numeros);
        
        */
        
        
        //PASO 6: MOSTRAR EL RESULTADO POR CONSOLA
        
        System.out.println("=== NÚMEROS ORDENADOS ===");

        /*
            Recorro la lista e imprimo cada número ya ordenado
            de menor a mayor.
        */
        for (int numero: numeros) {
            System.out.println(numero);
        }
        
        System.out.println("========================");
        
        //PASO 7: CERRAR EL BUFFEREDREADER
        
        try {
            
            /*
                Cierro el BufferedReader para liberar
                los recursos del sistema operativo
            */
            br.close();
            
        } catch (Exception e) {
            System.err.println("Error al cerrar el lector");
            e.printStackTrace();
        }
    }
}
