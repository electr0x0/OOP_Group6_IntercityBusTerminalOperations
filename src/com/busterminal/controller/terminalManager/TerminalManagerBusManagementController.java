/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerBusManagementController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXTextField txtFieldBusID;
    @FXML
    private MFXTextField txtFieldBusCapacity;
    @FXML
    private MFXSlider spinnerBusCapacity;
    @FXML
    private MFXComboBox<String> comboBoxBusType;
    @FXML
    private MFXComboBox<String> comboBusManufacturer;
    @FXML
    private MFXCheckListView<String> driverCheckListView;
    @FXML
    private MFXTextField txtFieldBusYear;

    private int idCounter = 0;

    private ObservableList<String> driverNames = FXCollections.observableArrayList();
    private ArrayList<String> selectedDrivers = new ArrayList<>();

    private Bus currentBus;

    private ArrayList<Bus> allAvailableBuses = new ArrayList<>();
    @FXML
    private MFXTextField txtFiledBusRegNum;
    @FXML
    private ImageView imageViewMoreSettings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Example driver names (For now) will add driver instances from Samin later
        driverNames.addAll("Driver A", "Driver B", "Driver C", "Driver D", "Driver E");
        // Populate bus types
        comboBoxBusType.setItems(FXCollections.observableArrayList("AC", "Non AC"));

        // Populate bus manufacturers
        comboBusManufacturer.setItems(FXCollections.observableArrayList(
                "Manufacturer A", "Manufacturer B", "Manufacturer C" // Replace with actual names
        ));

        // Populate driver names
        driverCheckListView.setItems(driverNames); // Assuming driverNames is populated

        // Sync slider with text field
        spinnerBusCapacity.valueProperty().addListener((obs, oldVal, newVal) -> {
            txtFieldBusCapacity.setText(String.valueOf(newVal.intValue()));
        });

        RelationshipDatabaseClass.getInstance().loadFromFile();

        // Sync text field with slider
        txtFieldBusCapacity.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                int capacity = Integer.parseInt(newVal);
                spinnerBusCapacity.setValue(capacity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });

        if (RelationshipDatabaseClass.getInstance().getBusIdCounter() != 0) {
            idCounter = RelationshipDatabaseClass.getInstance().getBusIdCounter();
        }

        if (RelationshipDatabaseClass.getInstance().getAllAvailableBuses() != null) {
            allAvailableBuses = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
        }
        
        setupContextMenuOnGear();
    }

    public void setupContextMenuOnGear() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem option1 = new MenuItem("View and Modify All Available Buses");
        option1.setOnAction(x -> 
                SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerAllAvailableBusView.fxml"));
        
        contextMenu.getItems().add(option1);
        
        imageViewMoreSettings.setOnMouseClicked(e -> {
            if (e.getButton().toString().equals("PRIMARY")) {
                contextMenu.show(imageViewMoreSettings, e.getScreenX(), e.getScreenY());
            }
        });
        
    }
    
    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }

    public void AddSelectedDriversToCurrentDrivers() {
        selectedDrivers.clear();
        String driverNames = "\n";
        for (String driverName : driverCheckListView.getSelectionModel().getSelectedValues()) {
            selectedDrivers.add(driverName);
            driverNames += driverName + "\n";
        }
        driverCheckListView.getSelectionModel().clearSelection();
        MFXDialog alertDialog = new MFXDialog("Drivers Added", "Assinged Drivers:" + driverNames, "Close", rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onClickGenBusID(ActionEvent event) {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        // Generate ID: Today's date + counter
        String busId = formattedDate + idCounter;

        // Update the text field with the new ID
        txtFieldBusID.setText(busId);

        // Increment the counter for the next ID
        idCounter++;

        RelationshipDatabaseClass.getInstance().setBusIdCounter(idCounter);
        RelationshipDatabaseClass.getInstance().saveToFile();

    }

    private void AddBus() {
        String busID = txtFieldBusID.getText();
        String busType = comboBoxBusType.getValue();
        String busCapacity = txtFieldBusCapacity.getText();
        String busManufacturer = comboBusManufacturer.getValue();
        String busManYear = txtFieldBusYear.getText();
        String busRegNum = txtFiledBusRegNum.getText();

        if (!Validator.isInteger(busID)) {
            showErrorDialog("Invalid Bus ID", "Bus ID must be a number.");
            return;
        }

        if (busType == null || busType.isEmpty()) {
            showErrorDialog("Missing Bus Type", "Please select a bus type.");
            return;
        }

        if (!Validator.isInteger(busCapacity)) {
            showErrorDialog("Invalid Bus Capacity", "Bus Capacity must be a number.");
            return;
        }

        if (busManufacturer == null || busManufacturer.isEmpty()) {
            showErrorDialog("Missing Manufacturer", "Please select a bus manufacturer.");
            return;
        }

        if (!Validator.isInteger(busManYear)) {
            showErrorDialog("Invalid Manufacture Year", "Year of Manufacture must be a number.");
            return;
        }

        if (!Validator.isInteger(busRegNum)) {
            showErrorDialog("Invalid Registration Number", "Registration Number must be a number.");
            return;
        }

        if (selectedDrivers.isEmpty()) {
            showErrorDialog("No Drivers Assigned", "Please assign at least one driver.");
            return;
        }

        currentBus = new Bus(Integer.parseInt(busID), Integer.parseInt(busCapacity), busType, Integer.parseInt(busRegNum), busManufacturer, Integer.parseInt(busManYear), selectedDrivers);
        allAvailableBuses.add(currentBus);

        RelationshipDatabaseClass.getInstance().setAllAvailableBuses(allAvailableBuses);
        RelationshipDatabaseClass.getInstance().saveToFile();

        showSuccessDialog("Bus Added Successfully", "The bus has been added to the system.");
    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    public void clearALlFields() {

    }

    @FXML
    private void onClickAddBusButton(ActionEvent event) {
        AddBus();
    }

    @FXML
    private void onClickAssignDrivers(ActionEvent event) {
        AddSelectedDriversToCurrentDrivers();
    }

}
