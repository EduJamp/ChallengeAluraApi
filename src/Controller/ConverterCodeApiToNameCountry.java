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
public class ConverterCodeApiToNameCountry {
    // Mapa inmutable que almacena la traducción inversa: Nombre -> Código
    private static final Map<String, String> CODE_TO_NAME_MAP = createMap();

    // Método privado que construye el mapa de traducción
    private static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        
        // **Asegúrate de que la clave (Nombre) coincida con lo que está en tu JComboBox**
        map.put("ARS", "ARGENTINA");
        map.put("BRL", "BRASIL");
        map.put("CAD", "CANADA");
        map.put("CLP", "CHILE");
        map.put("CNY", "CHINA");
        map.put("COP", "COLOMBIA");
        map.put("USD", "ESTADOS UNIDOS");
        map.put("EUR", "ESPAÑA"); 
        map.put("EUR", "FRANCIA"); 
        map.put("JPY", "JAPON");
        map.put("MXN", "MEXICO");
        map.put("PEN", "PERU");
        map.put("GBP", "REINO UNIDO");
        map.put("CHF", "SUIZA");
        map.put("NULL", "PAIS");
        
        // Hace el mapa inmutable (una buena práctica para constantes estáticas)
        return Collections.unmodifiableMap(map); 
    }
    
    public static String getCountryNameForCode(String code) {
        if (code == null) {
            return ""; 
        }
        // Usamos toUpperCase() para asegurar la coincidencia, ya que las claves son MAYÚSCULAS.
        // Si no se encuentra, devolvemos el countryName original (lo que causaría el error en la API).
        return CODE_TO_NAME_MAP.getOrDefault(code.toUpperCase(), "");
    }
}
