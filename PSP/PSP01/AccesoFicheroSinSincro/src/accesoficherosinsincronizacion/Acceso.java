/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoficherosinsincronizacion;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author 1yk0s
 */
public class Acceso {
    
    public static void main(String[] args) {
        
        int orden = 0;  //Número de orden de creación del proceso.
        String osName; //Nombre del sistema operativo.
        String nombreFichero = null;    //Nombre del fichero que se utiliza
        File archivo = null; //Acceso al fichero
        FileWriter escribir = null; //Abre la puerta al archivo para escribir 
        PrintWriter pw = null; //envuelve FileWriter para proporcionar métodos como println().
        FileReader leer = null; //Abre flujo de caracteres hacia el archivo
        BufferedReader br = null; //Acumula datos en una parte de la RAM(buffer)
        int valor = 0; //
        
       //Se comrpueba si estoy recibiendo argumentos en la línea de comandos
       if(args.length > 0) {
           orden = Integer.parseInt(args[0]);
           //Número de orden de creación del proceso.
       }
       
       try {
           PrintStream ps = new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream(
                        new File("javalog.txt"), true)), true);
       } catch (Exception e) {
           System.err.println("P"+orden+" No he podido redirigir salidas.");
       }
       
       /*
        Identifico el sistema operativo para poder acceder por su ruta
        al fichero de forma correcta.
       */
       osName = System.getProperty("os.name");
       if(osName.toUpperCase().contains("WIN")) { //Windows
           if(args.length > 1) {
               nombreFichero = args[1].replace("\\", "\\\\");
               //Se ha recibido la ruta del fichero en la línea de comandos
           } else { 
               nombreFichero = "E:\\1YK0SAMAR0K\\D.A.M\\ProyectosNetBeans\\PSP\\PSP01\\AccesoFicheroSinSincro\\valor.txt";
               //Fichero que se utilizará por defecto.
           }
       } else { //GNU Linux
           if(args.length > 1) {
               nombreFichero = args[1];
               //Se ha recibido la ruta del fichero en la línea de comandos.
           } else {
               nombreFichero = "/Volumes/1YK0SAMAR0K/D.A.M/ProyectosNetBeans/PSP/PSP01/AccesoFicheroSinSincro/valor.txt";
               //Fichero que se utilizará por defecto.
           }
       }
       
       archivo = new File(nombreFichero);
       //Preparo el acceso al fichero
       if (!archivo.exists()) {
           //Si NO existe el fichero
           try {
               archivo.createNewFile(); //Se crea el archivo.
               escribir = new FileWriter(nombreFichero);
               pw = new PrintWriter(escribir);
               pw.println(String.valueOf(0)); //Escribo el valor 0 en el fichero
               System.out.println("Proceso" + orden + ": Creando el fichero.");
           } catch (Exception e) {
               System.err.println("P"+orden+" Error al crear el fichero");
           } finally {
               try {
                   if(escribir != null) {
                       escribir.close();
                   }
               } catch (Exception e2) {
                   System.err.println("Error al errar el fichero");
                   System.exit(1); //Si hay error, finaliza.
               }
           }
       }
       //Se lee el fichero
       try {
           leer = new FileReader(archivo);
           br = new BufferedReader(leer);
           //Lectura del fichero.
           String linea;
           linea = br.readLine();
           valor = Integer.parseInt(linea);
           System.out.println("Proceso"+ orden + 
                   ": Valor leído del fichero: " + valor);
       } catch (Exception e) {
           System.err.println("P"+orden+" Error al leer del fichero");
       } finally {
           /*
            En el finally cierro el escribir, para asegurarme
            que se cierra tanto si todo va bien como si salta
            alguna excepción.
           */
           try {
               if (leer != null) {
                   leer.close();
               }
           } catch (Exception e2) {
               System.err.println("P"+orden+" Error al cerrar el fichero");
               System.exit(1); //Si hay error, finaliza.
           }
       }
       
       //Incremento
       valor++;
       
       //Escritura en fichero
       try {
           escribir = new FileWriter(nombreFichero);
           pw = new PrintWriter(escribir);
           pw.println(String.valueOf(valor));
           System.out.println("Proceso"+ orden +
                   ": Valor escrito en el fichero: " + valor);
       } catch (Exception e) {
           System.err.println("P"+orden+" Error al escribir en el fichero");
       } finally {
           try {
               //Me aseguro de que se cierra el fichero
               if( escribir != null) {
                   escribir.close();
               }
           } catch (Exception e2) {
               System.err.println("P"+orden+" Error al cerrar el fichero");
               System.exit(1); //Si hay error, finalizo.
           }
       }
    }
}
