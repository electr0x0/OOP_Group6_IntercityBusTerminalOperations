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
import java.util.ArrayList;

public class Ticket implements Serializable {
    private String ticketId;
    private Passenger passenger; // Passenger object who bought the ticket
    private BusSchedule schedule;
    private int seatNumber;
    private double price;
    private LocalDateTime bookingTime;
    private String bookingStatus; // "Confirmed" or "Cancelled" Status will be here (By default it will remain as Confirmed)
    private int ticketQty;
    private ArrayList<String> seatNumber1;
    private DummyClassForTableViewSchedule dummy;

    public Ticket(String ticketId, Passenger passenger, BusSchedule schedule, int seatNumber, int ticketQty) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.schedule = schedule;
        this.seatNumber = seatNumber;
        this.bookingTime = LocalDateTime.now();
        this.bookingStatus = "Confirmed";
        this.ticketQty = ticketQty;
    }

    public Ticket(String ticketId, Passenger passenger, DummyClassForTableViewSchedule dummy,  ArrayList<String> seatNumber1,int ticketQty) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.dummy = dummy;    
        this.seatNumber1 = seatNumber1;
        this.ticketQty= ticketQty;
    }
    
    public void setTicketPrice(String busType, String source, String destination){
        // Based on the source and destination ticket price will vary and busType as well (such as AC or NON-AC)
    }
    
    

    public void cancelTicket() { // If Passenger wants to cancel 
        this.bookingStatus = "Cancelled";
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", passenger=" + passenger + ", ticketQty=" + ticketQty + ", seatNumber1=" + seatNumber1 + ", dummy=" + dummy + '}';
    }

}

