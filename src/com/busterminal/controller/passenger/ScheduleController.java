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
import javafx.scene.control.Alert;
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
    
    ArrayList<BusTrip> b2 = new ArrayList();
    ArrayList<BusTripSchedule> b1 = new ArrayList();

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
      
        
        if ( fromCombo.getSelectionModel().isEmpty() == false && toCombo.getSelectionModel().isEmpty() == false && datePciker.getValue() != null ){
            
        if (fromCombo.getSelectionModel().getSelectedItem() != toCombo.getSelectionModel().getSelectedItem() ){
            
        
        String from = fromCombo.getSelectionModel().getSelectedItem().toString();
        String to = toCombo.getSelectionModel().getSelectedItem();
        LocalDate date = datePciker.getValue();
        
        
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/MatchingSchedules.fxml"));
        root = (Parent) someLoader.load();
        Scene someScene = new Scene (root);
        
         MatchingSchedulesController p = someLoader.getController();
         
         p.setTableviewList(tableviewList);
         
        b2= RelationshipDatabaseClass.getInstance().getAllTripList();
        b1= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
            System.out.println(b1);
            System.out.println(b2);
        
        
        for(BusTripSchedule x : b1){
            //for(BusTripSchedule y : b2){
                
                    if( x.getTrip().getSource() .equals(from) && x.getTrip().getDestination().equals(to) && x.getScheduleDate().equals(date) ){
                        //System.out.println(y.getScheduleDate());
                        DummyClassForTableViewSchedule d = new DummyClassForTableViewSchedule(x.getTrip().getBus(),x.getTrip().getBus().getManufacturer(),x.getTrip().getSource(), x.getTrip().getDestination(),x.getTrip().getAdultFare(),x.getTrip().getTimeSlot(),x.getScheduleDate());
                        tableviewList.add(d);
                        p.setTableviewList(tableviewList);
                    }
                    
               
            }
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
            }
           
    
        
    }  
        
        /*
        else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(" ");
        alert.setTitle("Error ");
        alert.setHeaderText("Source and Destination can not be same");
        alert.showAndWait();
        }
        } 
        else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(" ");
        alert.setTitle("Error");
        alert.setHeaderText("Please select Source, Destination & Date");
        alert.showAndWait();
        }
    }
    
   
            */
        
    

    }

    @FXML
    private void switchtoScene2(ActionEvent event) throws IOException {
         new SceneSwicth(anchorpane3,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
         
        
         
        
    }
    
}
