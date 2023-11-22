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
    private int tripId;
    private String time;
    private String sourceDestination;
    private String assignedDriver;
    private String assignedVehicle;

    public BusTripSchedule(int scheduleId, LocalDate scheduleDate, int tripId, String time, String sourceDestination, String assignedDriver, String assignedVehicle) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.tripId = tripId;
        this.time = time;
        this.sourceDestination = sourceDestination;
        this.assignedDriver = assignedDriver;
        this.assignedVehicle = assignedVehicle;
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

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
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

    public String getAssignedVehicle() {
        return assignedVehicle;
    }

    public void setAssignedVehicle(String assignedVehicle) {
        this.assignedVehicle = assignedVehicle;
    }

    //@Override
    //public String toString() {
        //return "BusTripSchedule{" + "scheduleId=" + scheduleId + ", scheduleDate=" + scheduleDate + ", tripId=" + tripId + ", time=" + time + ", sourceDestination=" + sourceDestination + ", assignedDriver=" + assignedDriver + ", assignedVehicle=" + assignedVehicle + '}';
    //}
    
    

}


