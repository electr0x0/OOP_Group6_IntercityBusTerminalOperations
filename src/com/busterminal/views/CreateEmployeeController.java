/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views;

import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateEmployeeController implements Initializable {

    @FXML
    private HBox page1;
    @FXML
    private TextField firstName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField lastName;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField confirmEmail;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
    @FXML
    private ChoiceBox<?> gender;
    @FXML
    private Button pagebtn1;
    @FXML
    private Button pagebtn2;
    @FXML
    private HBox page2;
    @FXML
    private ChoiceBox<?> designation;
    @FXML
    private PasswordField password;
    @FXML
    private TextField salary;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private AnchorPane alertMissing;
    @FXML
    private Label missingLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchPage(ActionEvent event) {
    }

    @FXML
    private void createNewEmployee(ActionEvent event) {
    }

    @FXML
    private void closeMissing(ActionEvent event) {
    }

    
}
