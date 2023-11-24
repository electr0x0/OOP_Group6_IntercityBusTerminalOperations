/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.util.ArrayList;

/**
 *
 * @author electr0
 */

public class Bus {
    private String busId;
    private int numberOfSeats;
    private String busType;
    private String busModel;
    private String manufacturer;
    private int yearOfManufacture;
    private ArrayList<BusSchedule> schedules;

    public Bus(String busId, int numberOfSeats, String busType, String busModel, String manufacturer, int yearOfManufacture) {
        this.busId = busId;
        this.numberOfSeats = numberOfSeats;
        this.busType = busType;
        this.busModel = busModel;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.schedules = new ArrayList<>();
    }

    public void addSchedule(BusSchedule schedule) {
        schedules.add(schedule);
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
    
    

}

