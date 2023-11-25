package com.busterminal.model;
import java.io.Serializable;
import java.time.LocalDate;

public class BusReservation implements Serializable {

    private int reservationId;
    private String passengerName;
    private String busId;
    private LocalDate departureDate;
    private int fare;
    

    public BusReservation(int reservationId, String passengerName, String busId, LocalDate departureDate, int fare) {
        this.reservationId = reservationId;
        this.passengerName = passengerName;
        this.busId = busId;
        this.departureDate = departureDate;
        this.fare = fare;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

   
    
}
