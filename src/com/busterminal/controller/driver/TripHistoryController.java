
package com.busterminal.controller.driver;

import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.DummyTripHistory;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class TripHistoryController implements Initializable {

    @FXML
    private TableView<DummyTripHistory> tableViewTripHistory;
    @FXML
    private TableColumn<DummyTripHistory, LocalDate> dateCol;
    @FXML
    private TableColumn<DummyTripHistory, String> routeCol;
    @FXML
    private TableColumn<DummyTripHistory, String> statusCol;
    @FXML
    private TableColumn<DummyTripHistory, String> assignedDriverCol;
    @FXML
    private TableColumn<DummyTripHistory, String> assignedVehicleCol;
    
    private ObservableList<DummyTripHistory> d1= FXCollections.observableArrayList();
    ArrayList<BusTrip> bustripList = new ArrayList();
    ArrayList<BusTripSchedule> bustripScheduleList = new ArrayList();
    

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       dateCol.setCellValueFactory(new PropertyValueFactory<>("scheduleDate")); 
             
       routeCol.setCellValueFactory(new PropertyValueFactory<>("route")); 
       statusCol.setCellValueFactory(new PropertyValueFactory<>("tripStatus")); 
       assignedDriverCol.setCellValueFactory(new PropertyValueFactory<>("assignedDriver")); 
       assignedVehicleCol.setCellValueFactory(new PropertyValueFactory<>("assignedVehicle")); 
       
       RelationshipDatabaseClass.loadFromFile();
       bustripScheduleList = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
       bustripList = RelationshipDatabaseClass.getInstance().getAllTripList();
       
        for(BusTripSchedule x: bustripScheduleList ){
            
                DummyTripHistory dummy = new DummyTripHistory(x.getScheduleId(),x.getScheduleDate(),x.getSourceDestination(),"Pending", x.getAssignedDriver(),x.getAssignedVehicle());
                
                d1.add(dummy);
                tableViewTripHistory.setItems(d1);
                
            }
        
        
    }    

    
    
    @FXML
    private void switchToDashboardOnClick(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void viewDetailsOnClick(ActionEvent event) throws IOException {
        
       RelationshipDatabaseClass.loadFromFile();
       bustripScheduleList = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
       bustripList = RelationshipDatabaseClass.getInstance().getAllTripList();
        
       
       if( tableViewTripHistory.getSelectionModel().getSelectedItem() != null){
       DummyTripHistory dm = tableViewTripHistory.getSelectionModel().getSelectedItem();
       ArrayList<DummyTripHistory> d2= new ArrayList();
       d2.add(dm);
     
       
       
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/TripHistoryDetail.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        TripHistoryDetailController p = someLoader.getController();
         for(BusTrip x: bustripList){
             for(DummyTripHistory y: d2){
                 if(x.getScheduleId() == y.getScheduleId()){
                     String time = x.getTimeSlot();
                     String bus = x.getBus().getManufacturer();
                     String fleet = x.getFleetType();
                     String distance =Double.toString(x.getDistance());
                     String fare = Integer.toString(x.getAdultFare());
                     p.setValues(time , bus, fleet , distance, fare);
                 }
                 
                 
                 
          
          
           
       }}
       
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
       }
       else{
           Alert a= new Alert(Alert.AlertType.ERROR);
           a.setTitle("No Trip Selected");
           a.setHeaderText("Please Select a Trip to view details");
           a.showAndWait();
       }
        
    }

    @FXML
    private void confirmTripCompletedOnClick(ActionEvent event) {
        
        
        
    }

    @FXML
    private void deleteOnClick(ActionEvent event) {
        
        ObservableList<DummyTripHistory> allItems =  tableViewTripHistory.getItems();
        
        DummyTripHistory selectedItem =  tableViewTripHistory.getSelectionModel().getSelectedItem();
        
        
        allItems.remove(selectedItem);
       
        
    }
    
}
