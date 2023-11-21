/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Bus;
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
    
    private ArrayList<Bus> availableBuses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadObjectDB();
        availableBuses = RelationshipDatabaseClass.getInstance().getAllAvailableBuses();
    } 
    
    public void loadObjectDB(){
        RelationshipDatabaseClass.getInstance().loadFromFile();
    }

    @FXML
    private void databaseTestButton(ActionEvent event) {
        String labelText = "\n";
        
        for (Bus busIns: availableBuses){
            labelText += busIns.toString()+"\n";
        }
        
        dataBaseTestResult.setText(labelText);
    }
    
}
