/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suministrador;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.lang.invoke.WrongMethodTypeException;
import java.nio.channels.FileLock;

/**
 *
 * @author kevin
 */
public class ElSuministrador {
    public static void main(String[] args) {
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        
        
        /*
            Compruebo si estoy recibiendo argumentos en la línea de comandos
            if (args.length > 0) {
                orden = Integer.parseInt(args[0]);
            }
            
            Número de orden de creación de este proceso.
        
        */
        
        try {
            
            //Redirijo salida y error estándar a un fichero
            
            PrintStream ps = new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File("javalog_suministrador.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
            
        } catch (Exception e) {
            System.err.println("Suministrador. NO he podido redirigir salidas.");
        }
        
        /*
            Identifico el sistema operativo para poder acceder por su ruta
            al fichero de forma correcta.
        */
        
        String osName = System.getProperty("os.name");
        if(osName.toUpperCase().contains("WIN")) { //Windows
            if(args.length > 0) {
                nombreFichero = args[0].replace("\\", "\\\\");
                //Se ha recibido la ruta  del fichero en la línea de comandos
            } else {
                nombreFichero = "E:\\D.A.M.\\ProyectosNetBeans\\PSP\\PSP01\\ElCliente\\buffer.txt";
                //Fichero que se utilizará por defecto
            }
        } else { //GNU/Linux
            if (args.length > 0) {
                nombreFichero = args[0];
                //Se ha recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "/Volumes/1YK0SAMAR0K/D.A.M/ProyectosNetBeans/PSP/PSP01/ElCliente/dist/buffer.txt";
                //Fichero que se utilizará por defecto
            }
        }
        
        //Se prepara el acceso al fichero
        
        archivo = new File(nombreFichero); 
        
        int i = 0;
        
        while (i < 10) { //Escribiremos 10 datos
            try {
                 raf = new RandomAccessFile(archivo, "rwd"); //Abro el fichero
                 
                 //**************
                 //Sección crítica
                 
                 bloqueo = raf.getChannel().lock();
                 
                 /*
                    Se bloquea el canal de acceso al fichero.
                    Se obtiene el objeto que representa el bloqueo para después
                    poder liberarlo.
                 */
                 
                 System.out.println("Suministrador: ENTRA sección");
                 
                 //Lectura del fichero
                 
                 if (raf.length() == 0) {
                     
                     raf.writeInt(i); //escribimos el valor
                     System.out.println("Suministrador: valor escrito " + i);
                     i++;
                 } else {
                     System.out.println("Suministrador: no puede escribir ");
                 }
                 
                 System.out.println("Suministrador: SALE sección");
                 bloqueo.release(); //Libero el bloqueo del canal del fichero
                 bloqueo = null;
                 
                 //Fin sección crítica
                 //**********************
                 
                 Thread.sleep(500); //Simulo tiempo de creación del dato
                 
                  
            } catch (Exception e) {
                System.err.println("Suministrador. Error al acceder al fichero");
                System.err.println(e.toString());
            } finally {
                try {
                    if(raf != null) {
                        raf.close();
                    }
                    if (bloqueo != null) {
                        bloqueo.release();
                    }
                } catch (Exception e2) {
                    System.err.println("Suministrador. Error al cerrar el fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, se finaliza
                }
            }
        }
    }
}
