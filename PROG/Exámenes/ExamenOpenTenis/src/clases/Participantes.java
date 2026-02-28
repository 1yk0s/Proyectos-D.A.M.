/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author 1yk0s
 */

/*
* Se implementa Serializable para poder guardarse en fichero binario.
* Se implementa Comparable<> para definir un orden "natural" por DNI.
*/

public abstract class Participantes implements Comparable<Participantes>, Serializable {
    
    protected String nombre;
    protected String dni;

    public Participantes(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    @Override
    public int compareTo(Participantes otro) {
        return this.dni.compareTo(otro.getDni());
    }
    
    @Override
    public String toString() {
        return "DNI: " + dni + " - Nombre: " + nombre;
    }
}
