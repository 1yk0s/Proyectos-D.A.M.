/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesopentenis;

/**
 *
 * @author 1yk0s
 */
public class Tenistas extends Participantes {
    
     private String categoria;
     private String nacionalidad;
     
     public Tenistas(String dni, String nombre, String categoria, String nacionalidad) {
         super(dni, nombre);
         this.categoria = categoria;
         this.nacionalidad = nacionalidad;;
     }
     
     public String getCategoria() {
         return categoria;
     }
    
     public String getNacionalidad() {
         return nacionalidad;
     }
     
     @Override
     public String toString() {
         return super.toString() + " | Rol: Tenista " + " | Categoría: " + categoria + " | Nacionalidad: " + nacionalidad;
     }
}
