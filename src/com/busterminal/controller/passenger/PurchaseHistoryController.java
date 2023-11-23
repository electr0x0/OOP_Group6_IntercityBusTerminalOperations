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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class PurchaseHistoryController implements Initializable {

    @FXML
    private AnchorPane purchaseHistoryAnchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //private void switchToTicketDetailsScene(ActionEvent event) throws IOException {
        //new SceneSwicth(purchaseHistoryAnchorpane,"ShowTicketDetailsScene.fxml");
    //}

    @FXML
    private void switchToCancelTicketScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/CancelTicket.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        new SceneSwicth(purchaseHistoryAnchorpane,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
    }

    @FXML
    private void switchToShowTicketDetailsScene(ActionEvent event) throws IOException {
        new SceneSwicth(purchaseHistoryAnchorpane,"/com/busterminal/views/passenger/ShowTicketDetails.fxml");
        
        
    }
    
}
