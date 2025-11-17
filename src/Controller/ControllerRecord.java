/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Client;
import Model.Coins;
import Model.RecordUserModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ControllerRecord {
    // 1. Instancia estática (única)
    private static ControllerRecord instance;
    
    // Lista para guardar los registros
    private final List<RecordUserModel> listRecord;

    // 2. Constructor privado para evitar que se creen instancias con 'new'
    private ControllerRecord() {
        // Inicializar la lista una sola vez
        this.listRecord = new ArrayList<>(); 
    }

    // 3. Método estático para acceder a la instancia única
    public static ControllerRecord getInstance() {
        if (instance == null) {
            instance = new ControllerRecord();
        }
        return instance;
    }

    // 4. Tu método saveRecord normal
    public void saveRecord(Client client, String hour, String date, Coins coins) {
        RecordUserModel records = new RecordUserModel();
        
        String nameCountryBase = ConverterCodeApiToNameCountry.getCountryNameForCode(coins.getBase_code_pair());
        String nameCountryTarget = ConverterCodeApiToNameCountry.getCountryNameForCode(coins.getTarget_code());
        
        records.setClient(String.valueOf(client.getCodClient()));
        records.setCurrentDate(date);
        records.setCurrentHour(hour);
        records.setBase_code(nameCountryBase);
        records.setTarget_code(nameCountryTarget);
        records.setRates(coins.getRate());
        records.setAmountToConvert(coins.getAmountToConvert());
        records.setConversionResult(coins.getResultConvertion());
        
        // Usar la lista interna (que es la misma para toda la app)
        this.listRecord.add(records); // Usar el método estándar 'add' si es una List
    }
    
    // (Opcional) Método para ver la lista completa
    public List<RecordUserModel> getListRecord() {
        return this.listRecord;
    }
}
