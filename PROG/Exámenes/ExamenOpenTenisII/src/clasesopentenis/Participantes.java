/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesopentenis;

import java.io.Serializable;

/**
 *
 * @author 1yk0s
 */
public class Participantes implements Comparable<Participantes>, Serializable {

    protected String dni;
    protected String nombre;
    
    public Participantes(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    public String getDNI() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setDNI(String dni) {
        this.dni = dni;
    }
    
    @Override
    public int compareTo(Participantes otro) {
        return this.dni.compareTo(otro.getDNI());
    }
    
    @Override
    public String toString() {
        return "Participante con DNI: " + dni + " - Nombre: " + nombre;
    }
    
    
    
}
