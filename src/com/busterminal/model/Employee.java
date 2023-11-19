package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;
import mainPkg.Interface.User;

public class Employee extends User implements Serializable {

    private String designation;
    private LocalDate dateofJoining;

    public Employee(String designation, LocalDate dateofJoining, int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        super(iD, firstName, lastName, address, email, dateOfBirth, password, phoneNumber, gender);
        this.designation = designation;
        this.dateofJoining = dateofJoining;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String Designation) {
        this.designation = Designation;
    }

    public LocalDate getDateofJoining() {
        return dateofJoining;
    }

    public void setDateofJoining(LocalDate DateofJoining) {
        this.dateofJoining = DateofJoining;
    }

    public void submitReport() {
    }
    
    

}
