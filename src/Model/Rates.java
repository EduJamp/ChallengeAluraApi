/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Enum.Countries;

/**
 *
 * @author USER
 */
public class Rates {
    private String country;
    private String abbreviatonCoin;
    private double rate;

    public Rates() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbbreviatonCoin() {
        return abbreviatonCoin;
    }

    public void setAbbreviatonCoin(String abbreviatonCoin) {
        this.abbreviatonCoin = abbreviatonCoin;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rates{" + "country=" + country + ", abbreviatonCoin=" + abbreviatonCoin + ", rate=" + rate + '}';
    }
}
