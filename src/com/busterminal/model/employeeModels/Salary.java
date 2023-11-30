/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.employeeModels;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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
    
    int incresedSalary;
    String reason;

    public Salary(String id, int salary) {
        this.id = id;
        this.salary = salary;
        this.isPaid = false;
    }

    public Salary(String id, int salary, Boolean askedForSalary, Boolean isPaid, LocalDate lastPaid, int incresedSalary, String reason) {
        this.id = id;
        this.salary = salary;
        this.askedForSalary = askedForSalary;
        this.isPaid = isPaid;
        this.lastPaid = lastPaid;
        this.incresedSalary = incresedSalary;
        this.reason = reason;
    }

    public int getIncresedSalary() {
        return incresedSalary;
    }

    public void setIncresedSalary(int incresedSalary) {
        this.incresedSalary = incresedSalary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

   public static void writeSalaryToFile(String filename, Salary salary) {
        String filePath = filename;

        // Read existing salary objects from the file
        ArrayList<Salary> existingSalaries = readSalaryFromFile(filePath);

        // Add the new salary object
        existingSalaries.add(salary);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // Write the entire list back to the file
            outputStream.writeObject(existingSalaries);
            System.out.println("Salary written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static ArrayList<Salary> readSalaryFromFile(String filename) {
        ArrayList<Salary> existingSalaries = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            existingSalaries = (ArrayList<Salary>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return existingSalaries;
    }


    
}
