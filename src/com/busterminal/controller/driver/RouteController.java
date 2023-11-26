/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.driver;

import com.busterminal.model.BusTripSchedule;
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
public class RouteController implements Initializable {

    @FXML
    private TableView<BusTripSchedule> tableViewCheckSchedule;
    @FXML
    private TableColumn<BusTripSchedule, String> assignedBusCol;
    @FXML
    private TableColumn<BusTripSchedule, String > assignedDriverCol;
    @FXML
    private TableColumn<BusTripSchedule, LocalDate> dateCol;
    @FXML
    private TableColumn<BusTripSchedule, String> sourceDestinationCol;

   
    ObservableList<BusTripSchedule> a1 =  FXCollections.observableArrayList();
    ArrayList<BusTripSchedule> b1 =  new ArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         
        assignedBusCol.setCellValueFactory(new PropertyValueFactory<>("assignedVehicle"));
        assignedDriverCol.setCellValueFactory(new PropertyValueFactory<>("assignedDriver"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        
        sourceDestinationCol.setCellValueFactory(new PropertyValueFactory<>("sourceDestination"));
        

        
         RelationshipDatabaseClass.loadFromFile();
         
         b1= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
         
         for( BusTripSchedule x: b1){
             a1.add(x);
             tableViewCheckSchedule.setItems(a1);
             
             
         }
         
      
    }    

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }
    
}
