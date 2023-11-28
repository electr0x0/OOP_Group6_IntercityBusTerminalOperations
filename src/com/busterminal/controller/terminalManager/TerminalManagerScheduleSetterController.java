/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

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
    private TableColumn<BusTripSchedule, Boolean> scheduleStatusCol;
    
    @FXML
    private MFXLegacyTableView<BusTripSchedule> tripScheduleTable;
    
    
    
    private ArrayList<BusTrip> allAvailableTripList;
    @FXML
    private AnchorPane rootPane;
    
    private ObservableList<BusTripSchedule> obvForTableView = FXCollections.observableArrayList();
    
    private ObservableList<BusTripSchedule> obvForTableViewFiltered = FXCollections.observableArrayList(); // Table where filtered items will go
    
    private ArrayList<BusTripSchedule> allAvailableTripSchedules = new ArrayList<>();
    
    private BusTripSchedule currentSchedule;
    
    private int currentScheduleID = 0;
    @FXML
    private TextField txtFieldSearchBar;
    @FXML
    private MFXButton buttonClickable;
    
    private MFXStageDialog dialog;
    private MFXGenericDialog dialogContent;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        setupColumns();
        
        // Load last scheduleID
        if (RelationshipDatabaseClass.getInstance().getCurrentScheduleID() != 0){
            currentScheduleID = RelationshipDatabaseClass.getInstance().getCurrentScheduleID();
        }
        
        if (RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules() != null){
            allAvailableTripSchedules = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
            obvForTableView.setAll(allAvailableTripSchedules);
            System.out.println(obvForTableView);
            tripScheduleTable.setItems(obvForTableView);
        }
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
        RelationshipDatabaseClass.getInstance().setCurrentScheduleID(currentScheduleID);
    }

    @FXML
    private void onClickAddScheduleButton(ActionEvent event) {
        BusTrip currentTrip = comboBoxAllTrips.getSelectedItem();
        LocalDate selectedDate = tripDatePicker.getValue();

        // Validation: Check if any required field is null or empty
        if (currentTrip == null) {
            showErrorDialog("Selection Error", "Please select a trip.");
            return;
        }

        if (selectedDate == null) {
            showErrorDialog("Date Error", "Please select a date.");
            return;
        }

        // Validation: Check if this trip is already assigned on the selected date
        for (BusTripSchedule schedule : allAvailableTripSchedules) {
            if (schedule.getTripId().equals(currentTrip.getTripID()) && schedule.getScheduleDate().equals(selectedDate)) {
                showErrorDialog("Schedule Conflict", "This trip is already scheduled on the selected date. Please select a different date or trip.");
                return;
            }
        }

        // Proceed with schedule creation
        currentSchedule = new BusTripSchedule(currentScheduleID,currentTrip, selectedDate);
        generateScheduleID();
        allAvailableTripSchedules.add(currentSchedule);
        obvForTableView.clear();
        obvForTableView.addAll(allAvailableTripSchedules);
        tripScheduleTable.setItems(obvForTableView);
        RelationshipDatabaseClass.getInstance().setAllAvailableTripSchedules(allAvailableTripSchedules);
        showSuccessDialog("Schedule Added", "The trip schedule has been successfully added.");
        clearAllFields();
    }
    
    private void clearAllFields(){
        comboBoxAllTrips.clearSelection();
        labelAdultFare.setText("Adult Fare       :");
        labelChildFare.setText("Child Fare        :");
        labelWeekendFare.setText("Weekend Fare  :");
    }

    
    private void setupColumns() {
        colScheduleId.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colScheduleDate.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        colTripId.setCellValueFactory(new PropertyValueFactory<>("tripId"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSourceDestination.setCellValueFactory(new PropertyValueFactory<>("sourceDestination"));
        colAssignedDriver.setCellValueFactory(new PropertyValueFactory<>("assignedDriver"));
        colAssignedVehicle.setCellValueFactory(new PropertyValueFactory<>("assignedVehicle"));
        scheduleStatusCol.setCellValueFactory(new PropertyValueFactory<>("tripScheduleStatus"));
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

    @FXML
    private void updateFareDetailsOnRowSelectionDetected(MouseEvent event) {
        if (!tripScheduleTable.getItems().isEmpty() && tripScheduleTable.getSelectionModel().getSelectedItem() != null ){
            BusTripSchedule selectedSchedule = tripScheduleTable.getSelectionModel().getSelectedItem();
            labelAdultFare.setText("Adult Fare       : "+selectedSchedule.getTrip().getAdultFare());
            labelChildFare.setText("Child Fare        : "+selectedSchedule.getTrip().getChildrenFare());
            labelWeekendFare.setText("Weekend Fare  : "+selectedSchedule.getTrip().getWeekendFare());
        }else{
            showErrorDialog("No Selection or Empty Table", "Please populate the table before clicking");
        }
        
    }
    
    private void dataFilter(){
        String searchCriteria = txtFieldSearchBar.getText().toLowerCase();

        // Creating a filtered list that wraps the original observable list
        FilteredList<BusTripSchedule> filteredData = new FilteredList<>(obvForTableView, b -> true);

        // Setting the filter predicate whenever the filter changes
        filteredData.setPredicate(busTripSchedule -> {
            // If filter text is empty, display all schedules.
            if (searchCriteria == null || searchCriteria.isEmpty()) {
                return true;
            }

            // Compare each column's value with the search text
            String lowerCaseSearchCriteria = searchCriteria.toLowerCase();

            if (String.valueOf(busTripSchedule.getScheduleId()).toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches schedule ID
            } else if (busTripSchedule.getScheduleDate().toString().toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches schedule date
            } else if (String.valueOf(busTripSchedule.getTripId()).toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches trip ID
            } else if (busTripSchedule.getTime().toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches time
            } else if (busTripSchedule.getSourceDestination().toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches source-destination
            } else if (busTripSchedule.getAssignedDriver().toLowerCase().contains(lowerCaseSearchCriteria)) {
                return true; // Filter matches assigned driver
            } else return String.valueOf(busTripSchedule.getAssignedVehicle()).toLowerCase().contains(lowerCaseSearchCriteria); // Filter matches assigned vehicle // Filter matches assigned vehicle
        });

        // Binding the SortedList to TableView
        SortedList<BusTripSchedule> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tripScheduleTable.comparatorProperty());

        // Setting the filtered and sorted data to the table view
        tripScheduleTable.setItems(sortedData);
    }
    
    

    @FXML
    private void filterTableViewFromSearch(InputMethodEvent event) {
        dataFilter();
    }

    @FXML
    private void filterTableViewFromSearch(KeyEvent event) {
        dataFilter();
    }

    @FXML
    private void OnClickdeleteModifyScheduleSelection(ActionEvent event) {
        if(tripScheduleTable.getSelectionModel().getSelectedItem() != null){
            deleteItemDialogCustom();
        }
        else{
            showErrorDialog("No selection detected/Empty Table", "Please select an item from the table to modify it!");
        }
    }
    
    public void deleteItemDialogCustom() {
        this.dialogContent = MFXGenericDialogBuilder.build().makeScrollable(true).get();
        
        this.dialog = MFXGenericDialogBuilder.build(dialogContent)
					.toStageDialogBuilder()
					.initModality(Modality.APPLICATION_MODAL)
					.setDraggable(true)
					.setTitle("Modification Options")
					.setOwnerNode(rootPane)
					.setScrimPriority(ScrimPriority.WINDOW)
					.setScrimOwner(true)
					.get();
        
        MFXButton deletebutton = new MFXButton("Delete");
        MFXButton modifyButton = new MFXButton("Modify");
        
        deletebutton.setOnAction(event -> {
            allAvailableTripSchedules.remove(tripScheduleTable.getSelectionModel().getSelectedItem()); // Schedule id is set to Index of item in List
            obvForTableView.clear();
            obvForTableView.setAll(allAvailableTripSchedules);
            RelationshipDatabaseClass.getInstance().setAllAvailableTripSchedules(allAvailableTripSchedules);
            dialog.close();
            showSuccessDialog("Success!", "Schedule Entry deletion has been successfull!");
        });
        
        modifyButton.setOnAction(event -> {
            ModificationSceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerScheduleInformationUpdate.fxml");
            dialog.close();
        });
        
        dialogContent.addActions(deletebutton,modifyButton);
        
        
        
        dialogContent.setMaxSize(15, 10);
	dialogContent.setHeaderText("Modification Options");
        dialogContent.setContentText("Select Delete to delete the selected Schedule or Modify");
        dialog.showDialog();
        
	}
    
    private void ModificationSceneSwitch(String fxmllocation){
        try {
            // Load the new content FXML file
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmllocation));
            AnchorPane newContent = loader.load();
            TerminalManagerScheduleInformationUpdateController controller = loader.getController();
            controller.setDataFromSceneSwitch(tripScheduleTable.getSelectionModel().getSelectedItem());
            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found","Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }
    
    

}
