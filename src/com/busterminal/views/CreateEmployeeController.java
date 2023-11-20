/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views;

import com.busterminal.model.Employee;
import com.busterminal.utilityclass.Validator;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateEmployeeController implements Initializable {

    @FXML
    private HBox page1;
    @FXML
    private Button pagebtn1;
    @FXML
    private HBox page2;
    @FXML
    private Button pagebtn2;
    @FXML
    private TextField firstName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField lastName;
    @FXML
    private TextField confirmEmail;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<String> designation;
    @FXML
    private PasswordField password;
    @FXML
    private TextField salary;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private AnchorPane alertMissing;
    @FXML
    private Label missingLabel;

    String genderList[] = {"Select Gender","Male","Female","Other"};
    String designationList [] = {"Select Designation","Manager","Human Resource"};
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.getItems().addAll(genderList);
        gender.setValue("Select Gender");
        
        designation.getItems().addAll(designationList);
        designation.setValue("Select Designation");
        
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            updateEmailMatchLabel();
        });

        confirmEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            updateEmailMatchLabel();
        });
        
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePasswordMatchLabel();
        });

        confirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            updatePasswordMatchLabel();
        });
    }    

 @FXML
private void switchPage(ActionEvent event) {
    Boolean isValid = areFirstFieldsEmpty(firstName,email,dob,lastName,confirmEmail,phoneNumber,address,gender);
    
    if (page1.isVisible() && !isValid) {
        page2.setVisible(true);
        page1.setVisible(false);
        pagebtn2.setTextFill(Color.SKYBLUE);
        pagebtn1.setTextFill(Color.BLACK);
    } else {
        page2.setVisible(false);
        page1.setVisible(true);
        pagebtn2.setTextFill(Color.BLACK);
        pagebtn1.setTextFill(Color.SKYBLUE);
    }
}

    @FXML
    private void createNewEmployee(ActionEvent event) {
        Boolean isEmail = Validator.validateEmail(email.getText());
        Boolean age = Validator.isOldEnough( dob.getValue());
        Boolean pass = Validator.validatePassword(password.getText());
        Boolean num = Validator.isValidPhoneNumber(phoneNumber.getText());
        Boolean sal = Validator.isInteger(salary.getText());
       
        String validOutput = "";
        if(!isEmail){validOutput+="Email is not valid! \n";}
        if(!age){validOutput+="\n Age is not valid! \n";}
        if(!pass){validOutput+="\n Password is too week! \n";}
        if(!num){validOutput+="\n Invalid Phone Number! \n";}
        if(!sal){validOutput+="\n Salary given not valid!";}
        if(isEmail && age && pass && num && sal){
            Employee newEmployee = new Employee(Integer.parseInt(salary.getText()),
                    designation.getValue(),firstName.getText(),lastName.getText(),
                    gender.getValue(),email.getText(),phoneNumber.getText(),dob.getValue(),
                    address.getText()
            );
            System.out.println(newEmployee.toString());
        } else{
            missingLabel.setText(validOutput);
            alertMissing.setVisible(true);
            page2.setVisible(false);
        }
    }
      
    public static boolean areFirstFieldsEmpty(TextField firstName, TextField email, DatePicker dob,
                                          TextField lastName, TextField confirmEmail,
                                          TextField phoneNumber, TextField address, ChoiceBox<String> choice) {
    return firstName.getText().isEmpty() ||
            email.getText().isEmpty() ||
            dob.getValue() == null ||
            lastName.getText().isEmpty() ||
            confirmEmail.getText().isEmpty() ||
            phoneNumber.getText().isEmpty() ||
            address.getText().isEmpty() ||
            choice.getValue() == "Select Gender"||
            (email.getText() == null ? confirmEmail.getText() != null : !email.getText().equals(confirmEmail.getText()));
}

    @FXML
    private void closeMissing(ActionEvent event) {
        alertMissing.setVisible(false);
        page2.setVisible(true);
    }
    
    private void updateEmailMatchLabel() {
        String mail = email.getText();
        String confirmmail = confirmEmail.getText();

        if (mail.equals(confirmmail)) {
            emailLabel.setText("");
        } else {
            emailLabel.setText("Emails do not match!");
            
        }
    }
    
    private void updatePasswordMatchLabel() {
        String pass = password.getText();
        String confirmPass = confirmPassword.getText();

        if (pass.equals(confirmPass)) {
            passwordLabel.setText("");
        } else {
            passwordLabel.setText("Passwords do not match!");
            
        }
    }
    
    
   
}
 
    

