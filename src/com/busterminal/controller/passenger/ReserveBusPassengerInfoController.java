/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

//import com.busterminal.views.passenger.*;
import com.busterminal.model.Passenger;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReserveBusPassengerInfoController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField nameTextField1;
    
    
    
    private ArrayList<Passenger> passengerList= new ArrayList();
    private ArrayList<String> helperList= new ArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmInfromationOnClick(ActionEvent event) throws IOException {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.setContentText(" Click on OK to return to Dashboard");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Bus Reservation Succesful");
        alert.showAndWait();
       
    
    
    String name = nameTextField1.getText();
    
    String email = emailTextField.getText();
    
    int phone = Integer.parseInt(phoneTextField.getText());
            
    
    
    
    
    Passenger p = new Passenger(name,email,helperList,phone);
    
    passengerList.add(p);
    
    try {
            FileOutputStream fs = new FileOutputStream("ReservePassengerData.bin",true);
            DataOutputStream ds = new DataOutputStream(fs);
            
            for(Passenger x : passengerList){
                ds.writeUTF(x.getName());
                ds.writeUTF(x.getEmail());
                for(String y: x.getSeatNumber()){
                    ds.writeUTF(y);
                    
                }
                ds.writeInt(x.getContactNum());
                
                
            }
            ds.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
            
  }

    }

    

