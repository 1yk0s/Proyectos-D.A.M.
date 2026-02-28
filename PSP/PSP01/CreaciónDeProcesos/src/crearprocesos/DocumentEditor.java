/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearprocesos;

/**
 *
 * @author 1yk0s
 */
public class DocumentEditor {
    
    //@Action
    public void crearNuevoEditor() {
        Process nuevoProceso; //Se define variable de tipo Process
        try {
            //Se obtiene el nombre del SO
            String osName  = System.getProperty("os.name");
            
            if(osName.toUpperCase().contains("WIN")) {
                //Windows
                nuevoProceso = Runtime.getRuntime().exec("java -jar " + 
                        "C:\\Users\\usuario\\Documents\\NetBeansProjects" +
                        "\\Editor\\dist\\DocumentEditor.jar");  
            } else {
                nuevoProceso = Runtime.getRuntime().exec("java -jar " +
                        "/home/usuario/NetBeansProjects/Editor/dist/DocumentEditor.jar");
            }
            
        } catch (SecurityException ex) {
            System.out.println("Ha ocurrido un error de seguridad" +
                    "No se ha podido crear el proceso por falta de permisos.");
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error, descripción: " +
                    ex.toString());
        }
    }
    
}
