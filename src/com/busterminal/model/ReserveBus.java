
package com.busterminal.model;

import java.time.LocalDate;

/**
 *
 * @author UseR
 */
public class ReserveBus {
    
    private String busType, acType, city;
    private int duration;
    private LocalDate date;

    public ReserveBus(String busType, String acType, String city, int duration, LocalDate date) {
        this.busType = busType;
        this.acType = acType;
        this.city = city;
        this.duration = duration;
        this.date = date;
    }
    
    public void createReserveBus(){
        
    }
    
    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReserveBus() {
    }
}
