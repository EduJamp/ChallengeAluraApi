/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Enum.Countries;
import java.util.Map;
/**
 *
 * @author USER
 */
public class Coins {
    //necesarios para el metodo updateFromSearch
    private String base_code_search; //codigo base del pais que se desea realizar el cambio
    private Map<String, Double> conversionRates; //obtenemos los cambios de las monedas segun el pais elegido
    
    private String countryCoin; //pais al que pertenece la moneda que deseamos realizar el cambio

    //necesarios para el metodo updateFromPair
    private String base_code_pair; //codigo de la moneda a cambiar para la busqueda de pares
    private double rate;//tasas
    private String target_code; // codigo de pais que se hara que su tasa se multiplique para obtener el cambio
    private double amountToConvert;
    private double resultConvertion;// resultado de la conversion

    public Coins() {
        
    }
    
    public void updateFromSearch(CoinAPI element) {
        this.setBase_code_search(element.base_code());
        this.setConversionRates(element.conversion_rates());
        this.setCountryCoin(null);
    }
    
    public void updateFromPair(CoinPairAPI element) {
        this.setBase_code_pair(element.base_code());
        this.setTarget_code(element.target_code());
        this.setAmountToConvert(0.0);
        this.setRate(element.conversion_rate());
        this.setResultConvertion(0.0);
    }

    public String getBase_code_search() {
        return base_code_search;
    }

    public void setBase_code_search(String base_code) {
        this.base_code_search = base_code;
    }

    public String getCountryCoin() {
        return countryCoin;
    }

    public void setCountryCoin(String countryCoin) {
        this.countryCoin = countryCoin;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target) {
        this.target_code = target;
    }

    public double getResultConvertion() {
        return resultConvertion;
    }

    public void setResultConvertion(double resultConvertion) {
        this.resultConvertion = resultConvertion;
    }

    public String getBase_code_pair() {
        return base_code_pair;
    }

    public void setBase_code_pair(String base_code_pair) {
        this.base_code_pair = base_code_pair;
    }

    public double getAmountToConvert() {
        return amountToConvert;
    }

    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    @Override
    public String toString() {
        return "Coins{" + "base_code_search=" + base_code_search + ", conversionRates=" + conversionRates + ", countryCoin=" + countryCoin 
                + ", base_code_pair=" + base_code_pair + ", rate=" + rate + ", target_code=" + target_code 
                + ", amountToConvert=" + amountToConvert + ", resultConvertion=" + resultConvertion + '}';
    }
}
