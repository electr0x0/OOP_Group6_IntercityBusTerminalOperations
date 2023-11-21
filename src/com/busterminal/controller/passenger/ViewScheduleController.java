/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

//import com.busterminal.views.passenger.*;
import com.busterminal.model.Bus;
import com.busterminal.model.BusSchedule;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ViewScheduleController implements Initializable {

    @FXML
    private TableView<BusSchedule> tableViewSchedule;
    @FXML
    private TableColumn<BusSchedule, Bus> busCol;
    @FXML
    private TableColumn<BusSchedule, String> sourceCol;
    @FXML
    private TableColumn<BusSchedule, String> destinationCol;
    @FXML
    private TableColumn<BusSchedule, LocalDateTime> departureCol;
    @FXML
    private TableColumn<BusSchedule, LocalDateTime> arrTimeCol;
    @FXML
    private TableColumn<BusSchedule, Integer> fareCol;

    
    ObservableList<BusSchedule> scheduleList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         busCol.setCellValueFactory(new PropertyValueFactory<>("bus")); 
         sourceCol.setCellValueFactory(new PropertyValueFactory<>("source")); 
         destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination")); 
         departureCol.setCellValueFactory(new PropertyValueFactory<>("departureTime")); 
         arrTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime")); 
         fareCol.setCellValueFactory(new PropertyValueFactory<>("adultFare")); 
    
 
         tableViewSchedule.setItems(scheduleList);
                 
                 
                 }    

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/Dashboard_Passenger.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }
    
}
