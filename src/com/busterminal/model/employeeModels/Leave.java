/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.employeeModels;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Leave implements Serializable {
    String id;
    Boolean onLeave;
    Boolean askedForLeave;
    String leaveReason;
    
    LocalDate leaveStartDate;
    LocalDate leaveEndDate;

    public Leave(String id, Boolean onLeave, Boolean askedForLeave, String leaveReason) {
        this.id = id;
        this.onLeave = onLeave;
        this.askedForLeave = askedForLeave;
        this.leaveReason = leaveReason;
    }

    public Leave(String id, Boolean onLeave, Boolean askedForLeave, String leaveReason, LocalDate leaveStartDate, LocalDate leaveEndDate) {
        this.id = id;
        this.onLeave = onLeave;
        this.askedForLeave = askedForLeave;
        this.leaveReason = leaveReason;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
    }
    
    

    public LocalDate getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(LocalDate leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public LocalDate getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(LocalDate leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

   
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(Boolean onLeave) {
        this.onLeave = onLeave;
    }

    public Boolean getAskedForLeave() {
        return askedForLeave;
    }

    public void setAskedForLeave(Boolean askedForLeave) {
        this.askedForLeave = askedForLeave;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
    
    
}
