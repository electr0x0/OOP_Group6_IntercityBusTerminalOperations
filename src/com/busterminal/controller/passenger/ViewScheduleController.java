/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

//import com.busterminal.views.passenger.*;
import com.busterminal.model.Bus;
import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.DummyClassForTableViewSchedule;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private TableView<DummyClassForTableViewSchedule> tableViewSchedule;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, Bus> busCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, String> sourceCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, String> destinationCol;
    
    
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, Integer> fareCol;

    
    ObservableList<DummyClassForTableViewSchedule> scheduleList = FXCollections.observableArrayList();
    ArrayList<BusTrip> helperList = new ArrayList();
    
    
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, String > timeCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, LocalDate> dateCol;
    
   private ArrayList<BusTripSchedule> b2 = new ArrayList();
   private ArrayList<BusTrip> b1 = new ArrayList();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         busCol.setCellValueFactory(new PropertyValueFactory<>("bus")); 
         sourceCol.setCellValueFactory(new PropertyValueFactory<>("source")); 
         destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination")); 
         timeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot")); 
         dateCol.setCellValueFactory(new PropertyValueFactory<>("scheduleDate")); 
         fareCol.setCellValueFactory(new PropertyValueFactory<>("adultFare")); 
         
         
         
         
        RelationshipDatabaseClass.loadFromFile();
        b2= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
        b1= RelationshipDatabaseClass.getInstance().getAllTripList();
        
        
        for(BusTrip x : b1){
            for(BusTripSchedule y : b2){
                if (x.getScheduleId() == y.getScheduleId()){
                    DummyClassForTableViewSchedule d = new DummyClassForTableViewSchedule(x.getBus(),x.getSource(), x.getDestination(),x.getAdultFare(),x.getTimeSlot(),y.getScheduleDate());
                    
                    scheduleList.add(d);
                    tableViewSchedule.setItems(scheduleList);
                }
            }
            
        }
         
         
    
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
