/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class HumanResource extends Employee {
    
    private ArrayList<Employee> MyEmps;

    public HumanResource(int Salary, String empType, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(Salary, empType, firstname, lastname, gender, email, phonenumber, dateofbirth, address);
        this.id = generateID(firstname,MyEmps);
        MyEmps.add(this);
    }
    
    

 
   
}
