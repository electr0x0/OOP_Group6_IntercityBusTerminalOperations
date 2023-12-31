/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.Serializable;

/**
 *
 * @author UseR
 */
public class Reservation  implements Serializable {
    
    String passengerName;
    String email;
    int phone;
    String reserveId;
    ReserveBus reserveBus;

    public Reservation(String passengerName, String email, int phone,String reserveId, ReserveBus reserveBus) {
        this.passengerName = passengerName;
        this.email = email;
        this.phone = phone;
        this.reserveId = reserveId;
        this.reserveBus = reserveBus;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId;
    }

   

  

    @Override
    public String toString() {
        return "Reservation{" + "passengerName=" + passengerName + ", email=" + email + ", phone=" + phone + ", reserveBus=" + reserveBus + '}';
    }

    public Reservation() {
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

  

   

    public ReserveBus getReserveBus() {
        return reserveBus;
    }

    public void setReserveBus(ReserveBus reserveBus) {
        this.reserveBus = reserveBus;
    }
    
    
    
    
}
