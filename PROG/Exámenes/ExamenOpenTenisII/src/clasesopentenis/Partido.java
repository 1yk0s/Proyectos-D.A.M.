/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesopentenis;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author 1yk0s
 */
public class Partido implements Comparable<Partido>, Serializable {
    
    private int codNum;
    private LocalDate fecha;
    private String categoria;
    private Arbitros arbitro;
    private Tenistas tenista1;
    private Tenistas tenista2;
    private Tenistas ganador;
    
    public Partido(int codNum, LocalDate fecha, String categoría, Arbitros arbitro, Tenistas tenista1,
                    Tenistas tenista2) {
        this.codNum = codNum;
        this.fecha = fecha;
        this.categoria = categoria;
        this.tenista1 = tenista1;
        this.tenista2 = tenista2;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public Tenistas getTenista1() {
        return tenista1;
    }
    
    public Tenistas getTenista2() {
        return tenista2;
    }
    
    public void setGanador(Tenistas ganador) {
        this.ganador = ganador;
    }
    
    
    
    @Override
    public int compareTo(Partido otro) {
        return this.fecha.compareTo(otro.getFecha());
    }
    
    @Override
    public String toString() {
        String textoGanador = (ganador == null) ? "Pendiente" : ganador.getNombre();
        return "Código de partido: " + codNum + " (" + fecha + ") " + " Categoría: " + " [" + categoria + " ]"
                + "Nombre Tenista local: " + tenista1.getNombre() + " Nombre Tenista Visitante: " + tenista2.getNombre() +
                        " Nombre del Arbitro: " + arbitro.getNombre() + " Nombre del Tenista ganador: " + textoGanador;
    }
    
}
