package com.busterminal.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Transition implements TransitionModel {
    private final StringProperty busId;
    private final StringProperty busName;
    private final StringProperty transitionDate;
    private final StringProperty departureTime;
    private final StringProperty destination;
    private final StringProperty tk;

    public Transition(String busId, String busName, String transitionDate, String departureTime, String destination, String tk) {
        this.busId = new SimpleStringProperty(busId);
        this.busName = new SimpleStringProperty(busName);
        this.transitionDate = new SimpleStringProperty(transitionDate);
        this.departureTime = new SimpleStringProperty(departureTime);
        this.destination = new SimpleStringProperty(destination);
        this.tk = new SimpleStringProperty(tk);
    }

    // Implement methods from the TransitionModel interface
    @Override
    public String getBusId() {
        return busId.get();
    }

    @Override
    public String getBusName() {
        return busName.get();
    }

    @Override
    public String getTransitionDate() {
        return transitionDate.get();
    }

    @Override
    public String getDepartureTime() {
        return departureTime.get();
    }

    @Override
    public String getDestination() {
        return destination.get();
    }

    @Override
    public String getTk() {
        return tk.get();
    }

    public StringProperty busIdProperty() {
        return busId;
    }

    public StringProperty busNameProperty() {
        return busName;
    }
}
