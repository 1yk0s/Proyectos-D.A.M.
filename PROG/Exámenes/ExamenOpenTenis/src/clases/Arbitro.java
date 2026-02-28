/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.time.LocalDate;

/**
 *
 * @author 1yk0s
 */
public class Arbitro extends Participantes {
    
    private LocalDate fechaLicencia;
    
    public Arbitro(String nombre, String dni, LocalDate fechaLicencia) {
        super(dni, nombre); // Llamada al constructor de la clase padre
        this.fechaLicencia = fechaLicencia;
    }
    
    @Override
    public String toString() {
        return super.toString() + "| Rol: Árbitro | Fecha de licencia: " + fechaLicencia;
    }
    
}
