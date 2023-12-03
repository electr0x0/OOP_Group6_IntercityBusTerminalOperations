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

public class Transaction implements Serializable{
    LocalDate txnDate;
    String txnType;
    double txnAmount;
    String txnStatus, txnParticular;

    public Transaction(LocalDate txnDate, String txnType, double txnAmount, String txnStatus, String txnParticular) {
        this.txnDate = txnDate;
        this.txnType = txnType;
        this.txnAmount = txnAmount;
        this.txnStatus = txnStatus;
        this.txnParticular = txnParticular;
    }

    public LocalDate getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getTxnParticular() {
        return txnParticular;
    }

    public void setTxnParticular(String txnParticular) {
        this.txnParticular = txnParticular;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction{");
        sb.append("txnDate=").append(txnDate);
        sb.append(", txnType=").append(txnType);
        sb.append(", txnAmount=").append(txnAmount);
        sb.append(", txnStatus=").append(txnStatus);
        sb.append(", txnParticular=").append(txnParticular);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
