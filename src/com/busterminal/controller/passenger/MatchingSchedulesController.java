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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class MatchingSchedulesController implements Initializable {

    @FXML
    private AnchorPane anchorpane4;
    @FXML
    private Label bus1Label;
    @FXML
    private Label bus2Label;

    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToSchedule(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane4,"views/passenger/Schedule.fxml");
        
    }
    void displayBus1Label(String text){
        bus1Label.setText(text);
        
    }
    void displayBus2Label(String text){
        bus2Label.setText(text);}

    @FXML
    private void switchToTicketbuyScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane4,"views/passenger/TicketDetails.fxml");
        
    }
} 
