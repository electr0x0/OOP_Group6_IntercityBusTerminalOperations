/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

//import com.busterminal.views.passenger.*;
import com.busterminal.model.Bus;
import com.busterminal.model.BusSchedule;
import com.busterminal.model.BusTrip;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private TableView<BusTrip> tableViewSchedule;
    @FXML
    private TableColumn<BusTrip, Bus> busCol;
    @FXML
    private TableColumn<BusTrip, String> sourceCol;
    @FXML
    private TableColumn<BusTrip, String> destinationCol;
    
    @FXML
    private TableColumn<BusTrip, LocalDateTime> arrTimeCol;
    @FXML
    private TableColumn<BusTrip, Integer> fareCol;

    
    ObservableList<BusTrip> scheduleList = FXCollections.observableArrayList();
    ArrayList<BusTrip> helperList = new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         busCol.setCellValueFactory(new PropertyValueFactory<>("bus")); 
         sourceCol.setCellValueFactory(new PropertyValueFactory<>("source")); 
         destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination")); 
         arrTimeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot")); 
        
         fareCol.setCellValueFactory(new PropertyValueFactory<>("adultFare")); 
    
         
         
         RelationshipDatabaseClass.loadFromFile();
         helperList= RelationshipDatabaseClass.getInstance().getAllTripList();
         for(BusTrip y : helperList ){
             scheduleList.add(y);
         }
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
