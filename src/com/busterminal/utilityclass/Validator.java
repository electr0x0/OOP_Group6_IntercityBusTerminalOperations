/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.utilityclass;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
   public static boolean validateEmail(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }

    // Basic checks for '@' and '.'
    int atIndex = email.indexOf('@');
    int dotIndex = email.lastIndexOf('.');

    // Ensure '@' is present and not the first or last character
    if (atIndex < 1 || atIndex >= email.length() - 1) {
        return false;
    }

    // Ensure '.' follows '@' and has at least one character after it
    if (dotIndex <= atIndex + 1 || dotIndex >= email.length() - 1) {
        return false;
    }

    // Ensure the domain is not too short
    if (email.substring(dotIndex).length() < 3) {
        return false;
    }

    return true;
}




    
     //Age Validate    
        public static boolean isOldEnough(LocalDate dateOfBirth) {
            LocalDate currentDate = LocalDate.now(); // Get the current date from the system clock
            Period period = Period.between(dateOfBirth, currentDate);

            return period.getYears() >= 18;
        }


     //Check if the fields are empty or not
    public static boolean areAllFieldsEmpty(TextField firstName, TextField email, DatePicker dob,
                                           TextField lastName, TextField confirmEmail,
                                           TextField phoneNumber, TextField address,
                                           ChoiceBox<String> designation, PasswordField password,
                                           TextField salary, PasswordField confirmPassword,ChoiceBox<String> choice) {
        return firstName.getText().isEmpty() ||
                email.getText().isEmpty() ||
                dob.getValue() == null ||
                lastName.getText().isEmpty() ||
                confirmEmail.getText().isEmpty() ||
                phoneNumber.getText().isEmpty() ||
                address.getText().isEmpty() ||
                "Select Designation".equals(designation.getValue()) ||
                password.getText().isEmpty() ||
                salary.getText().isEmpty() ||
                isInteger(salary.getText())||
                confirmPassword.getText().isEmpty()||
                "Select Gender".equals(choice.getValue())||
                confirmEmail.getText() != email.getText() ||
                password.getText() != confirmPassword.getText();
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
    

    public static boolean validatePassword(String password) {
        // Minimum 8 characters check
        if (password.length() < 8) {
            return false;
        }

        // At least one digit check
        Pattern digitPattern = Pattern.compile(".*\\d.*");
        Matcher digitMatcher = digitPattern.matcher(password);
        if (!digitMatcher.find()) {
            return false;
        }

        // At least one uppercase letter check
        Pattern uppercasePattern = Pattern.compile(".*[A-Z].*");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);
        if (!uppercaseMatcher.find()) {
            return false;
        }

        // Password meets all criteria
        return true;
    }

     
    public static boolean isInteger(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

     
     
}
