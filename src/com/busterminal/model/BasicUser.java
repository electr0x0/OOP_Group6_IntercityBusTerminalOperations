
package com.busterminal.model;


import java.io.Serializable;
import java.time.LocalDate;


public class BasicUser implements Serializable {

    public String firstname;
    private String lastname,address,language,gender;
    private String phonenumber;
    protected String email,password;
    private LocalDate dateofbirth;

    public BasicUser(String firstname, String lastname, String address, String language, String gender, String phonenumber, String email, String password, LocalDate dateofbirth) {
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

    @Override
    public String toString() {
        return "firstname:" + firstname + ", lastname:" + lastname + ", address:" + address + ", language:" + language + ", gender:" + gender + ", phonenumber:" + phonenumber + ", email:" + email + ", dateofbirth: " + dateofbirth ;
    }
    
    public void submitReport(){
        
    }

    public BasicUser(String firstname, String lastname,String gender,String email, String phonenumber,LocalDate dateofbirth,String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.dateofbirth = dateofbirth;
    }

   

    public BasicUser() {
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
