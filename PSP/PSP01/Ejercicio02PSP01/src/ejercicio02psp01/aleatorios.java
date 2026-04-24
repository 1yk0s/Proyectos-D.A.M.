/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio02psp01;

import java.util.Random;

/**
 *
 * @author kevin
 */
public class aleatorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        
            Implementa una aplicación llamada 'aleatorios', que genere al menos
            40 números aleatorios (entre 0 y 100), y que los escriba en su salida
            estándar.
        
        */
        
        //PASO 1: CREAR EL GENERADOR DE NÚMEROS ALEATORIOS
        
        /*
            Random es la clase de Java que permite generar
            números aleatorios de forma sencilla.
        */
        Random random = new Random();
        
        //PASO 2: DEFINIR CUÁNTOS NÚMEROS GENERAR
        
        /*
            El enunciado pide AL MENOS 40 números.
            Definimos la cantidad como variable para poder cambiarla
            fácilmente si fuera necesario
        */
        
        int cantidad = 40;
        
        
        //PASO 3: GENERAR Y MOSTRAR LOS NÚMEROS
        
        System.out.println("=== NÚMEROS ALEATORIOS GENERADOS ===");
        
        for (int i = 1; i <= cantidad; i++) {
            
            /*
                random.nextInt(101) genera un número aleatorio
                entre 0 y 100 ambos inclusive:
                nextInt(n) genera entre 0 y n-1
                nextInt(101) genera entre 0 y 100 
            */
            
            int numero = random.nextInt(101);
            
            /*
                Muestro cada número por la salida estándar
                con su posición para identificarlo
            */
            
            System.out.println("Número " + i + ": " + numero);
        }
        
        System.out.println("=======================================");
        System.out.println("Total de números generados: " + cantidad);
    }
}
