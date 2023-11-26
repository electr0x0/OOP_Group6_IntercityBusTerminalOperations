
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author UseR
 */
public class DummyTripHistory implements Serializable {
    
    private int scheduleId;
    private LocalDate scheduleDate;
    private String route;
    private String tripStatus;
    private String assignedDriver;
    private String assignedVehicle;

    public DummyTripHistory(int scheduleId, LocalDate scheduleDate, String route, String tripStatus, String assignedDriver, String assignedVehicle) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.route = route;
        this.tripStatus = tripStatus;
        this.assignedDriver = assignedDriver;
        this.assignedVehicle = assignedVehicle;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public DummyTripHistory() {
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
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

   

    @Override
    public String toString() {
        return "DummyTripHistory{" + "scheduleDate=" + scheduleDate + ", route=" + route + ", tripStatus=" + tripStatus + ", assignedDriver=" + assignedDriver + ", assignedVehicle=" + assignedVehicle + '}';
    }
    
    
    
    
}
