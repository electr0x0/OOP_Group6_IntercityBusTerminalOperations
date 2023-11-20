/*

package com.busterminal.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainPkg.Interface.Account;
import mainPkg.Interface.Administrator;
import mainPkg.Interface.PopUp;
import mainPkg.model.Client;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField confirmPasswordTF;
    @FXML
    private DatePicker birthdayDP;
    @FXML
    private TextField addressTF;
    private ComboBox<String> languageCB;
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRB;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void signUpOnMouseClick(ActionEvent event) throws IOException {
        String firstname = firstNameTF.getText();
        String lastname = lastNameTF.getText();
        String address = addressTF.getText();
        String contactNumber = phoneNumberTF.getText();
        String email = emailTF.getText();
        LocalDate dob = birthdayDP.getValue();
        String password = passwordTF.getText();
        String gender = (maleRB.isSelected() ? "Male" : "Famale");
        String confirmPassword = confirmPasswordTF.getText();
        int clientID = Account.generateClientID();
        if (password.equals(confirmPassword)) {
            if (Account.checkClientEmailExistence(email)) {
                PopUp.showMessage("Information", "Account Already Exists !");
            } else {
                Client c = new Client(clientID, firstname, lastname, address,email, dob, password,contactNumber,   gender);
                Administrator.clientCreateNewAccount(c);
                PopUp.showMessage("Information", "Account has been Succesfully Created\n"
                        + "Your Customer ID is: " + Integer.toString(clientID));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            PopUp.showMessage("Password didn't Match", "Confirmpasswoed and password must be same:");
        }

    }

    private void clear() {
        firstNameTF.clear();
        lastNameTF.clear();
        passwordTF.clear();
        confirmPasswordTF.clear();
        emailTF.clear();
        addressTF.clear();
        phoneNumberTF.clear();
    }

    @FXML
    private void goLogInOnMouseClick(ActionEvent event) throws IOException {
        backButton.getScene().getWindow().hide();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
*/