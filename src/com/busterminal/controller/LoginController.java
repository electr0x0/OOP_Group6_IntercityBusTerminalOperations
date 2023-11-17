package com.busterminal.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class LoginController implements Initializable{

    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public Label label;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPasswordButton;
    @FXML
    private ComboBox<String> accountTypeComboBox;  // set default type as a String
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // Set Account Type
        accountTypeComboBox.getItems().addAll("Administrator","Maintenance Staff","Driver","Manager","Passenger");
            
    
}

    
    private void loginUser(){
         
        
        //We verify the username and password
        if(!userNameTextField.getText().trim().equals("") 
                && !passwordField.getText().trim().equals("")){
            
            //When we click login button our login scene will hide and get new scene
            loginButton.getScene().getWindow().hide();
            
            //Now we are ready to go the next scene
            
            Stage adminDashBoard = new Stage();
            
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../Fxml/Admin/AdminDashBord.fxml"));
                //now create a scene
                Scene scene = new Scene(root);
                adminDashBoard.setScene(scene);
                adminDashBoard.show();
                adminDashBoard.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            
        }else label.setText("Enter username & password!");
        
        
    }

    @FXML
    private void loginButtonOnMouseClick(ActionEvent event) {
        loginUser();
    }

    @FXML
    private void onMouseClick(ActionEvent event) {
    }

    @FXML
    private void accountTypeOnAction(ActionEvent event) {
    }
}

