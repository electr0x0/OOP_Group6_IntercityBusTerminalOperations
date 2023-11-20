/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyEmployeeController implements Initializable {

    @FXML
    private TableView<?> employeeTable;
    @FXML
    private TableColumn<?, ?> employeTableId;
    @FXML
    private TableColumn<?, ?> tableFirstName;
    @FXML
    private TableColumn<?, ?> tableLastName;
    @FXML
    private TableColumn<?, ?> tableEmail;
    @FXML
    private TableColumn<?, ?> tablePhone;
    @FXML
    private TableColumn<?, ?> tableSalary;
    @FXML
    private TableColumn<?, ?> tableDesignation;
    @FXML
    private TableColumn<?, ?> tableAbsent;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void createEmployeeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/CreateEmployee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
