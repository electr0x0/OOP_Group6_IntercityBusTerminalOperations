/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

/**
 *
 * @author electr0
 */

import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket implements Serializable {
    private String ticketId;
    private Passenger passenger; // Passenger object who bought the ticket
    private BusTripSchedule schedule;
    private int seatNumber;
    private double price;
    private LocalDateTime bookingTime;
    private String bookingStatus; // "Confirmed" or "Cancelled" Status will be here (By default it will remain as Confirmed)
    private int ticketQty;
    private ArrayList<String> seatNumber1;
    private DummyClassForTableViewSchedule dummy;
    private String purchaseDate;

    public Ticket(String ticketId, Passenger passenger, BusTripSchedule schedule, int seatNumber, int ticketQty) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.schedule = schedule;
        this.seatNumber = seatNumber;
        this.bookingTime = LocalDateTime.now();
        this.bookingStatus = "Confirmed";
        this.ticketQty = ticketQty;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Ticket(String ticketId, Passenger passenger, DummyClassForTableViewSchedule dummy,  ArrayList<String> seatNumber1,int ticketQty,String bookingStatus, String purchaseDate ) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.dummy = dummy;    
        this.seatNumber1 = seatNumber1;
        this.ticketQty= ticketQty;
        this.bookingStatus = bookingStatus;
        this.purchaseDate = purchaseDate;
        autoCreateTransaciton();
        
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

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public BusTripSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(BusTripSchedule schedule) {
        this.schedule = schedule;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getTicketQty() {
        return ticketQty;
    }

    public void setTicketQty(int ticketQty) {
        this.ticketQty = ticketQty;
    }

    public ArrayList<String> getSeatNumber1() {
        return seatNumber1;
    }

    public void setSeatNumber1(ArrayList<String> seatNumber1) {
        this.seatNumber1 = seatNumber1;
    }

    public DummyClassForTableViewSchedule getDummy() {
        return dummy;
    }

    public void setDummy(DummyClassForTableViewSchedule dummy) {
        this.dummy = dummy;
    }
    
    public void autoCreateTransaciton(){
        double totalAmnt = dummy.getAdultFare() * this.ticketQty;
        String sourceToDesti = dummy.getSource()+"-"+dummy.getDestination();
        Transaction ticketTransacion = new Transaction(LocalDate.now(),"TICKET-SALE",totalAmnt,"Paid",sourceToDesti);
        RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(ticketTransacion);
    }

}

