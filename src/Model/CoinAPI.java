/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Model;

import java.util.Map;

/**
 *
 * @author USER
 */
public record CoinAPI(String time_last_update_utc, String time_next_update_utc, String base_code, Map<String, Double> conversion_rates) {
    
}
