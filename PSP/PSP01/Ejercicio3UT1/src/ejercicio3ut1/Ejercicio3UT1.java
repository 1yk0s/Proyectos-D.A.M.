/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3ut1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author kevin
 */
public class Ejercicio3UT1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        /*
        
            Crear un programa que lance Ejercicio2UT1 como proceso hijo,
            pasándole un nombre como argumento.
        
        */
        
        
        //Se obtiene el entorno de ejecución de la JVM
        Runtime r = Runtime.getRuntime();
        
        /*
        
            Se define el nombre al que le pasaré por argumento
            al proceso hijo
        
        */
        
        String nombre = "Juan";
        
       
        
        /*
        
            Se declara el proceso como null para usarlo
            fuera de los bloques try-catch de forma segura
        
        */
        
        
        Process p = null;
        
        /*
        
        Obtengo automáticalmente el directorio donde están los
        archivos .class compilados por NetBeans obteniendo 
        mediante System.getProperty("user.dir") el directorio actual
        y sumándole la ruta de ubicaciónd ne las clases.
        
        */
        
        String directorioClases = "E:\\D.A.M\\ProyectosNetBeans\\PSP\\PSP01\\" +
                                    "\\Ejercicio2UT1\\build\\classes\\";
        
        
                /*
        
                    Se construye el comando completo con el nombre como argumento
                    -cp . indica que busque las clases en el directorio actual.
                    Se construye el comando con la ruta absoluta para que Java
                    encuentre Ejercicio3UT1.class
        
                */
        
        String comando = "java -cp " + directorioClases + 
                " ejercicio2ut1.Ejercicio2UT1 " + nombre;
        
        //Se muestra el comando para verificar quee es correcto
        
        System.out.println("Comando: " + comando);
        
        
            /*
            
                BLOQUE 1: LANZAR EL PROCESO Y CAPTURAR SALIDA NORMAL
            
            */
            
        
        try {
            
            /*
            
                Se lanza Ejercicio2UT1 como proceso hijo.
                a partir de ahí se ejecuta en paralelo.
            
            */
            
            p = r.exec(comando);
            
            /*
            
                Se obtiene el canal de salida normal del proceso hijo
                lo que Ejercicio2UT1 escribe con System.out.println()
                se recibe aquí:
            
            */
            
            /*
            
                Se espera a que el proceso hijo termine ANTES
                de leer sus streames, para garantizar que toda
                la salida está disponible.
            
            */
            
            p.waitFor();
            
            //Se lee la salida normal
            
            InputStream is = p.getInputStream();
            
            /*
            
                Se envuelve en capas para leer línea a línea:
                InputStream -> bytes crudos
                InputStreamReader -> convierte bytes a caracteres
                BufferedReader -> permite usar readLine()
            
            */
            
            BufferedReader br = new BufferedReader(
                new InputStreamReader(is));
            
            //Variable donde se guarda cada línea leída.
            String linea;
            
            /*
            
                Se lee la salida del hijo línea a línea
                hasta que no haya más (readLine() devuelve null)
            
            */
            
            while((linea = br.readLine()) != null) {
                
                //Se muestra la salida del proceso hijo
                System.out.println("SALIDA > " + linea);
                
            }
            
            //Se cierra el stream y se liberan recursos
            br.close();
            
        } catch (Exception e) {
            
            /*
            
                Capturo cualquier error al lanzar el proceso
                o al leer su salida.
            
            */
            
            e.printStackTrace();
            
        }
        
        
        /*
        
            BLOQUE 2: CAPTURAR LA SALIDA DEL PROCESO HIJO
        
        */
        
        try {
            
            /*
            
                Se obtiene el canal de error del proceso hijo
                Si Ejercicio2UT1 produce algún error, llegará aquí:
            
            */
            
            InputStream er = p.getErrorStream();
            
            /*
            
                Igual que antes, se envuelve en capas para leer
                los errores línea a línea.
            
            */
            
            BufferedReader brer = new BufferedReader(
                new InputStreamReader(er));
            
            //Variable que guarda cada línea de error.
            
            String lineaError;
            
            //Se leen los errores hasta que no hay más.
            
            while((lineaError = brer.readLine()) != null) {
                
                //Se muestra el error con prefijo para identificarlo.
                
                System.out.println("ERROR > " + lineaError);
                
            }
            
            //Se cierra el stream del error y se liberan recursos
            
            brer.close();
            
        } catch (IOException ioe) {
            
            /*
            
                Se captura específicamente IOException al
                leer el stream de error
            
            */
            
            ioe.printStackTrace();
            
        }
        
        
        /*
        
        
            BLOQUE 3: ESPERAR AL PROCESO Y COMPROBAR SU RESULTADO
        
        
        */
        
        int exitVal;
        
        try {
            
            /*
            
                Espero a que EJercicio2UT1 termine completamente
                y obtengo su código de salida:
                0 = terminó correctamente.
                1 = terminó con error.
            
            */
            
            //.exitValue() simplemente recoge el código que ya está disponible.
            exitVal = p.exitValue();
            
            
            //Se muestra el código de salida
            
            System.out.println("Valor de salida: " + exitVal);
            
            
            
        } catch (IllegalThreadStateException e) {
            
            //Ocurre si el proceso aún no ha terminado.
            
            e.printStackTrace();
            
        }
    }
}
