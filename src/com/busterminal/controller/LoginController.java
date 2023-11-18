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
    private Label label;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPasswordButton;
    @FXML
    private ComboBox<String> accountTypeComboBox;  // set default type as a String
    @FXML
    private Button signupbutton;
    
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
    public void updateLabel(String text, String style) {
        if (label == null) {
            label.setText(text);
            label.setStyle(style);
        } else {
            System.out.println("Label is null. Make sure the label is properly defined in your FXML file.");
        }
    }

    @FXML
    private void loginButtonOnMouseClick(ActionEvent event) {
        loginUser();
    }


    @FXML
    private void accountTypeOnAction(ActionEvent event) {
    }

    @FXML
    private void signUpButtononMouseClick(ActionEvent event) {
            //When we click login button our login scene will hide and get new scene
            signupbutton.getScene().getWindow().hide();
            //Now we are ready to go the next scene         
            Stage signup = new Stage();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../Fxml/user/SignUp.fxml"));
                if (root!=null){
                    // Handle the case where the fxml file not found
                    System.out.println("Fxml file not found!");
                    return;
                }
                //now create a scene
                Scene scene = new Scene(root);
                signup.setScene(scene);
                signup.show();
                signup.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
}
    @FXML
    private void forgetPassWordonMouseClick(ActionEvent event) {
    }
}

