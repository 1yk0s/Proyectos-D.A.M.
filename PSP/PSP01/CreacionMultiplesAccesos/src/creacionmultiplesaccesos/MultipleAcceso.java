/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacionmultiplesaccesos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author 1yk0s
 */
public class MultipleAcceso {
    public static void main(String[] args) {
        Process nuevoProceso; //Se define una variable de tipo Process
        
        try {
            PrintStream ps = new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File("javalog.txt"), true)), true);
            System.setOut(ps); //Sustituyo todas las salidas estándar
            System.setErr(ps); //Sustituyo todas las salidas de error estándar
            
            for(int i = 0; i <= 20; i++) {
                nuevoProceso = Runtime.getRuntime().exec("java -jar "+
                        "AccesoMultiple.jar " + " " +  i + " nuevo.txt");
                /*
                    Creo el nuevo proceso y le indico el número de orden y
                    el fichero que debe utilizar.
                */
                
                System.out.println("Creado el proceso " + i);
                //Muestro en consola que he creado otro proceso.
                
                /*
                    PEQUEÑA PAUSA:
                    Evita el pantallazo azul de memoria de vídeo al no saturar
                    la creación de procesos en el kernel.
                */
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e3) {
                    System.err.println("Error a al parar Fork Bomb");
                    System.err.println(e3.toString());
                }

            }
            
        } catch (SecurityException ex) {
            System.err.println("Ha ocurrido un error de seguridad" +
                    "No se ha podido crear el proceso por falta de permisos");
        } catch (Exception ex) {
            System.err.println("Ha ocurrido un error, descripción: " +
                    ex.toString());
        }
    }
}
