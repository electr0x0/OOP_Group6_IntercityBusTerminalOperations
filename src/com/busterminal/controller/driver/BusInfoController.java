/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.driver;

import com.busterminal.controller.passenger.ReserveBusPassengerInfoController;
import com.busterminal.controller.passenger.ReservedBusListController;
import com.busterminal.model.Bus;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class BusInfoController implements Initializable {

    @FXML
    private TableColumn<Bus, Integer> busIdCol;
    @FXML
    private TableColumn<Bus, String> manufactureCol;
    @FXML
    private TableColumn<Bus, String> busTypeCol;
    @FXML
    private TableColumn<Bus, Integer> numberOfSeats;
    @FXML
    private TableView<Bus> tableViewBusInfo;

    ObservableList<Bus> b1 = FXCollections.observableArrayList();
    ArrayList<Bus> b2 = new ArrayList();
    @FXML
    private Label errorLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("busId"));
        manufactureCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        busTypeCol.setCellValueFactory(new PropertyValueFactory<>("busType"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        
        
        RelationshipDatabaseClass.loadFromFile();
        b2 = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
        
        for(Bus x: b2){
            b1.add(x);
            tableViewBusInfo.setItems(b1);
        }
        
        
    }    

    @FXML
    private void viewDetailsOnClick(ActionEvent event) {
        if( tableViewBusInfo.getSelectionModel().getSelectedItem() != null){
        Bus selectedBus = tableViewBusInfo.getSelectionModel().getSelectedItem();
        
        int regNum = selectedBus.getBusRegNum();
        int yearManufacture = selectedBus.getYearOfManufacture();
        ArrayList<String> assignedDriver = selectedBus.getAssingedDrivers();
        ArrayList<String> availableTime = selectedBus.getAvailableTimeSlots();
        ArrayList<String> occupiedTime = selectedBus.getOccupiedTimeSlots();
                
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/BusInfoDetail.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ReservedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BusInfoDetailController controller = loader.getController();
        controller.setItems(regNum, yearManufacture, assignedDriver, availableTime, occupiedTime);

        
        //controller.setValues(bustype, fare,date);

        
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        errorLabel.setText("");
        }
        else{
            errorLabel.setText("Please Select a bus");
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
    
}
