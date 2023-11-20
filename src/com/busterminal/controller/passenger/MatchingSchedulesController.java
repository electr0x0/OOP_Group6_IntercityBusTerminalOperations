/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.BusSchedule;
import com.busterminal.model.SceneSwicth;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView< BusSchedule > tableViewAvailableBuses;
    @FXML
    private TableColumn< BusSchedule , String> busCol;
    @FXML
    private TableColumn< BusSchedule , LocalDate> depTimeCol;
    @FXML
    private TableColumn< BusSchedule , LocalDate> arrTimeCol;
    @FXML
    private TableColumn< BusSchedule , String> availableSeatsCol;
    @FXML
    private TableColumn< BusSchedule , String> priceCol;

    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busCol.setCellValueFactory(new PropertyValueFactory<>("bus"));
        depTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        //availableSeatsCol.setCellValueFactory(new PropertyValueFactory<>("bus"));
    }    

    @FXML
    private void switchToSchedule(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane4,"/com/busterminal/views/passenger/Schedule.fxml");
        
        
    }

        
        
   

    @FXML
    private void switchToTicketbuyScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane4,"/com/busterminal/views/passenger/TicketDetails.fxml");
        
    }
} 
