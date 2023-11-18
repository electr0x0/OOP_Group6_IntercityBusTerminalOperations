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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReserveController implements Initializable {

    @FXML
    private AnchorPane anchorpaneReserve;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void swichSceneTo(ActionEvent event) {
    }

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpaneReserve,"views/passenger/Dashboard_Passenger.fxml");
    }
    
}
