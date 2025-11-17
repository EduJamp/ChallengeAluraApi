/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CurrencyConverter;

import Controller.ControllerAPI;
import Controller.ControllerClients;
import Controller.ControllerCoins;
import Model.CoinAPI;
import Model.CoinPairAPI;
import Model.Coins;
import Model.Rates;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class MainDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ControllerAPI controller = new ControllerAPI();
        try {
            
            String consult = "USD";
            String consult2 = "PEN";
//            CoinPairAPI coins = controller.pairConversion(consult, consult2);
//            System.out.println(coins.toString());
//            
//            Coins miMoneda = new Coins();
            ControllerCoins cc = new ControllerCoins();
            ControllerClients cn = new ControllerClients();
            String code = cn.generateCodeUniqueClient(4);
//            System.out.println(code);
//            cc.converterCoin(consult, consult2, 2);
//            System.out.println(cc.getCoin().getBase_code_pair());
//            System.out.println(cc.getCoin().getTarget_code());
//            System.out.println(cc.getCoin().toString());
//            System.out.println(miMoneda.getBase_code());
//            System.out.println(miMoneda.getTarget_code());
//            System.out.println(miMoneda.getRate());

//              ControllerCoins cc = new ControllerCoins();
//              String moneda = "PEN";
//              cc.converterCoin("PEN", "USD", 3);
//              cc.showRatesForCoin("PEN").forEach((clavePais, valorTasa) -> {
//                  System.out.println("Pais: " + clavePais + ", Tasa: " + valorTasa);
//              });

//               System.out.println(cc.getCoin().toString());
//              List<Rates> prueba = cc.showRatesOfTheCountry(moneda);
//              mostrarDatos(prueba);
            
            
        } catch (RuntimeException e) {
            System.out.println("No encontre esa moneda");
        }
    }
    
    private static void mostrarDatos(List<Rates> tasas) {
        
        if (tasas == null || tasas.isEmpty()) {
            System.out.println("⚠️ No hay datos para mostrar.");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("  CÓDIGO | PAÍS/MONEDA              | TASA");
        System.out.println("==================================================");

        // Iterar sobre la lista e imprimir
        for (Rates rate : tasas) {
            
            // Usamos .getAbbreviatonCoin() para el código y .getRate() para el valor.
            // Para el país, asumimos que getCountry() devuelve el nombre traducido.
            String pais = rate.getCountry().toString(); 
            String codigo = rate.getAbbreviatonCoin();
            double tasa = rate.getRate();
            
            // Usamos printf para un formato ordenado
            System.out.printf("  %-6s | %-24s | %.4f%n", 
                              codigo, 
                              pais, 
                              tasa);
        }
        System.out.println("==================================================");
    }
    
}
