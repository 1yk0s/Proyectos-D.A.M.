/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listarprocesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author kevin
 */
public class ListarProcesos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*

            En lugar de solo abrir algo, se pide al sistema que dé 
            información y la muestre en la consola Java.
            Se ejecuta el comando que lista los procesos activos.
        
        */
        
        //Se usa ProcessBuilder en lugar de Runtime
        
            //En Windows: "tasklist" / En Linux: "ps", "-e"

            /*
                ProcessBuilder permite crear y configurar procesos externos
                de forma más flexible que Runtime.exec().
                Esta es la forma más moderna y recomendada de lanzar procesos.
        
                Prepara el proceso, pero no lo ejecuta:
                ProcessBuilder pb = new ProcessBuilder("tasklist");
        
                Lanzar el proceso, hay que llamar a start():
                Process proceso = pb.start();
            
            */
            
            ProcessBuilder pb = new ProcessBuilder("tasklist");
            
            try {
                
                Process proceso = pb.start(); //Crea el proceso
                
                /*
                    Para ver la salida del comando, necesitamos
                    leer su "InputStream"
                */
                
                /*
                    ProcessBuilder permite crear y configurar procesos externos
                    de forma más flexible que Runtime.exec().
                    Esta es la forma más moderna y recomendada de lanzar procesos.
        
                    Prepara el proceso, pero no lo ejecuta:
                    ProcessBuilder pb = new ProcessBuilder("tasklist");
        
                    Lanzar el proceso, hay que llamar a start():
                    Process proceso = pb.start();
                */
                
                /*
                
                proceso.getInputStream() => Obtiene el canal de salida del
                proceso hijo.
                Lo que el proceso escribe con System.out.println() llega aquí
                como bytes crudos.
                
                new InputStreamReader(proceso.getInputStreamReader()) =>
                Convierte los bytes crudos que llegan del proceso hijo en 
                caracteres de texto legibles.
                
                BufferedReader letor = new BufferedReader ( 
                        new InputStreamReader(proceso.getInputStream())) =>
                Envuelve todo añadiendo un buffer que permite leer línea a línea
                con readLine() en lugar de carácter a carácter:
                
                
                */
                
                
                /*
                
                Leer desde teclado:
                
                BufferedReader lector = new BufferedReader(
                    new InputStreamReader(System.in));
                
                Leer desde un proceso hijo:
                
                BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));
                
                
                */
                
                
                BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()) );
                String linea;
                System.out.println("Salida del comando: ");
                
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                }
                
                /*
                    Espero a que el proceso termine y veo su código
                    de salida (0 = OK)
                */
                
                int retorno = proceso.waitFor();
                
                System.out.println("\nProceso finalizado con código: " + retorno);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}


/*

1. pb.start(): A diferencia de exec(), aquí primero
    se configura el objeto ProcessBuilder y luego se lanza.

2. proceso.getInputStream(): Los procesos son independientes.
    Si quieres ver lo que el Notepad o el tasklist están
    'escribiendo', hay que conectar un flujo (Stream)
    para leerlo desde Java.

3. waitFor(): Esto detiene el programa Java hasta que el proceso
    hijo termine.
    Es vital para la sincronización.


*/