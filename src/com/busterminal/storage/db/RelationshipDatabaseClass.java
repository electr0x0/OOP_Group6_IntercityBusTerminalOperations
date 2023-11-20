/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.storage.db;

import java.util.ArrayList;

/**
 *
 * @author electr0
 */

public class RelationshipDatabaseClass {

    private static RelationshipDatabaseClass instance;

    private ArrayList<String> allLocations;
    private ArrayList<String> allTimes;
    private ArrayList<String> allBusStands;

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
}

                                        