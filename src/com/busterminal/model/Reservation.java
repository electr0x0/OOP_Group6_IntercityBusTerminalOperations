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
    
    ReserveBus reserveBus;

    public Reservation(String passengerName, String email, int phone, ReserveBus reserveBus) {
        this.passengerName = passengerName;
        this.email = email;
        this.phone = phone;
        this.reserveBus = reserveBus;
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
