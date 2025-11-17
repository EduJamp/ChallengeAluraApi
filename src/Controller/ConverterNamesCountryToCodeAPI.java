/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ConverterNamesCountryToCodeAPI {
    // Mapa inmutable que almacena la traducción inversa: Nombre -> Código
    private static final Map<String, String> NAME_TO_CODE_MAP = createMap();

    // Método privado que construye el mapa de traducción
    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        
        // **Asegúrate de que la clave (Nombre) coincida con lo que está en tu JComboBox**
        map.put("ARGENTINA", "ARS");
        map.put("BRASIL", "BRL");
        map.put("CANADA", "CAD");
        map.put("CHILE", "CLP");
        map.put("CHINA", "CNY");
        map.put("COLOMBIA", "COP");
        map.put("ESTADOS UNIDOS", "USD");
        map.put("ESPAÑA", "EUR"); 
        map.put("FRANCIA", "EUR"); 
        map.put("JAPON", "JPY");
        map.put("MEXICO", "MXN");
        map.put("PERU", "PEN");
        map.put("REINO UNIDO", "GBP");
        map.put("SUIZA", "CHF");
        map.put("PAIS", "NULL");
        
        // Hace el mapa inmutable (una buena práctica para constantes estáticas)
        return Collections.unmodifiableMap(map); 
    }
    

    /**
     * Convierte el nombre del país seleccionado en el JComboBox a su código de moneda de API.
     * @param countryName El nombre seleccionado (ej: "PERU").
     * @return El código de moneda (ej: "PEN"). Devuelve la entrada original si no se encuentra.
     */
    public static String getCodeForCountryName(String countryName) {
        if (countryName == null) {
            return ""; 
        }
        // Usamos toUpperCase() para asegurar la coincidencia, ya que las claves son MAYÚSCULAS.
        // Si no se encuentra, devolvemos el countryName original (lo que causaría el error en la API).
        return NAME_TO_CODE_MAP.getOrDefault(countryName.toUpperCase(), countryName);
    }
    
    /**
     * Mapea el nombre de un país a su símbolo de moneda.
     * @param countryName El nombre del país (ej: "PERU", "ESTADOS UNIDOS", "ESPAÑA").
     * @return El símbolo de la moneda (ej: "S/.", "$", "€"), o una cadena vacía si no se encuentra.
     */
    public static String getCurrencySymbolByCountry(String countryName) {
        
        // Mapa que almacena la relación entre el nombre y el símbolo.
        // Se recomienda usar el nombre en mayúsculas para simplificar la comparación.
        Map<String, String> currencySymbols = new HashMap<>();
        
        // Nombres de países y sus símbolos correspondientes
        currencySymbols.put("PERU", "S/.");
        currencySymbols.put("MEXICO", "MXN"); // A veces se usa el código para evitar ambigüedades con el dólar
        currencySymbols.put("ARGENTINA", "AR$");
        currencySymbols.put("BRASIL", "R$");
        currencySymbols.put("CANADA", "CA$");
        currencySymbols.put("CHILE", "CLP");
        currencySymbols.put("COLOMBIA", "COP");
        currencySymbols.put("ESTADOS UNIDOS", "$");
        currencySymbols.put("ESPAÑA", "EUR");
        currencySymbols.put("FRANCIA", "EUR");
        currencySymbols.put("REINO UNIDO", "£");
        currencySymbols.put("JAPON", "¥");
        currencySymbols.put("CHINA", "¥");
        currencySymbols.put("SUIZA", "CHF");
        
        // Normalizamos el nombre de entrada a mayúsculas y quitamos espacios extra.
        String key = countryName.toUpperCase().trim();
        
        // Buscamos y devolvemos el símbolo. Si no se encuentra, devolvemos una cadena vacía o un valor por defecto.
        // getOrDefault es útil para evitar un null.
        return currencySymbols.getOrDefault(key, ""); 
    }

}
