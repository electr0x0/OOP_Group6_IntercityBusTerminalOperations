/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.DummyTableViewAllAvailableLocationTimeSlots;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerCurrentBusRoutesAndStandsController implements Initializable {
    
    private  ArrayList<String> allLocations;
    private  ArrayList<String> allTimes;
    private  ArrayList<String> allBusStands;
    private  ObservableList<DummyTableViewAllAvailableLocationTimeSlots> data = FXCollections.observableArrayList();
    int maxSize;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label totalTravelLocationLabel;
    @FXML
    private Label totalTimeSlotsLabel;
    @FXML
    private Label totalBusStandLocationLabel;
    @FXML
    private MFXTableView<DummyTableViewAllAvailableLocationTimeSlots> locationTimeTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
    public void setDataArrays(ArrayList<String> allLocations, ArrayList<String> allTimes, ArrayList<String> allBusStands) {
        this.allLocations = allLocations;
        this.allTimes = allTimes;
        this.allBusStands = allBusStands;
        maxSize = allLocations.size();
        setObvList();
        setupTable();
    }
    
    public void setObvList(){
        for (int i = 0; i < maxSize; i++) {
            String location = i < allLocations.size() ? allLocations.get(i) : "";
            String time = i < allTimes.size() ? allTimes.get(i) : "";
            String busStand = i < allBusStands.size() ? allBusStands.get(i) : "";

            data.add(new DummyTableViewAllAvailableLocationTimeSlots(location, time, busStand));
        }
    }
    
    
    public void setupTable(){
        
        MFXTableColumn<DummyTableViewAllAvailableLocationTimeSlots> locationColumn = new MFXTableColumn<>("Location", true, Comparator.comparing(DummyTableViewAllAvailableLocationTimeSlots::getLocation));
        locationColumn.setRowCellFactory(model -> new MFXTableRowCell<>(DummyTableViewAllAvailableLocationTimeSlots::getLocation));
        

        MFXTableColumn<DummyTableViewAllAvailableLocationTimeSlots> timeColumn = new MFXTableColumn<>("Time Slots", true, Comparator.comparing(DummyTableViewAllAvailableLocationTimeSlots::getTime));
        timeColumn.setRowCellFactory(model -> new MFXTableRowCell<>(DummyTableViewAllAvailableLocationTimeSlots::getTime));

        MFXTableColumn<DummyTableViewAllAvailableLocationTimeSlots> busStandColumn = new MFXTableColumn<>("Bus Stand", true, Comparator.comparing(DummyTableViewAllAvailableLocationTimeSlots::getbusStand));
        busStandColumn.setRowCellFactory(model -> new MFXTableRowCell<>(DummyTableViewAllAvailableLocationTimeSlots::getbusStand));

        locationTimeTableView.getTableColumns().addAll(locationColumn, timeColumn, busStandColumn);
        locationTimeTableView.setItems(data);
        
        

    }

    @FXML
    private void onClickBack(ActionEvent event) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerTicketPricing.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            TerminalManagerTripAndFareMangement controller = loader.getController();

            
            controller.setComboItems(allLocations,allTimes,allBusStands);
            controller.setAllCombo();
                    

            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }
    
    
}
