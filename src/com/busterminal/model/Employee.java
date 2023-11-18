/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.time.LocalDate;

/**
 *
 * @author electr0
 */
public class Employee extends User {
    int Salary;
    String empType;

    public Employee(int Salary, String empType, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(firstname, lastname, gender, email, phonenumber, dateofbirth, address);
        this.Salary = Salary;
        this.empType = empType;
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

    
    
    
}
