/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author electr0
 */
public class DummyTableViewAllAvailableLocationTimeSlots {
    private SimpleStringProperty location;
    private SimpleStringProperty time;
    private SimpleStringProperty busStand;

    public DummyTableViewAllAvailableLocationTimeSlots(String location, String time, String busStand) {
        this.location = new SimpleStringProperty(location);
        this.time = new SimpleStringProperty(time);
        this.busStand = new SimpleStringProperty(busStand);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
    
    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.location.set(time);
    }
    
    public String getbusStand() {
        return busStand.get();
    }

    public void setbusStand(String busStand) {
        this.busStand.set(busStand);
    }
    
}
