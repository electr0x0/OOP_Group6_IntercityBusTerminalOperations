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
public class Resignation implements Serializable {
    String id;
    Boolean askedForResignation;
    String resignationReason;

    public Resignation(String id, Boolean askedForResignation, String resignationReason) {
        this.id = id;
        this.askedForResignation = askedForResignation;
        this.resignationReason = resignationReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAskedForResignation() {
        return askedForResignation;
    }

    public void setAskedForResignation(Boolean askedForResignation) {
        this.askedForResignation = askedForResignation;
    }

    public String getResignationReason() {
        return resignationReason;
    }

    public void setResignationReason(String resignationReason) {
        this.resignationReason = resignationReason;
    }
    
    
}
