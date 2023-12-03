/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.BusTrip;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class TerminalManagerViewCurrentTripTableController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<BusTrip> tripTableView;
    @FXML
    private TableColumn<BusTrip, String> colTripID;
    @FXML
    private TableColumn<BusTrip, Integer> colBusID;
    @FXML
    private TableColumn<BusTrip, String> colTime;
    @FXML
    private TableColumn<BusTrip, String> colBusType;
    @FXML
    private TableColumn<BusTrip, Integer> colChildFare;
    @FXML
    private TableColumn<BusTrip, Integer> colAdultFare;
    @FXML
    private TableColumn<BusTrip, Integer> colWeekendFare;
    @FXML
    private TableColumn<BusTrip, String> colSource;
    @FXML
    private TableColumn<BusTrip, String> colDestination;
    @FXML
    private TableColumn<BusTrip, String> colDriver;
    @FXML
    private TableColumn<BusTrip, Boolean> colStatus;

    private ObservableList<BusTrip> tablViewList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
    }

    private void setupTable() {
        colTripID.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        colBusID.setCellValueFactory(new PropertyValueFactory<>("tripBusID"));
        colBusType.setCellValueFactory(new PropertyValueFactory<>("fleetType"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        colChildFare.setCellValueFactory(new PropertyValueFactory<>("childrenFare"));
        colAdultFare.setCellValueFactory(new PropertyValueFactory<>("adultFare"));
        colWeekendFare.setCellValueFactory(new PropertyValueFactory<>("weekendFare"));
        colSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDriver.setCellValueFactory(new PropertyValueFactory<>("driver"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("tripStatus"));
    }

    public void setTableData(ArrayList<BusTrip> allTrips) {
        if (allTrips != null) {
            tablViewList.setAll(allTrips);
            tripTableView.setItems(tablViewList);
        }

    }

    private void goBack() {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerTicketPricing.fxml"));
            AnchorPane newContent = loader.load();

            rootPane.getChildren().setAll(newContent);

        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog alertDialog = new MFXDialog("FXML not Found", "Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close", rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void goBackToFareManagementView(MouseEvent event) {
        goBack();
    }

}
