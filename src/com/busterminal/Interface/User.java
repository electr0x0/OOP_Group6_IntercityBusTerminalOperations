package com.busterminal.Interface;
import java.io.Serializable;
import java.time.LocalDate;


public abstract class User implements Serializable {
    
    protected int iD;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    protected String password;
    private String phoneNumber;
    private String gender;

    public User(int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        this.iD = iD;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    
    
   
    

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return iD;
    }

    public void setID(int ID) {
        this.iD = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }
    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate DateOfBirth) {
        this.dateOfBirth = DateOfBirth;
    }

    public String getPassword() {
        return password;
    }

        public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
}
