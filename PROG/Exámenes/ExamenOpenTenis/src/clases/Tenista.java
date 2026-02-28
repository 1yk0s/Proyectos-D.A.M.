/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author 1yk0s
 */
public class Tenista extends Participantes {
    
    private String categoria; //Masculina o Femenina
    private String nacionalidad;
    
    public Tenista(String nombre, String dni, String categoria, String nacionalidad) {
        super(dni, nombre);
        this.categoria = categoria;
        this.nacionalidad = nacionalidad;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Rol: Tenista | Categoría: " + categoria + " | Nacionalidad: " + nacionalidad;
    }    
}
