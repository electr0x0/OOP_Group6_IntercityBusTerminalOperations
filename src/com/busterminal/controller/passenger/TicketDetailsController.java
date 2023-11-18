/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.Passenger;
import com.busterminal.model.SceneSwicth;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class TicketDetailsController implements Initializable {

    @FXML
    private AnchorPane anchorPane5;
    @FXML
    private ComboBox<String> seatNumberCombo;
    private ComboBox<Integer> quantityCombo;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private Label departureLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label dateOfTravelLabel;
    @FXML
    private Label busDetailsLabel;
    @FXML
    private TextField nameTextField1;
    
    private ArrayList<Passenger> passengerList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seatNumberCombo.getItems().addAll("A1","A2","B1","B2","C1","C2","D1","D2","E1","E2","F1","F2","G1","G2","H1","H2");
        //quantityCombo.getItems().addAll(1,2,3,4,5,6);
        passengerList= new ArrayList();
    }    

    @FXML
    private void confirmInfromationOnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(" Click on OK to return to Dashboard");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Ticket Purchase Succesful");
        //alert.showAndWait();
        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(okButton, cancelButton);


    alert.showAndWait().ifPresent(buttonType -> {
    if (buttonType == okButton ) {
        try {
            new SceneSwicth(anchorPane5,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        } catch (IOException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }             
    });
    
    
    String name = nameTextField1.getText();
    
    String email = emailTextField.getText();
    
    int phone = Integer.parseInt(phoneTextField.getText());
            
    String seatNumber =seatNumberCombo.getValue();
    
    
    
    
    Passenger p = new Passenger(name,email,seatNumber,phone);
    
    passengerList.add(p);
    
    try {
            FileOutputStream fs = new FileOutputStream("PassengerData.bin",true);
            DataOutputStream ds = new DataOutputStream(fs);
            
            for(Passenger x : passengerList){
                ds.writeUTF(x.getName());
                ds.writeUTF(x.getEmail());
                ds.writeUTF(x.getSeatNumber());
                ds.writeInt(x.getContactNum());
                
                
            }
            ds.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
            
  }

    @FXML
    private void switchToMatchingScheduleScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorPane5,"/com/busterminal/views/passenger/MatchingSchedules.fxml");
        
    }
}
