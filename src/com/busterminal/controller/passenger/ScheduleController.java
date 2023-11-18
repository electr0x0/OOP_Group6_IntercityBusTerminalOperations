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
      
    
    String from = fromCombo.getSelectionModel().getSelectedItem().toString();
    String to = toCombo.getSelectionModel().getSelectedItem();

    if ("Dhaka".equals(from) && "Rajshahi".equals(to)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = loader.load();
        MatchingSchedulesController matchingScheduleController = loader.getController();
        matchingScheduleController.displayBus1Label("KTC HANIF\n" +
"115 NON_AC\n" +
"Starting Point: Kallyanpur KP1\n" +
"End Point: Rajshahi");
        matchingScheduleController.displayBus2Label("DESH TRAVELS\n" +
"011-DHK-CHAP Non AC\n" +
"Starting Point: Kallaynpur\n" +
"End Point: Chapai");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
           }

    else if ("Dhaka".equals(from) && "Chittagong".equals(to)){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = loader.load();
        MatchingSchedulesController matchingScheduleController = loader.getController();
        matchingScheduleController.displayBus1Label("GREEN LINE\n" +
"101-DD-Kalabagan-Upper AC\n" +
"Starting Point: Kalabagan1\n" +
"End Point:");
        matchingScheduleController.displayBus2Label(" SHYAMOLI NR TRAVELS\n" +
"102-CTG Non AC\n" +
"Starting Point: Gabtoli 06 No.\n" +
"End Point: CTG- BRTC-02");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
           }
       

    else if ("Dhaka".equals(from) && "Khulna".equals(to)){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = loader.load();
        MatchingSchedulesController matchingScheduleController = loader.getController();
        matchingScheduleController.displayBus1Label("WELCOME EXPRESS\n" +
"70 MIR-KHU (PADMA) Non AC\n" +
"\n" +
"Starting Point: Kocukhet\n" +
"\n" +
"End Point: Fultola");
        matchingScheduleController.displayBus2Label("HANIF ENTERPRISE\n" +
"12-Magura-Jessore NON_AC\n" +
"Starting Point: Abdullahpur\n" +
"End Point:");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
           }      
    
    
    else if ("Dhaka".equals(from) && "Sylhet".equals(to)){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = loader.load();
        MatchingSchedulesController matchingScheduleController = loader.getController();
        matchingScheduleController.displayBus1Label("SHYAMOLI NR TRAVELS\n" +
"906-SYLHET Non AC\n" +
"Starting Point: KP BRTC NR\n" +
"End Point: SYLHET KODOMTOLI-02");
        matchingScheduleController.displayBus2Label("GREEN LINE\n" +
"502-B-R/B AC\n" +
"Starting Point: Razarbagh1\n" +
"End Point:");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
           }
    
    }  
        
        
            
        
    

    

    @FXML
    private void switchtoScene2(ActionEvent event) throws IOException {
         new SceneSwicth(anchorpane3,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        
    }
    
}
