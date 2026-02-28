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
public class Arbitros extends Participantes {
    
    private String fechaLicencia;
    
    public Arbitros(String dni, String nombre, String fechaLicencia) {
        super(dni, nombre);
        this.fechaLicencia = fechaLicencia;
    }
    
    public String getFechaLicencia() {
        return fechaLicencia;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | Rol: Árbitro " + " | Fecha de Licencia: " + fechaLicencia;
    }
    
}
