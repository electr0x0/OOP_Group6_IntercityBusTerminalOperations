/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.DummyClassForTableViewSchedule;
import com.busterminal.model.SceneSwicth;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
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
    @FXML
    private DatePicker datePciker;
    
    private ObservableList<DummyClassForTableViewSchedule> tableviewList = FXCollections.observableArrayList();
    
    ArrayList<BusTripSchedule> b2 = new ArrayList();
    ArrayList<BusTrip> b1 = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Dhaka","Rajshahi","Chittagong","Khulna","Sylhet");
         
         fromCombo.setItems(list);
         toCombo.setItems(list);
        
        
    }    

    @FXML
    private void switchToMatchingSchedule(ActionEvent event) throws IOException {
      
    
        String from = fromCombo.getSelectionModel().getSelectedItem().toString();
        String to = toCombo.getSelectionModel().getSelectedItem();
        LocalDate date = datePciker.getValue();
        
        
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = (Parent) someLoader.load();
        Scene someScene = new Scene (root);
        
         MatchingSchedulesController p = someLoader.getController();
         
         //p.setTableviewList(tableviewList);
         
        RelationshipDatabaseClass.loadFromFile();
        b2= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
        b1= RelationshipDatabaseClass.getInstance().getAllTripList();
        
        
        for(BusTrip x : b1){
            for(BusTripSchedule y : b2){
                if (x.getScheduleId() == y.getScheduleId()){
                    if( x.getSource().equals(from) && x.getDestination().equals(to ) && y.getScheduleDate().equals(date) ){
                        //System.out.println(y.getScheduleDate());
                        DummyClassForTableViewSchedule d = new DummyClassForTableViewSchedule(x.getBus(),x.getSource(), x.getDestination(),x.getAdultFare(),x.getTimeSlot(),y.getScheduleDate());
                        tableviewList.add(d);
                        p.setTableviewList(tableviewList);
                    }
                    
                }
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
        
    
    
        
    }  } }
    
   
            
        
    

    

    @FXML
    private void switchtoScene2(ActionEvent event) throws IOException {
         new SceneSwicth(anchorpane3,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
         
        
         
        
    }
    
}
