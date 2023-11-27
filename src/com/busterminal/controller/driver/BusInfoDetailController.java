/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.driver;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class BusInfoDetailController implements Initializable {

    @FXML
    private TextField busRegNum;
    @FXML
    private TextField yearOfManuField;
    @FXML
    private TextArea assignedText;
    @FXML
    private TextArea availabelText;
    @FXML
    private TextArea occupiedText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void setItems(int regNum, int yearManufacture, ArrayList<String> assignedDriver,ArrayList<String>  availableTime, ArrayList<String> occupiedTime){
        busRegNum.setText(Integer.toString(regNum));
        yearOfManuField.setText(Integer.toString(yearManufacture));
        assignedText.appendText(assignedDriver.toString());   
        availabelText.appendText(availableTime.toString());  
        occupiedText.appendText(occupiedTime.toString());
        
        
        
        
    }
    
}
