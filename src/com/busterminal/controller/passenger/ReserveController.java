/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReserveController implements Initializable {

    @FXML
    private AnchorPane anchorpaneReserve;
    @FXML
    private ToggleGroup acType;
    @FXML
    private ComboBox<String> citySelectCombo;
    @FXML
    private ComboBox<Integer> daySelectCombo;
    @FXML
    private ComboBox<String> busTypeCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        citySelectCombo.getItems().addAll("Dhaka","Rajshahi","Sylhet","Chittagong","Khulna");
        daySelectCombo.getItems().addAll(1,2,3,4,5);
        busTypeCombo.getItems().addAll("Minibus","Coach","Double Decker Bus");
    }    


    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpaneReserve,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
    }

    @FXML
    private void swichSceneToReservedBusListsScene(ActionEvent event) {
        
    }
    
}
