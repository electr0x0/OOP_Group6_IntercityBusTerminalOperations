/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */

public class TerminalManagerTicketPricingController implements Initializable, Serializable {

    @FXML
    private MFXComboBox<String> comboPickupPoint;
    @FXML
    private MFXComboBox<String> comboDestinationPoint;
    @FXML
    private MFXToggleButton toggleFleetAC;
    @FXML
    private MFXToggleButton toggleFleetNonAC;
    @FXML
    private MFXTextField tFieldAdultFare;
    @FXML
    private MFXTextField tFieldChildFare;
    @FXML
    private MFXTextField tFieldWeekendFare;
    @FXML
    private MFXComboBox<String> comboBusList;
    @FXML
    private MFXComboBox<String> comboScheduleTimeList;
    @FXML
    private MFXComboBox<String> comboPickupBusStand;
    @FXML
    private MFXTextField comboPickupBusStandExtraDetails;
    @FXML
    private MFXComboBox<String> comboDropBusStand;
    @FXML
    private MFXTextField comboDropBusStandExtraDetails;
    
    private ToggleGroup busType = new ToggleGroup();

    @FXML
    private ImageView gearIcon;
    @FXML
    private AnchorPane rootPane;
    
    private  ArrayList<String> allLocations = new ArrayList<>();
    private  ArrayList<String> allTimes = new ArrayList<>();
    private  ArrayList<String> allBusStands = new ArrayList<>();
    private ArrayList<Bus> allAvailableBuses;
    @FXML
    private TableColumn<Bus, Integer> colBusID;
    @FXML
    private TableColumn<Bus, String> colBusType;
    @FXML
    private TableColumn<Bus, Integer> colSeatCapacity;
    @FXML
    private TableColumn<Bus, ArrayList<String>> colAvailableDrivers;
    @FXML
    private TableColumn<Bus, Boolean> colMtStatus;
    @FXML
    private MFXComboBox<String> comboListDriver;
    
    private ObservableList<Bus> currentBusInfoList = FXCollections.observableArrayList();
    @FXML
    private MFXLegacyTableView<Bus> busInfoShowerTable;
    @FXML
    private MFXProgressSpinner progressSpinner;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressSpinner.setVisible(false);
        setContextItems();
        loadArrayListsFromFile();
        setAllCombo();
        toggleFleetAC.setToggleGroup(busType);
        toggleFleetNonAC.setToggleGroup(busType);
        busListSetter();
        setupBusInfoTable();
        // 
        toggleFleetAC.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue) {
            comboBusList.getItems().clear();
            comboListDriver.getItems().clear();
            progressSpinner.setVisible(false);
        }});

        
    }
    
    
    private void setupBusInfoTable(){
        colBusID.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colBusType.setCellValueFactory(new PropertyValueFactory<>("busType"));
        colSeatCapacity.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        
       
        colAvailableDrivers.setCellValueFactory(new PropertyValueFactory<>("AssingedDrivers"));
        
        colBusID.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colMtStatus.setCellValueFactory(new PropertyValueFactory<>("mtStatus"));
    }
    
    @FXML
    private void updateBusTableInformation(ActionEvent event) {
        progressSpinner.setVisible(true);
        currentBusInfoList.clear();
        int busID = Integer.parseInt(comboBusList.getValue());

        new Thread(() -> {
            // Perform the delay in the background thread
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Handle the interruption appropriately
            }

            // Now back on the JavaFX Application Thread
            Platform.runLater(() -> {
                for (Bus busObj : allAvailableBuses) {
                    if (busObj.getBusId() == busID) {
                        currentBusInfoList.add(busObj);
                        for (String driver:busObj.getAssingedDrivers()){
                            comboListDriver.getItems().add(driver);
                        }
                    }
                }
                busInfoShowerTable.setItems(currentBusInfoList);
                progressSpinner.setVisible(false);
            });
        }).start();
    }


    @FXML
    private void updateCurrentScheduleDriver(ActionEvent event) {
    }

    @FXML
    private void onClickAddSchedule(ActionEvent event) {
        saveArrayListsToFile();
        RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
    }
    
    private void busListSetter(){
        RelationshipDatabaseClass.getInstance().loadFromFile();
        allAvailableBuses = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();

    }
    
    @FXML
    private void setBusListAC(MouseEvent event) {
       comboBusList.getItems().clear();
       for (Bus busObj: allAvailableBuses){
           if(busObj.getBusType().equals("AC")){
               String busID = "" + busObj.getBusId();
               comboBusList.getItems().add(busID);
           }
       }
    }

    @FXML
    private void setBusListNonAC(MouseEvent event) {
        comboBusList.getItems().clear();
        for (Bus busObj: allAvailableBuses){
           if(busObj.getBusType().equals("Non AC")){
               String busID = "" + busObj.getBusId();
               comboBusList.getItems().add(busID);
           }
       }
    }
    
    private void setContextItems(){
        ContextMenu contextMenu = new ContextMenu();
        
        MenuItem option1 = new MenuItem("Set Destination Locations and Define the Schedule List");
        option1.setOnAction(e -> setSceneToDestinationAndTimeDefiner());
        
        MenuItem option2 = new MenuItem("View All available Destination Locations and Time Slots");
        option2.setOnAction(e -> setSceneToAllCurrentAvaialableLocationTimeTableView());
        
        contextMenu.getItems().addAll(option1, option2);
        
        gearIcon.setOnMouseClicked(e -> {
            if (e.getButton().toString().equals("PRIMARY")) {
                contextMenu.show(gearIcon, e.getScreenX(), e.getScreenY());
            }
        });
    }
    
    private void setSceneToDestinationAndTimeDefiner(){
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerDestinationAndTimeDefiner.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            TerminalManagerDestinationAndTimeDefinerController controller = loader.getController();

            
            controller.setLocationAndTimeArrayList(allLocations,allTimes,allBusStands);

            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }
    
    private void setSceneToAllCurrentAvaialableLocationTimeTableView(){
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerCurrentBusRoutesAndStands.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            TerminalManagerCurrentBusRoutesAndStandsController controller = loader.getController();

            
            controller.setDataArrays(allLocations,allTimes,allBusStands);

            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }
    
    public void setComboItems(ArrayList<String> allLocations, ArrayList<String> allTimes, ArrayList<String> allBusStands ) {
       this.allLocations = allLocations;
       this.allTimes = allTimes;
       this.allBusStands = allBusStands;
    }
    
    public void setAllCombo(){
       comboPickupPoint.getItems().setAll(allLocations);
       comboDestinationPoint.getItems().setAll(allLocations);
       comboScheduleTimeList.getItems().setAll(allTimes);
       comboDropBusStand.getItems().setAll(allBusStands);
       comboPickupBusStand.getItems().setAll(allBusStands);
    }
    
    public void saveArrayListsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ScheduleData.bin"))) {
            out.writeObject(allLocations);
            out.writeObject(allTimes);
            out.writeObject(allBusStands);
            out.writeObject(allAvailableBuses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadArrayListsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("ScheduleData.bin"))) {
            allLocations = (ArrayList<String>) in.readObject();
            allTimes = (ArrayList<String>) in.readObject();
            allBusStands = (ArrayList<String>) in.readObject();
            allAvailableBuses = (ArrayList<Bus>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    

    
    
    
}
