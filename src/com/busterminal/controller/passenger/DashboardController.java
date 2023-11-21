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
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane anchorpane2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToScene1(ActionEvent event) throws IOException {
         new SceneSwicth(anchorpane2,"views/passenger/Login.fxml");
    }

    @FXML
    private void switchToScheduleInfoScene(ActionEvent event) throws IOException {
        //new SceneSwicth(anchorpane2,"views/passenger/Schedule.fxml");
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/viewSchedule.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }

    @FXML
    private void switchTopurchaseHistoryScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane2,"views/passenger/PurchaseHistory.fxml");
    }

    @FXML
    private void switchToReserveScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane2,"views/passenger/Reserve.fxml");
    }

    @FXML
    private void switchToOffersScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane2,"views/passenger/Offers.fxml");
        
    }

    @FXML
    private void switchToSupportScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane2,"views/passenger/Support.fxml");
        
        
    }

    @FXML
    private void switchToContactScene(ActionEvent event) throws IOException {
        //new SceneSwicth(anchorpane2,"views/passenger/Contact.fxml");
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/Contact.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToPurchaseTicketScene(ActionEvent event) throws IOException {
        
        new SceneSwicth(anchorpane2,"views/passenger/Schedule.fxml");
    }
    
}
