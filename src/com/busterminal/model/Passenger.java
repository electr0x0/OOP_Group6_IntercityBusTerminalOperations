/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.util.ArrayList;

/**
 *
 * @author electr0
 */
public class Passenger {
    
    private String name,email;
    private ArrayList<String> seatNumber;

    public Passenger(String name, String email, int contactNum) {
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    public ArrayList<String> getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(ArrayList<String> seatNumber) {
        this.seatNumber = seatNumber;
    }
    private  int contactNum;

    public Passenger(String name, String email, ArrayList<String> seatNumber, int contactNum) {
        this.name = name;
        this.email = email;
        this.seatNumber = seatNumber;
        this.contactNum = contactNum;
    }

    

    public Passenger() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  
   

    public int getContactNum() {
        return contactNum;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }
}


