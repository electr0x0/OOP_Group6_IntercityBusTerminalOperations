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
public class Salary implements Serializable {
    String id;
    int salary;
    Boolean askedForSalary;
    Boolean isPaid;
    LocalDate lastPaid;

    public Salary(String id, int salary, Boolean askedForSalary, Boolean isPaid, LocalDate lastPaid) {
        this.id = id;
        this.salary = salary;
        this.askedForSalary = askedForSalary;
        this.isPaid = isPaid;
        this.lastPaid = lastPaid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Boolean getAskedForSalary() {
        return askedForSalary;
    }

    public void setAskedForSalary(Boolean askedForSalary) {
        this.askedForSalary = askedForSalary;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public LocalDate getLastPaid() {
        return lastPaid;
    }

    public void setLastPaid(LocalDate lastPaid) {
        this.lastPaid = lastPaid;
    }

   
    
}
