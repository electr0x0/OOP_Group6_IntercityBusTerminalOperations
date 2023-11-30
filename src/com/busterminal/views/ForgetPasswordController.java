/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views;

import com.busterminal.model.Administrator;
import com.busterminal.model.PopUp;
import com.busterminal.model.User;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maruf
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private ComboBox<String> userTypeCB;
    @FXML
    private TextField userEmailTF;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField reEnterPasswordTF;
    @FXML
    private Button confirmButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeCB.getItems().addAll("Administrator", "Maintenance Staff", "Ticket Vendor", "Driver",
                "Terminal Manager", "Passenger", "Human Resource", "Accountant");

    }

    @FXML
    private void confirmButtonOnMouseClick(ActionEvent event) {
        String email = userEmailTF.getText();
        String password = newPassword.getText();
        String reEnterPassword = reEnterPasswordTF.getText();

        if (userTypeCB.getValue().equals(null)) {
            PopUp.showMessage("Alert", "Selecet your designation..!");
            return;
        }
        if (email.equals("")) {
            PopUp.showMessage("Alert", "Enter your email");
            return;
        }
        if (password.equals("")) {
            PopUp.showMessage("Alert", "Enter your new Password");
            return;
        }

        if (!password.equals(reEnterPassword)) {
            PopUp.showMessage("Warning", "Password and reenter password should be same..!");
            return;
        }
        if (password.length() != 8) {
            PopUp.showMessage("Alert", "Password length shoud be Eight");
            return;
        }
        
        if (userTypeCB.getValue().equals("Passenger")) {
            if (User.checkClientEmailExistence(email)) {
                Administrator.updateClientPassword(email, password);

            } else {
                PopUp.showMessage("Warning", "Invalid Email..!");

            }

        } else {
            if (User.checkEmployeeEmailExistence(email)) {
                Administrator.updatEmployeePassword(email, password);

            } else {
                PopUp.showMessage("Warning", "Invalid Email..!");

            }
        }
        PopUp.showConfirmationMessage();
        Stage forgotPassword = new Stage();
        try {
            confirmButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            forgotPassword.setScene(scene);
            forgotPassword.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
