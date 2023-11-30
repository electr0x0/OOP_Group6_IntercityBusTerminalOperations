/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.accountant;

/**
 *
 * @author electr0
 */
public class InvoiceService {
    
    private String serviceName, serviceDescription;
    private int serviceRate;
    private double serviceTaxPercent,serviceQty;
    
    
    private double itemTotal;
    private double serviceTaxAmount;

    public InvoiceService(String serviceName, String serviceDescription, double serviceQty, int serviceRate, double serviceTaxPercent) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceQty = serviceQty;
        this.serviceRate = serviceRate;
        this.serviceTaxPercent = serviceTaxPercent;
        CalculateValues();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceQty(double serviceQty) {
        this.serviceQty = serviceQty;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServiceQty() {
        return serviceQty;
    }

    public void setServiceQty(int serviceQty) {
        this.serviceQty = serviceQty;
    }

    public int getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(int serviceRate) {
        this.serviceRate = serviceRate;
    }

    public double getServiceTaxPercent() {
        return serviceTaxPercent;
    }

    public void setServiceTaxPercent(double serviceTaxPercent) {
        this.serviceTaxPercent = serviceTaxPercent;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public double getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(double serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }
    
    
    

    private void CalculateValues(){
        itemTotal = serviceQty * serviceRate;
        serviceTaxAmount = itemTotal * (serviceTaxPercent/100.0);
    }
}
