/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.storage.db;

import com.busterminal.model.Bus;
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
   
   
    
     private static final long serialVersionUID = 1L;

    // Private constructor to prevent instantiation
    private RelationshipDatabaseClass() {}

    // Static method to get the singleton instance
    public static RelationshipDatabaseClass getInstance() {
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
    }

    public ArrayList<String> getAllTimes() {
        return allTimes;
    }

    public void setAllTimes(ArrayList<String> allTimes) {
        this.allTimes = allTimes;
    }

    public ArrayList<String> getAllBusStands() {
        return allBusStands;
    }

    public void setAllBusStands(ArrayList<String> allBusStands) {
        this.allBusStands = allBusStands;
    }

    public ArrayList<Bus> getAllAvailableBuses() {
        System.out.println("Loaded All Buses" + allAvailableBuses);
        return allAvailableBuses;
    }

    public void setAllAvailableBuses(ArrayList<Bus> allAvailableBuses) {
        this.allAvailableBuses = allAvailableBuses;
        System.out.println(allAvailableBuses);
    }
    
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GlobalClassDatabase.bin"))) {
            out.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public static void loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("GlobalClassDatabase.bin"))) {
            instance = (RelationshipDatabaseClass) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
}

                                        