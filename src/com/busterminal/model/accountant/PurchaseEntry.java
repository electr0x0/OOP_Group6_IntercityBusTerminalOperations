/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.accountant;

import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author electr0
 */
public class PurchaseEntry implements Serializable{
    private LocalDate purchaseDate;
    private String seller, item, status;
    private double qty, rate, totalAmount;
    private Transaction currentTxn;

    public PurchaseEntry(LocalDate purchaseDate, String seller, String item, double qty, double rate, String status) {
        this.purchaseDate = purchaseDate;
        this.seller = seller;
        this.item = item;
        this.qty = qty;
        this.rate = rate;
        this.status = status;
        this.totalAmount = qty*rate;
        updateTransaction();
    }
    
    private void updateTransaction(){
        currentTxn = new Transaction(purchaseDate, "Purchase", totalAmount, status, item);
        RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(currentTxn);
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
   
    
}
