
package com.busterminal.controller;

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
import javafx.stage.Stage;


public class DashboardController implements Initializable {


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToRouteSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("Route.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToPassangerInfoSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("PassengerInfo.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }

    @FXML
    private void switchToBusInfoSceneOnClick(ActionEvent event) throws IOException {
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("BusInfo.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToReportSceneOnClick(ActionEvent event) throws IOException {
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("Report.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

   
   

    

    @FXML
    private void switchToChatSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("ChatBody.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToDashboardScene(ActionEvent event) {
    }

    @FXML
    private void switchToTripHistorySceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("TripHistory.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToInboxSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("Inbox.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
    }
    

    
}
