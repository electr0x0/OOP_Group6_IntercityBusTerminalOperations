package com.busterminal.views;

import com.busterminal.model.Administrator;
import com.busterminal.model.Client;
import com.busterminal.model.PopUp;
import com.busterminal.model.User;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SignUpController implements Initializable {

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
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRB;
    @FXML
    private Button backButton;
    @FXML
    private Label fristNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label birthdateLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button signUpButton;
    
    private final boolean True = true;
    private final boolean False = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void signUpOnMouseClick(ActionEvent event) throws IOException {
        String firstname = firstNameTF.getText();
        String lastname = lastNameTF.getText();
        String address = addressTF.getText();
        String contactNumber = phoneNumberTF.getText();
        String email = emailTF.getText().trim();
        LocalDate dob = birthdayDP.getValue();
        String password = passwordTF.getText();
        String gender = (maleRB.isSelected() ? "Male" : "Famale");
        String confirmPassword = confirmPasswordTF.getText();

        // Check validation and email and pass verification
        if (firstNameTF.getText().trim().equals("")) {
            fristNameLabel.setText("Enter your First Name !");
            return;
        }
        else{
            fristNameLabel.setText("");
        }
        if (lastNameTF.getText().trim().equals("")) {
            lastNameLabel.setText("Enter your last Name !");
            return;
        }else{
            lastNameLabel.setText("");
        }
        if (emailTF.getText().trim().equals("")) {
            emailLabel.setText("Enter your Eamil !");
            return;
        } else {
            emailLabel.setText("");
            if (User.verifyEmailSuffix(email) == False) {
                emailLabel.setText("@gmail.com");
                return;
            }
            else{
            emailLabel.setText("");
        }
        }
        if (phoneNumberTF.getText().trim().equals("")) {
            phonenumberLabel.setText("Enter your Phone Number !");
            return;
        }else{
            phonenumberLabel.setText("");
            int n =11;
            if(phoneNumberTF.getText().length()!=n){
                phonenumberLabel.setText("Enter valid Phone Number !");
                return;
            }
            else{
            phonenumberLabel.setText("");
        }
        }

        if (addressTF.getText().trim().equals("")) {
            addressLabel.setText("Enter your address !");
            return;
        }else{
            addressLabel.setText("");
        }
        
        if (birthdayDP.getValue() == null) {
            birthdateLabel.setText("Enter your Birth of Date !");
            return;
        }
        else{
            birthdateLabel.setText("");
            if(birthdayDP.getValue().isAfter(LocalDate.now())){
                birthdateLabel.setText("Birthdate is in the future. This is not possible!");
                return;
            }else{
            birthdateLabel.setText("");
        }
        }
        if (passwordTF.getText().trim().equals("")) {
            passwordLabel.setText("Enter eight length password !");
            return;
        } else {
            passwordLabel.setText("");
            if (User.verifyPasswordLength(passwordTF.getText()) == False) {
                passwordLabel.setText("Password length must be eight !");
                passwordTF.clear();
                return;
            }else{
           passwordLabel.setText("");
        }
        }
        if (maleRB.isSelected() == false && femaleRB.isSelected() == false) {
            genderLabel.setText("Choose your gender");
            return;
        }
        else{
            genderLabel.setText("");
        }

        if (!password.equals(confirmPassword)) {
            PopUp.showMessage("Password didn't Match", "Confirmpasswoed and password must be same !");
            confirmPasswordTF.clear();
            return;
        }

        
        
        int clientID = User.generateClientID();
        if (User.checkClientEmailExistence(emailTF.getText())== False) {
             Client c = new Client(clientID, firstname, lastname, address, email, dob, password, contactNumber, gender);
            Administrator.clientCreateNewAccount(c);
            PopUp.showMessage("Information", "Account has been Succesfully Created\n"
                    + "Your Customer ID is: " + Integer.toString(clientID));
            signUpButton.getScene().getWindow().hide();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            PopUp.showMessage("Information", "Account Already Exists !");
            return;    
        }
        

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
