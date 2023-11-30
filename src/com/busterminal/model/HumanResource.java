/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class HumanResource extends Employee {
    
    ArrayList<Employee> myEmployees = new ArrayList<>();

    public HumanResource(int Salary, String empType, String firstname, String lastname, String gender, String email, String phonenumber, LocalDate dateofbirth, String address) {
        super(Salary, empType, firstname, lastname, gender, email, phonenumber, dateofbirth, address);
    }

    

    
    
   
    
}
