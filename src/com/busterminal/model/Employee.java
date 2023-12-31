/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import com.busterminal.model.employeeModels.Leave;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.model.employeeModels.Resignation;
import com.busterminal.model.employeeModels.Salary;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author electr0
 */
public class Employee extends User {
    int Salary;
    String empType;
    String id;
   
    Salary salStatus;
    Overtime overTime;
    Resignation resignation;
    Leave holiday;
    
    Boolean onLeave;
    Boolean isPaid;
    LocalDate lastPaid;
    
    String performance;
    

    public Employee(int Salary, String empType, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(firstname, lastname, gender, email, phonenumber, dateofbirth, address);
        this.Salary = Salary;
        this.empType = empType;
        this.id = generateID(firstname);
        this.salStatus = new Salary(this.id,this.Salary);
        this.overTime = new Overtime(this.id);
        this.holiday = new Leave(this.id,false,false,"");
        this.resignation = new Resignation(this.id,false,"");
        this.onLeave = false;
        this.isPaid = false;
    }

    public Employee(int Salary, String empType, String id, Salary salStatus, Overtime overTime, Resignation resignation, Leave holiday, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(firstname, lastname, gender, email, phonenumber, dateofbirth, address);
        this.Salary = Salary;
        this.empType = empType;
        this.id = id;
        this.salStatus = salStatus;
        this.overTime = overTime;
        this.resignation = resignation;
        this.holiday = holiday;
        this.onLeave = holiday.getOnLeave();
        this.isPaid = salStatus.getIsPaid();
        this.lastPaid = salStatus.getLastPaid();
    }

    public Salary getSalStatus() {
        return salStatus;
    }

    public void setSalStatus(Salary salStatus) {
        this.salStatus = salStatus;
    }

    public Overtime getOverTime() {
        return overTime;
    }

    public void setOverTime(Overtime overTime) {
        this.overTime = overTime;
    }

    public Resignation getResignation() {
        return resignation;
    }

    public void setResignation(Resignation resignation) {
        this.resignation = resignation;
    }

    public Leave getHoliday() {
        return holiday;
    }

    public void setHoliday(Leave holiday) {
        this.holiday = holiday;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }
    
    
    
    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(Boolean onLeave) {
        this.onLeave = onLeave;
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
    
    
    
    
    private boolean calculatePayment(LocalDate lastPayed) {
    LocalDate currentDate = LocalDate.now();
    
    int monthsDiff = currentDate.getMonthValue() - lastPayed.getMonthValue() + (currentDate.getYear() - lastPayed.getYear()) * 12;
    int daysInCurrentMonth = currentDate.lengthOfMonth();

    if (monthsDiff >= 1) {
        return true;
    } else {
        return currentDate.getDayOfMonth() <= daysInCurrentMonth;
    }
}

    
   /* private void askForOverTime(int expectedHours){
        this.AppliedForOvertime = true;
        this.overTimeHours = expectedHours;
    }*/

    @Override
    public String toString() {
        return "Employee{" + "Salary=" + Salary + ", empType=" + empType + ", id=" + id + ", salStatus=" + salStatus + ", overTime=" + overTime + ", resignation=" + resignation + ", holiday=" + holiday + '}';
    }
    
    
    
    public static String generateID(String input) {
        int asciiValue = (int) input.charAt(0);
        LocalDateTime now = LocalDateTime.now();

        // Get date and time components individually
        String date = String.format("%02d%02d", now.getMonthValue(), now.getDayOfMonth());
        String time = String.format("%02d%02d%02d", now.getHour(), now.getMinute(), now.getSecond());

        // Generate the initial ID
        return String.format("%d%s%s", asciiValue, date, time);
    }

    
        public static ArrayList<Employee> readEmployeesFromFile(String filename) {
            ArrayList<Employee> employees = new ArrayList<>();

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
                // Read all existing employees from the file
                employees = (ArrayList<Employee>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions if the file doesn't exist or other issues
                e.printStackTrace();
            }

            return employees;
        }
        
        public static void writeEmployeesToFile(String filename, ArrayList<Employee> employees) {
            String filePath = filename;

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
                // Write the entire list back to the file
                outputStream.writeObject(employees);
                System.out.println("Employees written to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    

    public Salary findSalaryById(ArrayList<Salary> salaries, String id) {
        for (Salary salary : salaries) {
            if (salary.getId().equals(id)) {
                return salary;
            }
        }
        return null; // Return null if the Salary object with the specified ID is not found
    }
    
    public Overtime findOvertimeById(ArrayList<Overtime> overtimes, String id) {
        for (Overtime overtime : overtimes) {
            if (overtime.getId().equals(id)) {
                return overtime;
            }
        }
        return null; // Return null if the Overtime object with the specified ID is not found
    }
    
    public Resignation findResignationById(ArrayList<Resignation> resignations, String id) {
        for (Resignation resignation : resignations) {
            if (resignation.getId().equals(id)) {
                return resignation;
            }
        }
        return null; // Return null if the Resignation object with the specified ID is not found
    }
    
    public Leave findLeaveById(ArrayList<Leave> leaves, String id) {
        for (Leave leave : leaves) {
            if (leave.getId().equals(id)) {
                return leave;
            }
        }
        return null; // Return null if the Leave object with the specified ID is not found
    }
    
    public static void setCurrentUser(String email){
        ArrayList<Employee> empList = readEmployeesFromFile("MyEmployee.bin");
        for(Employee emp: empList){
            if(emp.getEmail().equals(email)){
                RelationshipDatabaseClass.getInstance().setCurrentLoggedIn(emp.getId());
                return;
            }
        }
    }
    
    
}
