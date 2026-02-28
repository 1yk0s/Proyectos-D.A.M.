/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenopentenis;

import clases.Arbitro;
import clases.Participantes;
import clases.Partidos;
import clases.Tenista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 1yk0s
 */
public class OpenTenis {
    
    //Dos estructuras de datos
    
    //Una estructura para almacenar los Partidos
    private List<Partidos> partidos;
    
    //Una estructura para almacenar los árbitros y tenistas
    private List<Participantes> participantes; // Polimorfismo: Guarda árbitros y tenistas
    
    //El constructor no recibe parámetros y construye ambas listas.
    public OpenTenis() {
        this.partidos = new ArrayList<>();
        this.participantes = new ArrayList<>();
        
    }
    
    //1. Listar árbitros, tenistas(masculinos) o tenistas(femeninos)
    private Participantes buscarParticipante(String dni) {
        for(Participantes p : participantes) {
            if(p.getDni().equalsIgnoreCase(dni)) {
                return p;
            }
        }
        return null;
    }
    
    //2. listarArbitros(). Lista todos los árbitros de la clase Participantes.
    public void listarArbitros() {
        System.out.println("--- LISTADO DE ÁRBITROS ---");
        for(Participantes p : participantes) {
            if(p instanceof Arbitro) {
                System.out.println(p);
            }
        }
    }
    
    //3. listarTenistaPorSexo(). Listar Tenista por sexo.
    public void listarTenistaPorSexo(String sexo) {
        System.out.println("--- LISTAR TENISTA CON SEXO: " + sexo + " ---");
        for(Participantes p : participantes) {
            if(p instanceof Tenista) {
                Tenista t = (Tenista) p; //Casting seguro.
                if(t.getCategoria().equalsIgnoreCase(sexo)) {
                    System.out.println(t);
                }
            }
        }
    }
    
    //4. altaArbitro(). Se da de alta un árbitro.
    public void altaArbitro(String nombre, String dni, String fechaLicenciaString) {
        if(buscarParticipante(dni) != null) {
            System.out.println("Error: Ya existe un participante con DNI " + dni);
            return;
        }
        try {
            LocalDate fecha = LocalDate.parse(fechaLicenciaString); //Conversión String -> LocalDate
            Arbitro a = new Arbitro(dni, nombre, fecha);
            participantes.add(a);
            Collections.sort(participantes);
            System.out.println("Alta OK: Árbitro " + nombre);
            
        } catch (Exception e) { 
            System.out.println("Error en formato de fecha (Use YYYY-MM-DD).");   
        }
    }
    
    //5. altaTenista(). Se dan de alta los Tenistas
    public void altaTenista(String nombre, String dni, String categoria, String nacionalidad) {
        if(buscarParticipante(dni) != null) {
            System.out.println("Error: Ya existe un participante con DNI " + dni);
            return;
        }
        //Validar categoría simple
        if(!categoria.equalsIgnoreCase("Masculina") && !categoria.equalsIgnoreCase("Femenino")) {
            System.out.println("Error: Categoría debe ser Masculina o Femenino");
            return;
        }
        
        Tenista t = new Tenista(dni, nombre, categoria, nacionalidad);
        participantes.add(t);
        Collections.sort(participantes);
        System.out.println("Alta OK: Tenista " + nombre);
    }
    
    //6. altaPartidos(). Se dan de alta los partidos.
    public void altaPartido(int codigo, String fechaString, String categoria, String dniArbitro, String dniTenistaLocal,
                            String dniTenistaVisitante) {
        LocalDate fecha = LocalDate.parse(fechaString);
        
        //Se buscan los objetos en la lista única
        Participantes arbitro = buscarParticipante(dniArbitro);
        Participantes tenista1 = buscarParticipante(dniTenistaLocal);
        Participantes tenista2 = buscarParticipante(dniTenistaVisitante);
        
        //Se comprueban las existencias
        if(arbitro == null || tenista1 == null || tenista2 == null) {
            System.out.println("Error: alguno de los DNI no existe en la lista");
        }
        
        //Se comprueban los  tipos (instanceof)
        if(!(arbitro instanceof Arbitro)) {
            System.out.println("El DNI " + dniArbitro + " No corresponde a un Árbitro");
        }
        
        if(!(tenista1 instanceof Tenista) || !(tenista2 instanceof Tenista)) {
            System.out.println("Error: el DNI " + dniTenistaLocal + " o el DNI " + dniTenistaVisitante +
                                " No corresponden a un Tenista");
        }
        
        //Casting y lógica de negocio
        Tenista t1 = (Tenista) tenista1;
        Tenista t2 = (Tenista) tenista2;
        Arbitro arb = (Arbitro) arbitro;
        
        if(t1.getDni().equals(t2.getDni())) {
            System.out.println("El jugador Local y el jugador Visitante son el mismo");
        }
        
        if(!t1.getCategoria().equalsIgnoreCase(categoria) || !t2.getCategoria().equalsIgnoreCase(categoria)){
            System.out.println("Error: La categoría de los Tenistas no coincide con la del partido");
        }
        
        //Comprobaciones correctas, se crea el partido
        Partidos p = new Partidos(codigo, fecha, categoria, arb, t1, t2);
        
        //Añade a la lista partidos
        partidos.add(p);
        
        //Se ordena la lista partidos por fecha
        Collections.sort(partidos);
        System.out.println("Alta OK: Partido creado");
        
    }
    
    //7. Listar todos los partidos
    public void listarPartidos() {
        System.out.println("--- LISTAR TODOS LOS PARTIDOS --- Ordenados por fecha");
        for(Partidos p : partidos) {
            System.out.println(p);
        }
    }
    
    //8. Mostrar partidos jugados por nacionalidad
    public void partidosJugadosPorNacionalidad(String nacionalidad) {
        System.out.println("--- LISTAR PARTIDOS GANADOS POR NACIONALIDAD ---");
        for(Partidos p : partidos) {
            boolean t1Es = p.getTenista1().getNacionalidad().equalsIgnoreCase(nacionalidad);
            boolean t2Es = p.getTenista2().getNacionalidad().equalsIgnoreCase(nacionalidad);
            
            if (t1Es || t2Es) {
                System.out.println(p);
            }
        }
    }
    
    //9. Mostrar partidos ganados por un jugador (Tenista).
    public void mostrarVictoriasJugador(String dni) {
        int contador = 0;
        for(Partidos p : partidos) {
            //Se verifica que el ganador no sea null y que coincida con el dni de parámetro
            if(p.getGanador() != null && p.getGanador().getDni().equals(dni)) {
                contador++;
            }
        }
        System.out.println("El jugador con DNI: " + dni + " ha ganado " + contador + " partidos");
    }
    
    //10. Buscar partido. Método privado para utilizar en otros métodos (método auxiliar)
    private Partidos buscarPartido(int codigo) {
        for(Partidos p : partidos) {
            if(p.getCodNum() == codigo) {
                return p;
            }
        }
        return null;
    }
    
    //11. Asignar ganador de un partido.
    public void asignarGanador(int codigo, String dni) {
        Partidos p = buscarPartido(codigo);
        if(p == null) {
            System.out.println("Error: el Partido no se ha encontrado");
        }
        //Se verifica si el DNI pasado por parámetro coincide con alguno de los jugadores del partido
        if(p.getTenista1().getDni().equals(dni)) {
            p.setGanador(p.getTenista1());
            System.out.println("Resultado actualizado. Ganador: " + p.getTenista1().getNombre());
        } else if (p.getTenista2().getDni().equals(dni)){
            p.setGanador(p.getTenista2());
            System.out.println("Resultado actualizado. Gandor: " + p.getTenista2().getNombre());
        } else {
            System.out.println("Error: El DNI introucido no corresponde con ningún tenista del partido");
        }
    }
    
    //12. Cambiar nombre de árbitro existente
    public void cambiarNombreArbitro(String dni, String nombre) {
        Participantes p = buscarParticipante(dni);
        if(p != null && p instanceof Arbitro) {
            p.setNombre(nombre);
            System.out.println("Nombre del árbitro modificado correctamente");
        } else {
            System.out.println("Error: No existe ningún Árbitro con ese DNI");
        }
    }
    
    //13. Guardar datos
    public void guardarDatos(String nombre) {
        //Try-whit-resources: Cierra el flujo al terminar el bloque
        try 
            (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombre))) {
            oos.writeObject(participantes);
            oos.writeObject(partidos);
        
        } catch (IOException e) {
             System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
    
        
    //14. Cargar datos
    public void cargarDatos(String nombre) {
        File archivo = new File(nombre); 
        if(!archivo.exists()) {
            System.out.println("No existen los datos guardados previamente");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombre))) {
            this.participantes = (List<Participantes>) ois.readObject();
            this.partidos = (List<Partidos>) ois.readObject();
            System.out.println("Datos cargados en el fichero");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
        
}
