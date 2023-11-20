
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;
import mainPkg.Interface.User;


public class Client extends User implements Serializable {

    public Client(int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        super(iD, firstName, lastName, address, email, dateOfBirth, password, phoneNumber, gender);
    }

    

   
}