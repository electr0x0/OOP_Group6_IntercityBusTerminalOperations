/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

/**
 *
 * @author electr0
 */

import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BusTrip implements Serializable {
    private String tripID;
    private Bus bus; // Bus object reference for strong link
    private String source;
    private String destination;
    private String timeSlot;
    private String fleetType;
    private int adultFare, childrenFare, weekendFare;
    private double distance;
    private String driver;
    private boolean tripStatus = true;
    
    private int tripBusID;

    public BusTrip(String tripID, Bus bus, String source, String destination, String timeSlot, int adultFare, int childrenFare, int weekendFare, double distance, String driver) {
        this.tripID = tripID;
        this.bus = bus;
        this.source = source;
        this.destination = destination;
        this.timeSlot = timeSlot;
        this.fleetType = bus.getBusType();
        this.adultFare = adultFare;
        this.childrenFare = childrenFare;
        this.weekendFare = weekendFare;
        this.distance = distance;
        this.driver = driver;
        this.tripBusID = bus.getBusId();
        bus.addOccupiedTimeSlot(timeSlot);
        updateAvailableTimeSlot();
        updateBusInstanceInObjectDB();
        
    }
    private void updateAvailableTimeSlot(){
        for (String time: RelationshipDatabaseClass.getInstance().getAllTimes()){
            if (!time.equals(timeSlot) && !bus.getOccupiedTimeSlots().contains(time)){
                bus.addAvailableTimeSlot(time);
            }
        }
    }
    
    public void updateBusInstanceInObjectDB() {
        
        ArrayList<Bus> busesDB = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
        
        for (int i = 0; i < busesDB.size(); i++) {
            if (busesDB.get(i).getBusId() == bus.getBusId()) {
                busesDB.set(i, bus); // Replace the bus object at the found index
                break;
            }
        }
        
        RelationshipDatabaseClass.getInstance().setAllAvailableBuses(busesDB);
    }

    public boolean isTripStatus() {
        return tripStatus;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getFleetType() {
        return fleetType;
    }

    public void setFleetType(String fleetType) {
        this.fleetType = fleetType;
    }

    public int getAdultFare() {
        return adultFare;
    }

    public void setAdultFare(int adultFare) {
        this.adultFare = adultFare;
    }

    public int getChildrenFare() {
        return childrenFare;
    }

    public void setChildrenFare(int childrenFare) {
        this.childrenFare = childrenFare;
    }

    public int getWeekendFare() {
        return weekendFare;
    }

    public void setWeekendFare(int weekendFare) {
        this.weekendFare = weekendFare;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    

    public void setTripStatus(boolean tripStatus) {
        this.tripStatus = tripStatus;
    }


    @Override
    public String toString() {
        return "ID- "+tripID+" "+source+"-"+destination+"-"+fleetType+" Cap-"+bus.getNumberOfSeats();
    }

    public int getTripBusID() {
        return tripBusID;
    }

    public void setTripBusID(int tripBusID) {
        this.tripBusID = tripBusID;
    }


    
    
    
    
    
    
    


}
 

