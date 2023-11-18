/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.utilityclass;

import java.time.LocalDate;
import java.time.Period;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class Validator {
    //Email Validate
    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        
        return atIndex >= 5 && atIndex != -1 && dotIndex > atIndex && dotIndex + 4 <= email.length() - 1 && email.endsWith(".com");
    }
    
     //Age Validate    
    public static boolean isOldEnough(LocalDate dateOfBirth, DatePicker dob) {
        LocalDate currentDate = dob.getValue(); 
        Period period = Period.between(dateOfBirth, currentDate);
        
        return period.getYears() >= 18;
    }

     //Check if the fields are empty or not
    public static boolean areAllFieldsEmpty(TextField firstName, TextField email, DatePicker dob,
                                           TextField lastName, TextField confirmEmail,
                                           TextField phoneNumber, TextField address,
                                           ChoiceBox<String> designation, PasswordField password,
                                           TextField salary, PasswordField confirmPassword,ChoiceBox<String> choice) {
        return firstName.getText().isEmpty() &&
                email.getText().isEmpty() &&
                dob.getValue() == null &&
                lastName.getText().isEmpty() &&
                confirmEmail.getText().isEmpty() &&
                phoneNumber.getText().isEmpty() &&
                address.getText().isEmpty() &&
                designation.getValue() == null &&
                password.getText().isEmpty() &&
                salary.getText().isEmpty() &&
                confirmPassword.getText().isEmpty()&&
                choice.getValue() == null;
    }
    
     //Phone Number Validate
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Check if the phone number is not null and has exactly 11 characters
        if (phoneNumber != null && phoneNumber.length() == 11) {
            // Check if the phone number contains only numeric characters
            return phoneNumber.matches("[0-9]+") && phoneNumber.startsWith("01");
        }
        return false;
    }
}
