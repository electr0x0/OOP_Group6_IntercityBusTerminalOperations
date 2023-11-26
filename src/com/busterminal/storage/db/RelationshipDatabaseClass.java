/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.storage.db;

import com.busterminal.model.Bus;
import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author electr0
 */

public class RelationshipDatabaseClass implements Serializable {

    private static RelationshipDatabaseClass instance;

    private ArrayList<String> allLocations;
    private ArrayList<String> allTimes;
    private ArrayList<String> allBusStands;
    private ArrayList<Bus> allAvailableBuses;
    private int busIdCounter;
    private ArrayList<BusTrip> allTripList;
    private ArrayList<BusTripSchedule> allAvailableTripSchedules;
    private int currentScheduleID;

    // Private constructor to prevent instantiation
    private RelationshipDatabaseClass() {}

    // Static method to get the singleton instance
    public static RelationshipDatabaseClass getInstance() {
        loadFromFile();
        if (instance == null) {
            instance = new RelationshipDatabaseClass();
        }
        return instance;
    }

    public ArrayList<String> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(ArrayList<String> allLocations) {
        this.allLocations = allLocations;
        saveToFile();
    }

    public ArrayList<String> getAllTimes() {
        return allTimes;
    }

    public void setAllTimes(ArrayList<String> allTimes) {
        this.allTimes = allTimes;
        saveToFile();
    }

    public ArrayList<String> getAllBusStands() {
        
        return allBusStands;
    }

    public void setAllBusStands(ArrayList<String> allBusStands) {
        this.allBusStands = allBusStands;
        saveToFile();
    }

    public ArrayList<Bus> getAllAvailableBuses() {
        System.out.println("Loaded All Buses" + allAvailableBuses);
        return allAvailableBuses;
    }

    public void setAllAvailableBuses(ArrayList<Bus> allAvailableBuses) {
        this.allAvailableBuses = allAvailableBuses;
        System.out.println(allAvailableBuses);
        saveToFile();
    }

    public int getBusIdCounter() {
        System.out.println("Bus Id Counter loaded from ObjectDatabase");
        return busIdCounter;
    }

    public void setBusIdCounter(int busIdCounter) {
        System.out.println("Bus Id counter saved to ObjectDatabase");
        this.busIdCounter = busIdCounter;
        saveToFile();
    }
    
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GlobalClassDatabase.bin"))) {
            out.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("GlobalClassDatabase.bin"))) {
            instance = (RelationshipDatabaseClass) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BusTrip> getAllTripList() {
        return allTripList;
    }

    public void setAllTripList(ArrayList<BusTrip> allTripList) {
        this.allTripList = allTripList;
        saveToFile();
    }

    public ArrayList<BusTripSchedule> getAllAvailableTripSchedules() {
        return allAvailableTripSchedules;
    }

    public void setAllAvailableTripSchedules(ArrayList<BusTripSchedule> allAvailableTripSchedules) {
        this.allAvailableTripSchedules = allAvailableTripSchedules;
        saveToFile();
    }

    public int getCurrentScheduleID() {
        return currentScheduleID;
    }

    public void setCurrentScheduleID(int currentScheduleID) {
        this.currentScheduleID = currentScheduleID;
        saveToFile();
    }
    
    
    
}

