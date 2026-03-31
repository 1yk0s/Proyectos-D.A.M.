/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creaciondeunproceso;

/**
 *
 * @author kevin
 */
public class CreacionDeUnProceso {
    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        String comando = "NOTEPAD";
        Process p;
        
        try {
            p = r.exec(comando);
        } catch (Exception e) {
            System.out.println("Error en: " + comando);
            e.printStackTrace();
        }
    }
}
