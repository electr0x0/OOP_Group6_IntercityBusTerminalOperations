package com.busterminal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author electr0
 */
public class Employee extends User {
    int Salary;
    String empType;
    String id;
    
    ArrayList<Employee> myEmployees = new ArrayList<>();

    public Employee(int Salary, String empType, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(firstname, lastname, gender, email, phonenumber, dateofbirth, address);
        this.Salary = Salary;
        this.empType = empType;
        this.id = generateID(firstname,myEmployees);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Employee> getMyEmployees() {
        return myEmployees;
    }

    
    @Override
    public String toString() {
        return "Employee{" + "Salary=" + Salary + ", empType=" + empType + '}';
    }
    
    public static String generateID(String input, ArrayList<Employee> employees) {
        int asciiValue = (int) input.charAt(0);
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("MMdd"));
        String time = now.format(DateTimeFormatter.ofPattern("HHmmss"));

        // Generate the initial ID
        String generatedID = String.format("%d%s%s", asciiValue, date, time);

        // If the employees list is not initialized or empty, return the initial ID
        if (employees == null || employees.isEmpty()) {
            return generatedID;
        }

        // Check for uniqueness
        int counter = 1;
        String tempID = generatedID;
        while (true) {
            boolean unique = true;
            for (Employee emp : employees) {
                if (emp.getId().equals(tempID)) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                return tempID;
            }
            tempID = generatedID + counter++;
        }
    }

    
    
    

}
