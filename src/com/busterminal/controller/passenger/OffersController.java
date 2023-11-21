/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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



public class OffersController implements Initializable {

    
    
    @FXML
    private AnchorPane offerAnchorpane;
    private ArrayList<String> s1 = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        new SceneSwicth(offerAnchorpane,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        
        
    }

    @FXML
    private void createTestOnClick(ActionEvent event) {
    }
    
}
