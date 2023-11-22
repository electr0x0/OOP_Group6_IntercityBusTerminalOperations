/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerScheduleSetterController implements Initializable {

    @FXML
    private MFXFilterComboBox<BusTrip> comboBoxAllTrips;
    @FXML
    private MFXDatePicker tripDatePicker;
    @FXML
    private Label labelAdultFare;
    @FXML
    private Label labelChildFare;
    @FXML
    private Label labelWeekendFare;
    @FXML
    private TableColumn<BusTripSchedule, Integer> colScheduleId;
    @FXML
    private TableColumn<BusTripSchedule, String> colScheduleDate;
    @FXML
    private TableColumn<BusTripSchedule, Integer> colTripId;
    @FXML
    private TableColumn<BusTripSchedule, String> colTime;
    @FXML
    private TableColumn<BusTripSchedule, String> colSourceDestination;
    @FXML
    private TableColumn<BusTripSchedule, String> colAssignedDriver;
    @FXML
    private TableColumn<BusTripSchedule, String> colAssignedVehicle;
    @FXML
    private MFXLegacyTableView<BusTripSchedule> tripScheduleTable;
    
    private ArrayList<BusTrip> allAvailableTripList;
    private ArrayList<BusTripSchedule> allAvailableBusTripSchedule;
    @FXML
    private AnchorPane rootPane;
    
    private ObservableList<BusTripSchedule> obvForTableView = FXCollections.observableArrayList();
    
    private ArrayList<BusTripSchedule> allAvailableTripSchedules = new ArrayList<>();
    
    private BusTripSchedule currentSchedule;
    
    private int currentScheduleID = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        
//        if (RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules() != null){
//            allAvailableTripSchedules = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
//        }
    }
    

    private void loadData(){
        try {
        allAvailableTripList = RelationshipDatabaseClass.getInstance().getAllTripList();
        System.out.println("Trip List assigned to Controller" + allAvailableTripList.get(0));
        comboBoxAllTrips.getItems().addAll(allAvailableTripList);
        }
        catch(NullPointerException npe){
            showErrorDialog("Error loading Data","Trip List Data loading faield, Please populate Trip Information before trying to Schedule one!");
        }
    }
    
    public void generateScheduleID(){
        currentScheduleID++;
    }

    @FXML
    private void onClickAddScheduleButton(ActionEvent event) {
        BusTrip currentTrip = comboBoxAllTrips.getSelectedItem();
        LocalDate selectedDate = tripDatePicker.getValue();
        String sourceAndDestination = currentTrip.getSource() + "-" + currentTrip.getDestination();
        String assignedDriver = currentTrip.getDriver();
        String busID = ""+currentTrip.getBus().getBusId();
        currentSchedule = new BusTripSchedule(currentScheduleID,selectedDate,5, currentTrip.getTimeSlot(), sourceAndDestination,assignedDriver,busID  );
        generateScheduleID();
        allAvailableTripSchedules.add(currentSchedule);
        obvForTableView.addAll(allAvailableTripSchedules);
        tripScheduleTable.setItems(obvForTableView);
        RelationshipDatabaseClass.getInstance().setAllAvailableTripSchedules(allAvailableTripSchedules);
    }
    
    private void setupColumns() {
        colScheduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colScheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        colTripId.setCellValueFactory(new PropertyValueFactory<>("tripId"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSourceDestination.setCellValueFactory(new PropertyValueFactory<>("sourceDestination"));
        colAssignedDriver.setCellValueFactory(new PropertyValueFactory<>("assignedDriver"));
        colAssignedVehicle.setCellValueFactory(new PropertyValueFactory<>("assignedVehicle"));
    }
    
    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert",rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success",rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onTripSelection(ActionEvent event) {
        String adFare = "Adult Fare       : "+comboBoxAllTrips.getSelectedItem().getAdultFare();
        String chFare = "Child Fare        : "+comboBoxAllTrips.getSelectedItem().getChildrenFare();
        String wkEndFare = "Weekend Fare  : "+comboBoxAllTrips.getSelectedItem().getWeekendFare();
        labelAdultFare.setText(adFare);
        labelChildFare.setText(chFare);
        labelWeekendFare.setText(wkEndFare);
        
    }
}
