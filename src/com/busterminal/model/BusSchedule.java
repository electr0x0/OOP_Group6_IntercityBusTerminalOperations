/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

/**
 *
 * @author electr0
 */

import java.io.Serializable;
import java.time.LocalDateTime;

public class BusSchedule implements Serializable {
    private String scheduleId;
    private Bus bus; // Bus object reference for strong link
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String fleetType;
    private int adultFare, childrenFare, weekendFare;
    private float distance;
    private String driver;

    public BusSchedule(String scheduleId, Bus bus, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, String fleetType, int adultFare, int childrenFare, int weekendFare, float distance) {
        this.scheduleId = scheduleId;
        this.bus = bus;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fleetType = bus.getBusType();
        this.adultFare = adultFare;
        this.childrenFare = childrenFare;
        this.weekendFare = weekendFare;
        this.distance = distance;
    }
    
    
    
    
    


}
 

