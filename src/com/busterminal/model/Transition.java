package com.busterminal.model;

import java.time.LocalDate;


public class Transition {

    private int busId;
    private String busName;
    private LocalDate transitionDate;
    private String departureTime;
    private String destination;
    private int tk;

    public Transition(int busId, String busName, LocalDate transitionDate, String departureTime, String destination, int tk) {
        this.busId = busId;
        this.busName = busName;
        this.transitionDate = transitionDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.tk = tk;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public LocalDate getTransitionDate() {
        return transitionDate;
    }

    public void setTransitionDate(LocalDate transitionDate) {
        this.transitionDate = transitionDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTk() {
        return tk;
    }

    public void setTk(int tk) {
        this.tk = tk;
    }

    

}
