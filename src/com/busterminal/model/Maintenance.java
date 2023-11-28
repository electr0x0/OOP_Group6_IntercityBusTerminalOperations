/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author UseR
 */
public class Maintenance implements Serializable {
    
    private String busId;
    private String maintenanceType;
    private String  comment;
    private LocalDate reqDate;
    private String details;
    

    public Maintenance() {
    }

    public Maintenance(String busId, String maintenanceType, String comment, LocalDate reqDate, String details) {
        this.busId = busId;
        this.maintenanceType = maintenanceType;
        this.comment = comment;
        this.reqDate = reqDate;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

   

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

  

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReqDate() {
        return reqDate;
    }

    public void setReqDate(LocalDate reqDate) {
        this.reqDate = reqDate;
    }
    
    
}
