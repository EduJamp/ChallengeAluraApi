/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CoinAPI;
import Model.CoinPairAPI;
import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class ControllerAPI {
    
    private static final String API_KEY = "2b00808492deb0b67a3bc353";
    
    public CoinAPI searchInApi(String coinCurrent) {
        
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + coinCurrent);
       
       HttpClient client = HttpClient.newHttpClient();

       HttpRequest request = HttpRequest.newBuilder()
              .uri(direction)
              .build();
       
       try {

           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CoinAPI.class);
       
       }catch(Exception e) {
           
           throw new RuntimeException("No encontre esa moneda");
           
       }
       
    }
    
    public CoinPairAPI pairConversion(String coinCurrent, String coinChange) {
        
        URI direction = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "pair" + "/" + coinCurrent + "/" + coinChange + "/");
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direction)
                .build();
        
        try {
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CoinPairAPI.class);
            
        } catch(Exception e) {
            
            throw new RuntimeException("no se encontro es moneda");
            
        }
    }
}
