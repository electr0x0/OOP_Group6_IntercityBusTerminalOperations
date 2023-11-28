/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerFinancialReportsController implements Initializable {

    @FXML
    private Label dataBaseTestResult;
    
    private ArrayList<BusTripSchedule> allAvailableTripSchedules;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allAvailableTripSchedules = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
        for (BusTripSchedule busSche: RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules()){
            System.out.println(busSche.toString());
        }
    } 
    

    @FXML
    private void databaseTestButton(ActionEvent event) {
        String labelText = "\n";
        
        for (BusTripSchedule busSche: allAvailableTripSchedules){
            labelText += busSche.getAssignedDriver()+"\n"+busSche.getAssignedDriver()+"\n"+busSche.getSourceDestination()+"\n"+busSche.getTime()+"\n";
        }
        
        dataBaseTestResult.setText(labelText);
    }
    
}
