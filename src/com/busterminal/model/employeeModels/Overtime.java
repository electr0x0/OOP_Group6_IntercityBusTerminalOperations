/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.employeeModels;

import static com.busterminal.views.Employee.EmployeeDashboardController.readOvertimeFromFile;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

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
    String overtimeReason;

    public Overtime(String id) {
        this.id = id;
        this.isOvertime = false;
    }

    public Overtime(String id, Boolean isOvertime, int overtimeHours, int overtimeRate, Boolean askedForOvertime, String overtimeReason) {
        this.id = id;
        this.isOvertime = isOvertime;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.askedForOvertime = askedForOvertime;
        this.overtimeReason = overtimeReason;
    }

    

    public String getOvertimeReason() {
        return overtimeReason;
    }

    public void setOvertimeReason(String overtimeReason) {
        this.overtimeReason = overtimeReason;
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

    public static void writeOvertimeToFile(String filename, Overtime overtime) {
        String filePath = filename;

        // Read existing overtime objects from the file
        ArrayList<Overtime> existingOvertimes = readOvertimeFromFile(filePath);

        // Add the new overtime object
        existingOvertimes.add(overtime);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // Write the entire list back to the file
            outputStream.writeObject(existingOvertimes);
            System.out.println("Overtime written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Overtime> readOvertimeFromFile(String filename) {
        ArrayList<Overtime> existingOvertimes = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            existingOvertimes = (ArrayList<Overtime>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return existingOvertimes;
    }
    
    public static ArrayList<Overtime> deleteOvertimeById(String filename, String idToDelete) {
        ArrayList<Overtime> existingOvertimes = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            existingOvertimes = (ArrayList<Overtime>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return existingOvertimes; // Return the original list if an exception occurs while reading
        }

        // Remove the Overtime object with the given ID
        existingOvertimes.removeIf(overtime -> overtime.getId().equals(idToDelete));

        return existingOvertimes;
    }

    public static ArrayList<Salary> deleteSalaryById(String filename, String idToDelete) {
        ArrayList<Salary> existingSalaries = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            existingSalaries = (ArrayList<Salary>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return existingSalaries; // Return the original list if an exception occurs while reading
        }

        // Remove the Salary object with the given ID
        existingSalaries.removeIf(salary -> salary.getId().equals(idToDelete));

        return existingSalaries;
    }

    
    
}
