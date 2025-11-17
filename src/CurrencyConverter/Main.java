/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CurrencyConverter;

import Controller.ControllerClients;
import Model.Client;
import SaveData.ClientRepositoryCSV;
import View.LoginUser;
import View.Splash;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClientRepositoryCSV manejador = new ClientRepositoryCSV();
        ControllerClients controller = ControllerClients.getInstance();
        
        List<Client> clientesCargados = manejador.cargarDatos();
        for (Client cliente : clientesCargados) {
            controller.addClientsInList(cliente);
        }
        
        
        //intanciamos el objeto de clase runnable
        Runnable run = () -> {
            
            //instanciamos la clase Splash para poder hacer que se muestre la ventana
            Splash splash = new Splash();
            splash.setVisible(true);
            splash.setLocationRelativeTo(null);
                
            try {
                //usamos un hilo para indicar cuanto tiempo queremos que se muestre el splash
                Thread.sleep(5000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //cerramos el splash
            splash.dispose();
            
            //instanciamos la nueva ventana que deseamos que se aperture
            LoginUser login = new LoginUser();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
        };
        
        //creamos un hilo donde almacenaremos el objeto runnable para poder iniciarlo
        Thread newHiloSplash = new Thread(run);
        
        //iniciamos el run del objeto runnable
        newHiloSplash.start();
        
    }
    
}
