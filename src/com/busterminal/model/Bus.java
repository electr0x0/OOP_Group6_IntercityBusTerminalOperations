/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author electr0
 */

public class Bus implements Serializable {
    private int busId;
    private int numberOfSeats;
    private String busType;
    private int busRegNum;
    private String manufacturer;
    private int yearOfManufacture;
    private ArrayList<BusSchedule> schedules;
    private ArrayList<String> assingedDrivers;
    private boolean mtStatus;

    public Bus(int busId, int numberOfSeats, String busType, int busRegNum, String manufacturer, int yearOfManufacture, ArrayList<String> assingedDrivers) {
        this.busId = busId;
        this.numberOfSeats = numberOfSeats;
        this.busType = busType;
        this.busRegNum = busRegNum;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.schedules = new ArrayList<>();
        this.assingedDrivers = assingedDrivers;
    }

    @Override
    public String toString() {
        return "Bus{" + "busId=" + busId + ", numberOfSeats=" + numberOfSeats + ", busType=" + busType + ", busRegNum=" + busRegNum + ", manufacturer=" + manufacturer + ", yearOfManufacture=" + yearOfManufacture + ", schedules=" + schedules + ", assingedDrivers=" + assingedDrivers + '}';
    }

    public void addSchedule(BusSchedule schedule) {
        schedules.add(schedule);
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
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

    public int getBusModel() {
        return busRegNum;
    }

    public void setbusRegNum(int busRegNum) {
        this.busRegNum = busRegNum;
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

    public ArrayList<String> getAssingedDrivers() {
        return assingedDrivers;
    }

    public void setAssingedDrivers(ArrayList<String> assingedDrivers) {
        this.assingedDrivers = assingedDrivers;
    }

    public int getBusRegNum() {
        return busRegNum;
    }

    public void setBusRegNum(int busRegNum) {
        this.busRegNum = busRegNum;
    }

    public boolean isMtStatus() {
        return mtStatus;
    }

    public void setMtStatus(boolean mtStatus) {
        this.mtStatus = mtStatus;
    }
    
    

}

