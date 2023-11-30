/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerAllAvailableBusViewController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<Bus> tableViewBus;
    @FXML
    private TableColumn<Bus, Integer> colBusID;
    @FXML
    private TableColumn<Bus, Integer> colBusCapacity;
    @FXML
    private TableColumn<Bus, String> colBusType;
    @FXML
    private TableColumn<Bus, Integer> colBusReg;
    @FXML
    private TableColumn<Bus, String> colBusManufac;
    @FXML
    private TableColumn<Bus, ArrayList<String>> colAssignedDrivers;
    @FXML
    private TableColumn<Bus, String> colMtStatus;
    
    private ArrayList<Bus> allAvailableBus;
    
    private ObservableList<Bus> tableViewBusList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(RelationshipDatabaseClass.getInstance().getAllAvailableBuses() != null){
            allAvailableBus = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
            tableViewBusList.setAll(allAvailableBus);
            tableViewBus.getItems().setAll(tableViewBusList);
        }
        setupTable();
    }
    
    private void setupTable(){
        colBusID.setCellValueFactory(new PropertyValueFactory<>("busId"));
        colBusCapacity.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        colBusType.setCellValueFactory(new PropertyValueFactory<>("busType"));
        colBusManufac.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colAssignedDrivers.setCellValueFactory(new PropertyValueFactory<>("assingedDrivers"));
        colMtStatus.setCellValueFactory(new PropertyValueFactory<>("mtStatus"));
        colBusReg.setCellValueFactory(new PropertyValueFactory<>("busRegNum"));
    }
    
    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path",rootPane);
        }
    }

    @FXML
    private void onClickDeleteBus(ActionEvent event) {
        if (tableViewBus.getSelectionModel().getSelectedItem() != null){
            for (Bus busObj: allAvailableBus){
                if(tableViewBus.getSelectionModel().getSelectedItem().equals(busObj)){
                    allAvailableBus.remove(busObj);
                    MFXDialog.showSuccessDialog("Success!", "Successfully Deleted", rootPane);
                    break;
                }
            }
            RelationshipDatabaseClass.getInstance().setAllAvailableBuses(allAvailableBus);
            tableViewBusList.setAll(allAvailableBus);
            tableViewBus.getItems().setAll(tableViewBusList);
        }
        else{
            MFXDialog.showErrorDialog("No Selection!", "Please Select a Valid Item Before Deleting!", rootPane);
        }
    }

    @FXML
    private void onClickBackButton(MouseEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerBusManagement.fxml");
    }
    
}
