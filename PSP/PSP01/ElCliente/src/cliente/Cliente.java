/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

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
public class Cliente {
    public static void main(String[] args) {
        String nombreFichero = "";
        File archivo = null;
        RandomAccessFile raf = null;
        FileLock bloqueo = null;
        int valor = 0;
        
        
        /*
            Compruebo si estoy recibiendo argumentos en la línea de comandos
            if (args.length > 0) {
                orden = Integer.parseInt(args[0]);
            }
            Número de orden de creación de este proceso.
        */
        
        try {
            //Se redirige la salida y error estándar a un fichero.
            PrintStream ps = new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File("javalog_cliente.txt"), true)), true);
            System.setOut(ps);
            System.setErr(ps);
        } catch (Exception e) {
            System.err.println("Cliente. No he podido redirigir salidas");
        }
        
        /*
            Identifico el sistema operativo para poder acceder por su ruta
            al fichero de forma correcta.
        */
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().contains("WIN")) { //Windows
            if (args.length > 0) {
                nombreFichero = args[0].replace("\\", "\\\\");
                //Se ha recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "E:\\D.A.M.\\ProyectosNetBeans\\PSP\\PSP01\\ElCliente\\buffer.txt";
                //Fichero que utilizará por defecto
            }
        } else { //GNU/Linux
            if (args.length > 0) {
                nombreFichero = args[0];
                //Se ha recibido la ruta del fichero en la línea de comandos
            } else {
                nombreFichero = "/Volumes/1YK0SAMAR0K/D.A.M/ProyectosNetBeans/PSP/PSP01/ElCliente/dist/buffer.txt";
            }
        }
        
        //Preparamos el acceso al fichero
        
        archivo = new File(nombreFichero);
        int i = 0;
        while (valor < 9) { //Leeremos 10 datos
            
            try {
                
                raf = new RandomAccessFile(archivo, "rwd"); //Abro el fichero.
                
                //***************
                //Sección crítica
                
                bloqueo = raf.getChannel().lock();
                
                /*
                    Bloqueo el canal de acceso al fichero.
                    Obtenemos el objeto que representa el bloqueo para después
                    poder liberarlo.
                */
                
                System.out.println("Cliente: ENTRA sección");
                
                //Lectura del fichero
                
                if (raf.length() != 0) {
                    valor = raf.readInt(); //Leemos el valor
                    raf.setLength(0); //vaciar el fichero
                    System.out.println("Cliente: valor escrito " + valor);
                    
                } else {
                    System.out.println("Cliente: no puede leer.");
                }
                
                System.out.println("Cliente: SALE sección");
                bloqueo.release(); //Libero el bloqueo del canal del fichero
                bloqueo = null;
                
                //Fin de la sección crítica
                //****************************
                
                Thread.sleep(1000); //Simulo el tiempo de creación del dato
                
            } catch (Exception e) {
                System.err.println("Cliente. Error al acceder al fichero. ");
                System.err.println(e.toString());
            } finally {
                try {
                    if (bloqueo != null) {
                        bloqueo.release();
                    }
                    if (raf != null) {
                        raf.close();
                    }
                } catch (Exception e2) {
                    System.err.println("Cliente. Error al cerrar el fichero");
                    System.err.println(e2.toString());
                    System.exit(1); //Si hay error, finalizo
                }
            }
            
        }
        
    }
    
}
