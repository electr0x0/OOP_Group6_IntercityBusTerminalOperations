/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.accountant;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author electr0
 */
public class RefundRequest implements Serializable{
    private LocalDate date;
    private String passengerName;
    private int ticketID;
    private int amount;
    private String status = "Pending",refundReason;

    public RefundRequest(LocalDate date, String passengerName, int ticketID, int amount, String refundReason) {
        this.date = date;
        this.passengerName = passengerName;
        this.ticketID = ticketID;
        this.amount = amount;
        this.refundReason = refundReason;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
    
    
}
