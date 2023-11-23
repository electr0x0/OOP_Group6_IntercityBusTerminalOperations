/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.Serializable;

/**
 *
 * @author electr0
 */
public class Passenger implements Serializable{
    
    private String name,email;
    //private ArrayList<String> seatNumber;

    public Passenger(String name, String email, int contactNum) {
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    
    private  int contactNum;

    

    

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


