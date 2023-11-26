/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.MaintenanceStaff;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddMaintenanceMemberController implements Initializable {

    @FXML
    private TableView<?> maintenanceEmployeeTable;
    @FXML
    private TableColumn<?, ?> IdCol;
    @FXML
    private TableColumn<?, ?> firstNameCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<?, ?> genderCol;
    @FXML
    private TableColumn<?, ?> phoneCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton FemaleRB;
    @FXML
    private ComboBox<?> TypeCB;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private TextField salaryTF;
    @FXML
    private TextField addressTF;
    @FXML
    private DatePicker birthdayDP;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private Label fristNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label TypeLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthdateLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label salaryLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddButtonOnMouseClick(ActionEvent event) {
    }
    
}
