package com.busterminal.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BusReservation {

    private final StringProperty reservationId;
    private final StringProperty passengerName;
    private final StringProperty busId;
    private final StringProperty departureDate;
    private final StringProperty fare;

    public BusReservation(String reservationId, String passengerName, String busId, String departureDate, String fare) {
        this.reservationId = new SimpleStringProperty(reservationId);
        this.passengerName = new SimpleStringProperty(passengerName);
        this.busId = new SimpleStringProperty(busId);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.fare = new SimpleStringProperty(fare);
    }

    public String getReservationId() {
        return reservationId.get();
    }

    public StringProperty reservationIdProperty() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId.set(reservationId);
    }

    public String getPassengerName() {
        return passengerName.get();
    }

    public StringProperty passengerNameProperty() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName.set(passengerName);
    }

    public String getBusId() {
        return busId.get();
    }

    public StringProperty busIdProperty() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId.set(busId);
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public StringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getFare() {
        return fare.get();
    }

    public StringProperty fareProperty() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare.set(fare);
    }
}
