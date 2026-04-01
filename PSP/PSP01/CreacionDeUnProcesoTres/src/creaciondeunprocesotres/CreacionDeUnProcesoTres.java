/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creaciondeunprocesotres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author kevin
 */
public class CreacionDeUnProcesoTres {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Runtime r = Runtime.getRuntime();
        String comando = "CMD /C DIRR";
        Process p = null;
        
        try {
            p = r.exec(comando);
            
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader (is));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            
            br.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(
                    new InputStreamReader (er));
            String liner;
            while ((liner = brer.readLine()) != null) {
                System.out.println("ERROR > " + liner);
            }
            brer.close();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        //COMPROBACIÓN DE ERROR 0 BIEN, 1 MAL
        int exitVal;
        
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
