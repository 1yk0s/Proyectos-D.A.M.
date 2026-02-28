/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionamientoopentenis;

import clasesopentenis.Arbitros;
import clasesopentenis.Participantes;
import clasesopentenis.Partido;
import clasesopentenis.Tenistas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1yk0s
 */
public class OpenTenis {
    //Estructuras de datos
    private List<Participantes> participantes;
    private List<Partido> partidos;
    
    public OpenTenis() {
        participantes = new ArrayList<Participantes>();
        partidos = new ArrayList<Partido>();
    }
    
    //MÉTODOS AUXILIARES
    private Participantes buscarParticipante(String dni) {
        for(Participantes p: participantes) {
            if(p.getDNI().equals(dni)) {
                return p;
            }
        }
        return null;
    }
    
    //1. Listar árbitros, lista todos los árbitros de la clase participantes
    public void listarArbitros(String dni) {
        for(Participantes p : participantes) {
            if(p instanceof Arbitros) {
                System.out.println(p);
            }
        }
    }
    
    //2. Listar Tenistas por sexo
    public void listarTenistaPorSexo(String categoria) {
        for(Participantes p : participantes) {
            if(p instanceof Tenistas) {
                Tenistas t = (Tenistas) p;
                if(t.getCategoria().equals(categoria)) {
                    System.out.println(t);
                }
            }
        }
    }
    
}
