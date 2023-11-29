
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author UseR
 */
public class ReserveBus implements Serializable {

    @Override
    public String toString() {
        return "ReserveBus{" + "reserveId=" + reserveId + ", busType=" + busType + ", acType=" + acType + ", city=" + city + ", duration=" + duration + ", date=" + date + ", fare=" + fare + '}';
    }
    private int reserveId;
    private String busType, acType, city;
    private int duration;
    private LocalDate date;
    private int fare;

    public ReserveBus(String busType, String acType, String city, int duration, LocalDate date, int fare ) {
        this.busType = busType;
        this.acType = acType;
        this.city = city;
        this.duration = duration;
        this.date = date;
        this.fare = fare;
    }

    public ReserveBus(int reserveId, String busType, String acType, String city, int duration, LocalDate date, int fare) {
        this.reserveId = reserveId;
        this.busType = busType;
        this.acType = acType;
        this.city = city;
        this.duration = duration;
        this.date = date;
        this.fare = fare;
    }

    public ReserveBus( String busType,  int fare, LocalDate date) {
        
        this.busType = busType;
      
        this.fare = fare;
        this.date = date;
    }
    
    
    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
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

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }
    
}
