/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodemultiplefichero;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/**
 *
 * @author kevin
 */
public class AccesoDeMultipleFichero {
    public static void main(String[] args) {
        int orden = 0;
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        int valor = 0;
        
        
        
        //Compruebo si estoy recibiendo argumentos en la línea de comandos
        if (args.length > 0) {
            orden = Integer.parseInt(args[0]);
            //Número de orden de creación de este proceso
            try {
                //Redirijo la salida y error estándar a un fichero
                PrintStream ps = new PrintStream(
                    new BufferedOutputStream(
                        new FileOutputStream(
                            new File("javalog.txt"), true)), true);
            } catch (Exception e) {
                System.err.println("P" + orden + " No he podido redirigir salidas");
            }
        }
        
        /*
            Identifico el sistema operativo para poder acceder por su ruta
            al fichero de forma correcta.
        */
        
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) { //Windows
            if (args.length > 1) {
                nombreFichero = args[1].replace("\\", "\\\\");
                //He recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "E:\\1YK0SAMAR0K\\D.A.M\\ProyectosNetBeans\\PSP\\PSP01\\AccesoFicheroConSincro\\valor.txt"; //Windows
                //Fichero que se utilizará por defecto
            }
        } else { //GNU/Linux
            if (args.length > 1) {
                nombreFichero = args[1];
                //He recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "/Volumes/1YK0SAMAR0K/D.A.M/ProyectosNetBeans/PSP/PSP01/AccesoFicheroSinSincro/valor.txt"; //GNU/Linux
            }
        }
        
        //Preparo el acceso al fichero
        archivo = new File(nombreFichero);
        for (int i = 0; i < 100; i++) {
            try {
                raf = new RandomAccessFile(archivo, "rwd"); //Abro el fichero
                //*************************
                
                //Sección crítica
                bloqueo = raf.getChannel().lock(); //Objeto FileLock
                
                /*
                    Bloqueo el canal de acceso al fichero.
                    Obtengo el objeto que representa el bloqueo para después
                    poder liberarlo.
                */
                System.out.println("Proceso" + orden + ": ENTRA sección");
                
                //Lectura del fichero
                valor = raf.readInt(); //Lee el valor
                valor++; //Incremento.
                raf.seek(0); //Vuelvo a colocarme en el principio del fichero
                raf.writeInt(valor); //Escribo el valor.
                
                System.out.println("Proceso" + orden + ": SALE sección");
                
                bloqueo.release(); //Libero el bloqueo del canal del fichero
                bloqueo = null;
                
                //Fin de sección crítica
                //**********************
                
                System.out.println("Proceso" + orden + ": valor escrito " + valor);
                
            } catch (Exception e) {
                System.err.println("P" + orden + " Error al acceder al fichero");
                System.err.println(e.toString());
            } finally {
                try {
                    if (raf != null) {
                        raf.close();
                    }
                    if (bloqueo != null) {
                        bloqueo.release();
                    }
                } catch (Exception e2) {
                    System.err.println("P" + orden + " Error al cerrar el fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, finalizo.
                }
            }
        }
    }
}