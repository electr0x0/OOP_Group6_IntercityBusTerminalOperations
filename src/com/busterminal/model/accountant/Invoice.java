/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.accountant;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author electr0
 */
public class Invoice {
    private String invoiceNum;
    private LocalDate invoiceDueDate;
    
    private String billToClientName,billToAdress;
    private double billToPhoneNumber;
    
    private String fromBusinessName,fromEmail,fromAdress;
    private double fromPhoneNo;
    
    private double subTotal, totalTax;
    
    private String invNote;
    
    private ArrayList<InvoiceService> serviceList;

    public Invoice(String invoiceNum, LocalDate invoiceDueDate, String billToClientName, String billToAdress, double billToPhoneNumber, String fromBusinessName, String fromEmail, String fromAdress, double fromPhoneNo, String invNote) {
        this.invoiceNum = invoiceNum;
        this.invoiceDueDate = invoiceDueDate;
        this.billToClientName = billToClientName;
        this.billToAdress = billToAdress;
        this.billToPhoneNumber = billToPhoneNumber;
        this.fromBusinessName = fromBusinessName;
        this.fromEmail = fromEmail;
        this.fromAdress = fromAdress;
        this.fromPhoneNo = fromPhoneNo;
        this.invNote = invNote;
        setTotal();
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }
    
    public void setTotal(){
        for(InvoiceService invServ: serviceList){
            subTotal += invServ.getItemTotal();
            totalTax += invServ.getServiceTaxAmount();
        }
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public LocalDate getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(LocalDate invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public String getBillToClientName() {
        return billToClientName;
    }

    public void setBillToClientName(String billToClientName) {
        this.billToClientName = billToClientName;
    }

    public String getBillToAdress() {
        return billToAdress;
    }

    public void setBillToAdress(String billToAdress) {
        this.billToAdress = billToAdress;
    }

    public double getBillToPhoneNumber() {
        return billToPhoneNumber;
    }

    public void setBillToPhoneNumber(double billToPhoneNumber) {
        this.billToPhoneNumber = billToPhoneNumber;
    }

    public String getFromBusinessName() {
        return fromBusinessName;
    }

    public void setFromBusinessName(String fromBusinessName) {
        this.fromBusinessName = fromBusinessName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }

    public double getFromPhoneNo() {
        return fromPhoneNo;
    }

    public void setFromPhoneNo(double fromPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    public String getInvNote() {
        return invNote;
    }

    public void setInvNote(String invNote) {
        this.invNote = invNote;
    }

    public ArrayList<InvoiceService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<InvoiceService> serviceList) {
        this.serviceList = serviceList;
    }
    
    
    
    
    
}
