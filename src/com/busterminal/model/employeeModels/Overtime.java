/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.employeeModels;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Overtime implements Serializable {
    String id;
    
    Boolean isOvertime;
    int overtimeHours;
    int overtimeRate;
    Boolean askedForOvertime;

    public Overtime(String id, Boolean isOvertime, int overtimeHours, int overtimeRate, Boolean askedForOvertime) {
        this.id = id;
        this.isOvertime = isOvertime;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.askedForOvertime = askedForOvertime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsOvertime() {
        return isOvertime;
    }

    public void setIsOvertime(Boolean isOvertime) {
        this.isOvertime = isOvertime;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public int getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(int overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Boolean getAskedForOvertime() {
        return askedForOvertime;
    }

    public void setAskedForOvertime(Boolean askedForOvertime) {
        this.askedForOvertime = askedForOvertime;
    }

    
    
    
}
