/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.BusTrip;
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
    private TableView<BusTrip > tableViewAvailableBuses;
    @FXML
    private TableColumn< BusTrip , String> busCol;
    @FXML
    private TableColumn< BusTrip  , LocalDate> depTimeCol;
    
    @FXML
    private TableColumn< BusTrip  , String> sourceCol;
    @FXML
    private TableColumn< BusTrip  , String> destinationCol;
    @FXML
    private TableColumn<BusTrip  , String> fareCol;
    @FXML
    private TableColumn<BusTrip, LocalDate> dateCol;

    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busCol.setCellValueFactory(new PropertyValueFactory<>("bus"));
        depTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        fareCol.setCellValueFactory(new PropertyValueFactory<>("adultFare"));
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
