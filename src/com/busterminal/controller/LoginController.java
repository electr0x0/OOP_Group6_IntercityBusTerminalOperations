/*
package com.busterminal.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainPkg.Interface.PopUp;
import mainPkg.Interface.Account;
public class LoginController implements Initializable {

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
        accountTypeComboBox.getItems().addAll("Administrator", "Maintenance Staff", "Ticket Vendor", "Driver",
                "Terminal Manager", "Passenger", "Human Resource");

    }
    @FXML
    private void loginButtonOnMouseClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = null;
        String email = userNameTextField.getText();
        String password = passwordField.getText();
        System.out.println(password);
        if (Account.verifyEmailSuffix(email) && Account.verifyPasswordLength(password)){
            
       
        switch (accountTypeComboBox.getValue()) {
            case "Administrator":
                if (Account.employeepasswordMatch(accountTypeComboBox.getValue(),email, password)==true) {
                    loader = new FXMLLoader(getClass().getResource("AdminDashbord.fxml"));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Maintenance Staff":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Ticket Vendor":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Driver":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Terminal Manager":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Beef":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Human Resource":
                if (email.equals("mdmarufkhan195@gmail.com") & password.equals("maruf195")) {
                    loader = new FXMLLoader(getClass().getResource(""));
                } else {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                }
                break;
            case "Passenger":
                //email
                //password
                if (!Account.checkClientEmailExistence(email)) {
                    PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    return;
                }
                if (!Account.clientpasswordMatch(email, password)) {
                   PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    return;
                }else{
                    loader = new FXMLLoader(getClass().getResource(""));
                }
                break;
        }
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }else{
            userNameTextField.clear();
            passwordField.clear();
            PopUp.showMessage("Alert", "Invalid Email Or Password"+"\n"+"suffix of email should be @gmail.com"+"\n"+"Password length should be 8");
        }
    }

    @FXML
    private void signUpButtononMouseClick(ActionEvent event) throws IOException {
        //signupbutton.getScene().getWindow().hide();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        root.resize(0, 0);
    }

    @FXML
    private void forgetPassWordonMouseClick(ActionEvent event) {
    }
    


}
*/