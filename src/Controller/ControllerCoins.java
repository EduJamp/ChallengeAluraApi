/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Enum.Countries;
import Model.Client;
import Model.CoinAPI;
import Model.CoinPairAPI;
import Model.Coins;
import Model.Rates;
import Model.RecordUserModel;
import View.ConverterFinal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author USER
 */
public class ControllerCoins {
    private static ControllerCoins instance;
    
    private Coins coin;
    private ControllerAPI apiService = new ControllerAPI();
    
    public ControllerCoins() {
        this.coin = new Coins();
    }
    
    public static ControllerCoins getInstance() {
        if (instance == null) {
            instance = new ControllerCoins();
        }
        return instance;
    }
    
    // -- METODOS PARA LO QUE ES LA LOGICA DE CONVERTIR MONEDAS --
    
    //metodo para convertir las monedas
    public void converterCoin(String coin1, String coin2, double canttidadAConvertir) {
        CoinPairAPI coinApiPair = apiService.pairConversion(coin1,coin2);
        
        coin.updateFromPair(coinApiPair);
        this.coin.setAmountToConvert(canttidadAConvertir);
        
        double conversion = this.coin.getRate() * this.coin.getAmountToConvert();
        
        this.coin.setResultConvertion(conversion);
    }
    
    //metodo para ver las tasas
    public double showRates(String coin1, String coin2) {
        CoinPairAPI coinApiPair = apiService.pairConversion(coin1,coin2);
        coin.updateFromPair(coinApiPair);
        
        return this.coin.getRate();
    }
    
    // -- METODOS PARA MOSTRAR LAS TASAS DE LOS PAISES
    
    //metodo para obtener las tasas dependiendo un pais, devuelve un map solo con las tasas y paises que se quiere
    public Map<String,Double> showRatesForCoin(String data) {
        CoinAPI coinApiShow = apiService.searchInApi(data);
        
        coin.updateFromSearch(coinApiShow);
        
        Map<String, Double> completeMap = this.coin.getConversionRates();
        
        
//        Set<String> codesCoin = filterMap.keySet(); //colocamos todos los codigos(key) de las monedas en este set
//        Collection<Double> ratesCoin = filterMap.values(); //colocamos todos las tasas en la collection 
       
        List<String> listFilterMap = List.of(
                                                "ARS", //ARGENTINA
                                                "BRL", //BRASIL
                                                "CAD", //CANADA
                                                "CLP",//CHILE
                                                "CNY",//CHINA
                                                "COP", //COLOMBIA
                                                "USD", //ESTADOS UNIDOS -- EEUU
                                                "EUR",//ESPAÑA - //FRANCIA
                                                "JPY",//JAPON
                                                "MXN",//MEXICO
                                                "PEN", //PERU
                                                "GBP",//REINO UNIDO
                                                "CHF"//SUIZA
        );
        
        Map<String, Double> filterMap = new HashMap<>();
        
        for (String codeCountry : listFilterMap){
            
            if (completeMap.containsKey(codeCountry)){
                
                double rateCountry = completeMap.get(codeCountry);
                
                filterMap.put(codeCountry, rateCountry);
            }
        }
        
        return filterMap;//devolvemos el nuevo map
    }
    
    //metodo para mostrar las tasas de los paises
    public List<Rates> showRatesOfTheCountry(String data) {
        Map<String, Double> copy = showRatesForCoin(data);
        
        List<Rates> listRate = new ArrayList<>();
        
        Map<String, String> mapaTraduccion = new HashMap<>();
            mapaTraduccion.put("ARS", "ARGENTINA");
            mapaTraduccion.put("BRL", "BRASIL");
            mapaTraduccion.put("CAD", "CANADA");
            mapaTraduccion.put("CLP", "CHILE");
            mapaTraduccion.put("CNY", "CHINA");
            mapaTraduccion.put("COP", "COLOMBIA");
            mapaTraduccion.put("USD", "ESTADOS UNIDOS");
            mapaTraduccion.put("EUR", "ESPAÑA - FRANCIA"); 
            mapaTraduccion.put("JPY", "JAPON");
            mapaTraduccion.put("MXN", "MEXICO");
            mapaTraduccion.put("PEN", "PERU");
            mapaTraduccion.put("GBP", "REINO UNIDO");
            mapaTraduccion.put("CHF", "SUIZA");
            
        
        for (Map.Entry<String, Double> entry : copy.entrySet()) {
        
            Rates rate = new Rates();
            
            String codigoMoneda = entry.getKey();
            Double tasa = entry.getValue();

            //Obtener el nombre del país para el campo 'country' de Rates
            String nombrePais = mapaTraduccion.getOrDefault(
                codigoMoneda, 
                "País Desconocido"
            );
            
            rate.setCountry(nombrePais);
            rate.setAbbreviatonCoin(codigoMoneda);
            rate.setRate(tasa);
            
            listRate.add(rate);
        }
        
        return listRate;
    }
    
    public ControllerAPI getApiService() {
        return apiService;
    }

    public void setApiService(ControllerAPI apiService) {
        this.apiService = apiService;
    }
    
    public void setCoin(Coins coin) {
        this.coin = coin;
    }
    
    public Coins getCoin() {
        return this.coin;
    }
}
