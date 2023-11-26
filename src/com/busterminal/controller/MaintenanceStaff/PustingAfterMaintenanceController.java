/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.Database;
import com.busterminal.model.MaintenanceHistory;
import com.busterminal.model.PopUp;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class PustingAfterMaintenanceController implements Initializable {

    @FXML
    private TextField busIdTF;
    @FXML
    private TextField issueResolvedTF;
    @FXML
    private TableView<MaintenanceHistory> maintenanceHistoryTable;
    @FXML
    private TableColumn<MaintenanceHistory, Integer> busIdCol;
    @FXML
    private TableColumn<MaintenanceHistory, LocalDate> lastServicingCol;
    @FXML
    private TableColumn<MaintenanceHistory, String> issueResolvedCol;

    private ObservableList<MaintenanceHistory> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastServicingCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        issueResolvedCol.setCellValueFactory(new PropertyValueFactory<>("issueResolved"));
        maintenanceHistoryTable.getItems().addAll(Database.getInstanceBinFile("MaintenanceHistory.bin"));
        maintenanceHistoryTable.setVisible(true);
    }

    @FXML
    private void sendOnMouseClick(ActionEvent event) {
        if (!busIdTF.getText().equals("") && !issueResolvedTF.getText().equals("")) {
            try {
                int busId = Integer.parseInt(busIdTF.getText());
                String issueResolved = issueResolvedTF.getText(); 
                MaintenanceHistory mlist = new MaintenanceHistory(busId, LocalDate.now(), issueResolved);
                data.add(mlist);
                Database.writeToBinFile("MaintenanceHistory.bin", data);
                maintenanceHistoryTable.setItems(data);
            } catch (Exception e) {
                PopUp.showMessage("Warning", "Bus Id shoud be Integer" + "\n Please try agin with valid Bus Id");
            }
        } else {
            PopUp.showMessage("Alert", "Please Enter the busId"+"\n Enter the Resolved issue..!");
        }

    }

    @FXML
    private void viewMaintenanceHistoryOnMouseClick(ActionEvent event) {
    }


}
