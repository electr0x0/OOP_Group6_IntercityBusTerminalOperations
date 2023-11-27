/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

/**
 *
 * @author electr0
 */


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;
import java.time.LocalDate;

public class BusTripSchedule implements Serializable{
    private int scheduleId;
    private LocalDate scheduleDate;
    private String tripId;
    private String time;
    private String sourceDestination;
    private String assignedDriver;
    private int assignedVehicle;
    private BusTrip trip;
    private boolean tripScheduleStatus = true;

    public BusTripSchedule(int scheduleId, BusTrip trip, LocalDate scheduleDate) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.trip = trip;
        this.tripId = trip.getTripID();
        this.time = trip.getTimeSlot();
        this.sourceDestination = trip.getSource() + "-" + trip.getDestination();
        this.assignedDriver = trip.getDriver();
        this.assignedVehicle = trip.getBus().getBusId();
    }

    public BusTrip getTrip() {
        return trip;
    }

    public void setTrip(BusTrip trip) {
        this.trip = trip;
    }
    
    

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSourceDestination() {
        return sourceDestination;
    }

    public void setSourceDestination(String sourceDestination) {
        this.sourceDestination = sourceDestination;
    }

    public String getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(String assignedDriver) {
        this.assignedDriver = assignedDriver;
    }

    public int getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(int assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    public boolean isTripScheduleStatus() {
        return tripScheduleStatus;
    }

    public void setTripScheduleStatus(boolean tripScheduleStatus) {
        this.tripScheduleStatus = tripScheduleStatus;
    }
    
    
    
    
    
    
    

}


