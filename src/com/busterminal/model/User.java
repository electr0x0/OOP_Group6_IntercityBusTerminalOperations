
package com.busterminal.model;


import java.io.Serializable;
import java.time.LocalDate;


public abstract class User implements Serializable {
    private String firstname,lastname,address,language,gender;
    private int id;
    private String phonenumber;
    protected String email,password;
    private LocalDate dateofbirth;

    public User(String firstname, String lastname,String gender,String email, String phonenumber,LocalDate dateofbirth,String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.dateofbirth = dateofbirth;
    }

    public User(String firstname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.language = language;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.dateofbirth = dateofbirth;
    }

    public User() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    
    
    
    
    
}
