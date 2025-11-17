/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USER
 */
public class RecordUserModel {
    private String client;
    private String currentHour;
    private String currentDate;
    private double amountToConvert;
    private double conversionResult;
    private String base_code;
    private String target_code;
    private double rates;

    public RecordUserModel() {
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(String currentHour) {
        this.currentHour = currentHour;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public double getAmountToConvert() {
        return amountToConvert;
    }

    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    public double getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "RecordUserModel{" + "client=" + client + ", currentHour=" + currentHour + ", currentDate=" + currentDate 
                + ", amountToConvert=" + amountToConvert + ", conversionResult=" + conversionResult + ", base_code=" + base_code 
                + ", target_code=" + target_code + ", rate=" + rates + '}';
    }
}
