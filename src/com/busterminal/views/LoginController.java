package com.busterminal.views;

import com.busterminal.model.Employee;
import com.busterminal.model.PopUp;
import com.busterminal.model.User;
import com.busterminal.storage.db.RelationshipDatabaseClass;
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
    private ComboBox<String> accountTypeComboBox;
    @FXML
    private Button signupbutton;
    private final boolean True = true;
    private final boolean False = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set Account Type
        accountTypeComboBox.getItems().addAll("Administrator", "Maintenance Staff",  "Driver",
                "Terminal Manager", "Passenger", "Human Resource", "Accountant");

        // set Default value for designation Combobox
        accountTypeComboBox.setValue("Passenger");
    }

    @FXML
    private void loginButtonOnMouseClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = null;
        String email = userNameTextField.getText().trim();
        String password = passwordField.getText().trim();
        System.out.println(password);
        System.out.println(accountTypeComboBox.getValue());
        if (email.equals("")) {
            PopUp.showMessage("Alert", "Enter your Email.");
            userNameTextField.clear();
            return;
        }
        if (password.equals("")) {
            PopUp.showMessage("Alert", "Enter your Password");
            passwordField.clear();
            return;
        }
        if (User.verifyEmailSuffix(email) && User.verifyPasswordLength(password)) {

            switch (accountTypeComboBox.getValue()) {
                case "Administrator":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Addministrator/AdminDashbord.fxml"));
                        Employee.setCurrentUser(email); // set Current mail for HR
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Maintenance Staff":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml"));
                        Employee.setCurrentUser(email); // set Current mail for HR
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Accountant":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantDashboard.fxml"));
                        Employee.setCurrentUser(email);
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Driver":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
                        Employee.setCurrentUser(email);
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Terminal Manager":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerDashboard.fxml"));
                        Employee.setCurrentUser(email); 
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource(""));
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Human Resource":
                    if (User.employeepasswordMatch(accountTypeComboBox.getValue(), email, password) == True) {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
                        Employee.setCurrentUser(email); // set Current mail for HR
                        RelationshipDatabaseClass.getInstance().setCurrentUserEmail(email);
                    } else {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                    }
                    break;
                case "Passenger":
                    //email
                    //password
                    if (!User.clientpasswordMatch(email, passwordField.getText())) {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                        return;
                    }
                    if (User.clientpasswordMatch(email, password) == False) {
                        PopUp.showMessage("Wrong credentials", "Invalid username or password");
                        return;
                    } else {
                        loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/Dashboard_Passenger.fxml"));
                    }
                    break;
            }
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(False);
            stage.show();

        } else {
            userNameTextField.clear();
            passwordField.clear();
            PopUp.showMessage("Alert", "Invalid Email Or Password" + "\n" + "suffix of email should be @gmail.com" + "\n" + "Password length should be 8");
        }

    }

    @FXML
    private void signUpButtononMouseClick(ActionEvent event){
        Stage signup = new Stage();
        try {
            signupbutton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
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
        Stage forgotPassword = new Stage();
        try {
            forgotPasswordButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("ForgetPassword.fxml"));
            Scene scene = new Scene(root);
            forgotPassword.setScene(scene);
            forgotPassword.show();
            forgotPassword.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
