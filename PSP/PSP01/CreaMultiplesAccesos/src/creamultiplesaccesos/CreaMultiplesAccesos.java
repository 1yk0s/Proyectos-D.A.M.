/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creamultiplesaccesos;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

/**
 *
 * @author kevin
 */
public class CreaMultiplesAccesos {
    public static void main(String[] args) throws FileNotFoundException {
        Process nuevoProceso = null;
        String nombreFichero = null;
        File archivo = null;
        RandomAccessFile raf = null;
        
        /*
            Identifico el sistema operativo para poder acceder por su ruta
            al fichero de forma correcta
        */
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) {
            if (args.length > 0) {
                nombreFichero = args[0].replace("\\", "\\\\");
                //He recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "E:\\1YK0SAMAR0K\\D.A.M\\ProyectosNetBeans\\PSP\\PSP01\\AccesoFicheroConSincro\\valor.txt"; //Windows
                // Fichero que se utilizará por defecto
            }
        } else { //GNU/Linux
            if (args.length > 0) {
                nombreFichero = args[0];
                //He recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "/Volumes/1YK0SAMAR0K/D.A.M/ProyectosNetBeans/PSP/PSP01/AccesoFicheroSinSincro/valor.txt"; //GNU/Linux
            }
        }
        
        try {
            //Redirijo la salida estándar y de error a un fichero
            PrintStream ps = new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File("javalog.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
            
        } catch (Exception e) {
            System.err.println("Error al redigir las salidas");
            System.err.println(e.toString());
        }
        
        archivo = new File(nombreFichero);
        //Preparo el acceso al fichero
        if (!archivo.exists()) {
            //Si no existe el fichero
            try {
                archivo.createNewFile(); //Lo creo
                raf = new RandomAccessFile(archivo, "rw"); //Abro el fichero
                raf.writeInt(0); //Escribo el valor inicial 0
                System.out.println("Creado el fichero.");
                
            } catch (Exception e) {
                System.err.println("Error al crear el fichero.");
                System.err.println(e.toString());
            } finally {
                try {
                    if (raf != null) {
                        raf.close();
                    }
                } catch (Exception e2) {
                    System.err.println("Error al cerrar fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, finalizo.
                }
            }
        }
        
        //Creo un grupo de procesos que accederán al mismo fichero
        
        try {
            for(int i = 0; i <= 25; i++) {
                nuevoProceso = Runtime.getRuntime().exec("java -jar " +
                        "AccesoDeMultipleFichero.jar " + i + " " + args[0]);
                /*
                    Creo el nuevo proceso y le indico el número de orden y
                    el fichero que debe utilizar
                */
                System.out.println("Creado el proceso " + i);
                //Muestro en consola que he creado otro proceso
            }
            
        } catch (SecurityException ex) {
            System.err.println("Ha ocurrido un error de Seguridad." +
                    "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.err.println("Ha ocurrido un error, descripción: " +
                    ex.toString());
        }
    }
}