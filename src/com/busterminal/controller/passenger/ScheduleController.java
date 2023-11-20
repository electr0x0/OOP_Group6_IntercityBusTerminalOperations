/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ScheduleController implements Initializable {

    @FXML
    private AnchorPane anchorpane3;
    @FXML
    private ComboBox fromCombo;
    @FXML
    private ComboBox<String> toCombo;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Dhaka","Rajshahi","Chittagong","Khulna","Sylhet");
         
         fromCombo.setItems(list);
         toCombo.setItems(list);
        
        // TODO
    }    

    @FXML
    private void switchToMatchingSchedule(ActionEvent event) throws IOException {
      
    
    //String from = fromCombo.getSelectionModel().getSelectedItem().toString();
    //String to = toCombo.getSelectionModel().getSelectedItem();

        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
        
    
    
        
    }  
            
        
    

    

    @FXML
    private void switchtoScene2(ActionEvent event) throws IOException {
         new SceneSwicth(anchorpane3,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        
    }
    
}
