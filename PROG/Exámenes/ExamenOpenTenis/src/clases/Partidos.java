/**
 * 
 * compile.on.save=true license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author 1yk0s
 */

import java.io.Serializable;
import java.time.LocalDate;

public class Partidos implements Comparable<Partidos>, Serializable {
    
    private int codNum;
    private LocalDate fecha;
    private String categoria;
    private Arbitro arbitro;    //Un partido tiene un árbitro
    private Tenista tenista1;   //Un partido tiene dos tenistas (1/2)
    private Tenista tenista2;   //Un partido tiene dos tenistas (2/2)
    private Tenista ganador;    //Inicialmente null porque no se ha utilizado.
    
    public Partidos(int codNum, LocalDate fecha, String categoria, Arbitro arbitro, Tenista tenista1,
                    Tenista tenista2) {
        this.codNum = codNum;
        this.fecha = fecha;
        this.categoria = categoria;
        this.arbitro = arbitro;
        this.tenista1 = tenista1;
        this.tenista2 = tenista2;
    }
    
    
    // Métodos Getter necesarios para la lógica 
    
    public int getCodNum() {
        return codNum;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public Arbitro getArbitro() {
        return arbitro;
    }
    
    public Tenista getTenista1() {
        return tenista1;   
    }
    
    public Tenista getTenista2() {
        return tenista2;
    }
    
    public Tenista getGanador() {
        return ganador;
    }
    
    //Método Setter necesarios para la lógica.
    public void setGanador(Tenista ganador) {
     this.ganador = ganador;   
    }
    
    //Métodos sobreescritos
    @Override
    public int compareTo(Partidos otro) {
        return this.fecha.compareTo(otro.getFecha());
    }
    
    @Override
    public String toString() {
        String textoGanador = (ganador == null) ? "Pendiente" : ganador.getNombre();
        return "Partido: " + codNum + " ( " + fecha + " )" + " [ " + categoria + " ] " +
                tenista1.getNombre() + " vs " + tenista2.getNombre() + " | Árbitro : " +
                arbitro.getNombre() + " | Ganador: " + textoGanador;
    }
    
}
