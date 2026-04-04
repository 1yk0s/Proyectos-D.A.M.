/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2ut1;

/**
 *
 * @author kevin
 */
public class Ejercicio2UT1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        
            Programa que reciba desde la línea de comandos un nombre
            y lo visualice.
        
        */
        
        
        /*
        
            Se comprueba si el usuario ha pasado algún argumento
            args.length indica cuántos argumentos se han recibido
        
        */
        
        if (args.length > 0) {
            
            /*
            
                args[0] contiene el primer argumento recibido,
                en este caso el nombre.
            
            */
            
            String nombre = args[0];
            
            //Se muestra el nombre por consola
            
            System.out.println("El nombre recibido es: " + nombre);
            
        } else {
            
            //Si no se ha pasado ningún argumento, se avisa al usuario
            
            System.out.println("Error: no se ha recibido ningún argumento");
            System.out.println("Uso correcto: java MostrarNombre <nombre>");
            
        }   
    }
}
