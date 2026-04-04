/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciounoutuno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author kevin
 */
public class Ejercicio1UT1 {
    public static void main(String[] args) {
        
        /*
            Se obtiene la instancia única del entorno de ejecución
            de la JVM.
            A través de ella, es posible interactuar con el sistema operativo.
        */
        
        Runtime r = Runtime.getRuntime();
        
        /*
            Se define el comando que lanzará el proceso hijo
            "java -jar" indica que se ejecuta un fichero .jar
        */
        String comando = "java -jar CreacionDeUnProcesoTres.jar";
        
        /*
            Se declara el proceso como null para poder usarlo
            fuera de bloques try-catch de forma segura
        */
        Process p = null;
        
        
        /*
        
            BLOQUE 1: LANZAR EL PROCESO Y CAPTURAR SALIDA NORMAL
        
        */
        
        try {
            
            /*
                Se lanza el proceso hijo.
                A partir de aquí, CreacionDeUnProcesoTres se ejecutará
                en paralelo.
            */
            
            p = r.exec(comando);
            
            /*
            
                Se obtiene el canal de la salida normal del proceso hijo.
                Lo que el hijo escribe con System.out.println(),
                se recibe aquí como bytes.
            
            */
            
            InputStream is = p.getInputStream();
            
            /*
            
                Se envuelve el stream en capas para poder leer
                el texto línea a línea cómodamente.
                InputStream -> bytes crudos.
                InputStreamReader -> convierte bytes a caracteres
                BufferedReader -> permite usar readLine()
            
            */
            
            BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
            
            //Variable donde guardaré cada línea leída.
            
            String linea;
            
            /*
                
                Se lee línea a línea hasta que el método readLine()
                devuelva null.
                Esto indica que el proceso hijo no tiene más salida.
            
            */
            
            while ((linea = br.readLine()) != null) {
                
                /*
                    Se imprime cada línea con prefijo para identificarla
                    como salida normal del proceso hijo.
                */
                
                System.out.println("SALIDA > " + linea);
                
            }
            
            /*
                
                Se cierra el BufferedReader y toda la cadena de streams
                para liberar recursos del sistema operativo.
            
            */
            
            br.close();
            
            
        } catch (Exception e) {
            
            /*
                
                Se captura cualquier error al lanzar el proceso
                o al leer su salida (IOException, etc.)
            
            */
            
            e.printStackTrace();
        }
        
        
        /*
        
            BLOQUE 2: CAPTURAR SALIDA DE ERROR DEL PROCESO HIJO
        
        */
        
        try {
            
            /*
            
                Se obtiene el canal de error del proceso hijo.
                Lo que el hijo escribe con System.err.println(),
                o los errores que produzca CMD, llegan aquí:
            
            */
            
            InputStream er = p.getErrorStream();
            
            /*
            
                Igual que antes, se envuelven en capas
                para poder leer el error línea a línea.
            
            */
            
            BufferedReader brer = new BufferedReader(
                    new InputStreamReader(er));
            
            
            //Variable donde guardar cada línea de error leída
            
            String lineaError;
            
            /*
            
                Se lee las líneas de error hasta que no haya más.
            
            */
            
            while ((lineaError = brer.readLine()) != null) {
                
                /*
                    
                    Se imprime con prefijo "ERROR >" para distinguirlas
                    visualmente de la salida normal en consola.
                
                */
                
                System.out.println("ERROR > " + lineaError);
                
                //Se cierra el stream de errores y se liberan recursos.
                
                brer.close();
                
                
            }
            
        } catch (IOException ioe) {
            
            /*
            
                Se captura específicamente IOException,
                que puede ocurrir al leer el stream de error.
            
            */
            
            ioe.printStackTrace();
        }
        
        
        
        /*
        
            BLOQUE 3: ESPERAR AL PROCESO Y COMPROBAR SU RESULTADO
        
        */
        
        /*
        
            Se declara la variable del código de salida fuera del try-catch
            en caso de necesitar usarla después.
        
        */
        
        int exitVal;
        
        try {
            
            /*
            
                waitFor() pausa el programa hasta que el proceso hijo
                termine completamente, y devuelve su código de salida:
                0 = el proceso terminó correctamente
                1 = el proceso terminó con algún error.
            
            */
            
            exitVal = p.waitFor();
            
            //Se muestra el código de salida para comprobar el estado
            System.out.println("Valor de salida: " + exitVal);
            
        } catch (InterruptedException e) {
            
            /*
            
                InterruptedException ocurre si otro hijo interrumpe
                la espera antes de que el proceso hijo termine
            
            */
            
            e.printStackTrace();
            
        }
    }
}
