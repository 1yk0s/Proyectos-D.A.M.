/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenopentenis;

/**
 *
 * @author 1yk0s
 */
public class TestOpenTenis {
    
    public static void main(String[] args) {
        
        //Se crea objeto de tipo OpenTenis.
        OpenTenis open = new OpenTenis();
        
        /*
        1. Dar de alta 2 árbitros y 4 tenistas (2 masculinos y 2 femeninos)
           Se realiza de forma desordenada para probar que se ordenan por DNI
        */
        open.altaArbitro("Manolo", "00000001A", "2026-01-01");
        open.altaTenista("Pepe", "00000002A", "Masculina", "Nigeria");
        open.altaArbitro("Polo", "00000003A", "2026-01-02");
        open.altaTenista("Francisca", "00000004A", "Femenino", "Marroquí");
        open.altaTenista("Pepa", "00000005A", "Femenino", "Holandesa");
        open.altaTenista("Germán", "00000006A", "Masculina", "Alemán");
        
        /*
        2. Listar por separado solo los árbitros, los tenistas y las tenistas.
           Comprobar que estén ordenados
        */
        open.listarArbitros();
        open.listarTenistaPorSexo("Masculino");
        open.listarTenistaPorSexo("Femenino");
        
        /*
        3. Dar de alta al menos 3 partidos, uno de ellos intentando dar valor a dos
           tenistas de diferente categoría
        */
        open.altaPartido(0, "2025-01-03", "Masculina", "00000001A", "00000002A", "00000006A");
        open.altaPartido(1, "2025-06-23", "Femenina", "00000003A", "00000004A", "00000005A");
        open.altaPartido(2, "2022-12-01", "Masculina", "00000001A", "00000002A", "00000004A");
        
        /*
        4. Asigna ganadores a los partidos
        */
        
        open.asignarGanador(0, "00000006A");
        open.asignarGanador(1, "00000004A");
        
        /*
        5. Mostrar los partidos jugados por los tenistas de la nacionalidad "xxx"
        */
        open.partidosJugadosPorNacionalidad("Alemán");
        
        /*
        6. Mostrar la cantidad de partidos ganados de 2 tenistas
        */
        open.mostrarVictoriasJugador("00000002A");
        open.mostrarVictoriasJugador("00000004A");
        
        /*
        7. Modifica el nombre de un árbitro
        */
        open.cambiarNombreArbitro("00000001A", "Paco");
        
        /*
        8. Guarda en fichero los partidos
        */
        open.guardarDatos("openTenis_datos.dat");
        
    }
    
}
