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

public class BusTripSchedule implements Serializable {
    private SimpleIntegerProperty scheduleId;
    private SimpleStringProperty scheduleDate;
    private SimpleIntegerProperty tripId;
    private SimpleStringProperty time;
    private SimpleStringProperty sourceDestination;
    private SimpleStringProperty assignedDriver;
    private SimpleStringProperty assignedVehicle;
    

    public BusTripSchedule(int scheduleId, LocalDate scheduleDate, int tripId, String time, String sourceDestination, String assignedDriver, String assignedVehicle) {
        this.scheduleId = new SimpleIntegerProperty(scheduleId);
        this.scheduleDate = new SimpleStringProperty(scheduleDate.toString());
        this.tripId = new SimpleIntegerProperty(tripId);
        this.time = new SimpleStringProperty(time);
        this.sourceDestination = new SimpleStringProperty(sourceDestination);
        this.assignedDriver = new SimpleStringProperty(assignedDriver);
        this.assignedVehicle = new SimpleStringProperty(assignedVehicle);
    }

    // Getters and Setters for Simple Properties
    public int getScheduleId() {
        return scheduleId.get();
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId.set(scheduleId);
    }

    public SimpleIntegerProperty scheduleIdProperty() {
        return scheduleId;
    }

    public String getScheduleDate() {
        return scheduleDate.get();
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate.set(scheduleDate.toString());
    }

    public SimpleStringProperty scheduleDateProperty() {
        return scheduleDate;
    }

}


