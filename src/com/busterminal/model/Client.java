
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;


public class Client extends User implements Serializable {

    public Client(int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        super(iD, firstName, lastName, address, email, dateOfBirth, password, phoneNumber, gender);
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

   
}