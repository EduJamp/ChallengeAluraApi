/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author USER
 */
public class Clock {
    private DecimalFormat formatter;

    public Clock() {
        formatter = new DecimalFormat("00");
    }
    
    //metodo para devolver la fecha actual formateada
    public String getDate() {
        //obtenemos la fecha actual cada vez que se ejecuta el codigo
        Calendar calendar = new GregorianCalendar();
        
        String day = Integer.toString(calendar.get(Calendar.DATE));
        String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        
        String fullDate = day + "-" + month + "-" + year;
        
        return fullDate;
    }
    
    public String getTime() {
        //obtenemos la hora actual cada vez que se ejecuta este metodo
        Calendar calendar = new GregorianCalendar();
        
        String hour = formatter.format(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = formatter.format(calendar.get(Calendar.MINUTE));
        String second = formatter.format(calendar.get(Calendar.SECOND));
        
        String fullTime = hour + ":" + minute + ":" + second;
        
        return fullTime;
    }
    
}
