/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }
      
    public static boolean areFirstFieldsEmpty(TextField firstName, TextField email, DatePicker dob,
                                          TextField lastName, TextField confirmEmail,
                                          TextField phoneNumber, TextField address, ChoiceBox<String> choice) {
    return firstName.getText().isEmpty() &&
            email.getText().isEmpty() &&
            dob.getValue() == null &&
            lastName.getText().isEmpty() &&
            confirmEmail.getText().isEmpty() &&
            phoneNumber.getText().isEmpty() &&
            address.getText().isEmpty() &&
            choice.getValue() == null;
}
    
    
    
 
    
}
