package com.busterminal.controller;

import com.busterminal.model.BasicUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


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
    @FXML
    private ComboBox<String> languageCB;
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton femaleRB;
    private List<BasicUser> data;
    @FXML
    private Button signUpButton;
    private BasicUser person;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = new ArrayList<>();
        languageCB.getItems().addAll("English", "Bangla");
        System.out.println(data);

    }

    @FXML
    private void signUpOnMouseClick(ActionEvent event) throws IOException {
        person = new BasicUser(firstNameTF.getText(), lastNameTF.getText(), addressTF.getText(), languageCB.getValue(), (maleRB.isSelected() ? "Male" : "Famale"), phoneNumberTF.getText(), emailTF.getText().trim(), passwordTF.getText().trim(),
        birthdayDP.getValue());
        
        data.add(person);
        filewrite();
        sceneSwitch();
    }

    private void filewrite() {
        File file = null;
        FileInputStream fos = null;
        ObjectOutputStream dos = null;
        try {
            String location = "userdata.bin";
            file = new File(location);
            if (file.exists()) {
                dos = new AppendableObjectOutputStream(new FileOutputStream(location, true));
            } else {
                dos = new ObjectOutputStream(new FileOutputStream(location));
            }
            dos.writeObject(person);
            System.out.println(person.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clear();
    }

    private void sceneSwitch() {
        signUpButton.getScene().getWindow().hide();
        Stage login = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../../Fxml/login.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            // Create a object For using Label which is located login scene    
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginController loginController = new LoginController();
        loginController.updateLabel("Sign up Successfully..!", "-fx-text-fill: green;");

    }

    private void clear() {
        firstNameTF.clear();
        lastNameTF.clear();
        passwordTF.clear();
        confirmPasswordTF.clear();
        languageCB.setValue("");
        emailTF.clear();
        addressTF.clear();
        phoneNumberTF.clear();
    }
}
