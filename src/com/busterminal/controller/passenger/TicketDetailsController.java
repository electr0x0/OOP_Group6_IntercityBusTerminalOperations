/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.BusSchedule;
import com.busterminal.model.Passenger;
import com.busterminal.model.SceneSwicth;
import com.busterminal.model.Ticket;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class TicketDetailsController implements Initializable {

    @FXML
    private AnchorPane anchorPane5;
   
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField nameTextField1;
    
    private ArrayList<Passenger> passengerList;
    private ArrayList<Ticket> ticketList;
    @FXML
    private Label departureLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label dateOfTravelLabel;
    @FXML
    private Label busDetailsLabel;
    @FXML
    private Button a1;
    @FXML
    private Button a2;
    @FXML
    private Button a3;
    @FXML
    private Button a4;
    @FXML
    private Button a5;
    @FXML
    private Button a6;
    @FXML
    private Button a7;
    @FXML
    private Button a8;
    @FXML
    private Button a9;
    @FXML
    private Button a10;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button b9;
    @FXML
    private Button b10;
    
    private ArrayList<String> seathelperList = new ArrayList();
    @FXML
    private TextField ticketIdTextField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //seatNumberCombo.getItems().addAll("A1","A2","B1","B2","C1","C2","D1","D2","E1","E2","F1","F2","G1","G2","H1","H2");
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
        System.out.println(seathelperList);
        
        

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
            
    //String seatNumber =seatNumberCombo.getValue();
    
    
    
    
    Passenger p = new Passenger(name,email,phone);
    BusSchedule b = new BusSchedule();
    
    Ticket t1 = new Ticket(ticketIdTextField.getText(),p,b,23.0,seathelperList);
    
    passengerList.add(p);
    ticketList.add(t1);
    
    try {
            FileOutputStream fs = new FileOutputStream("PassengerData.bin",true);
            DataOutputStream ds = new DataOutputStream(fs);
            
            for(Passenger x : passengerList){
                ds.writeUTF(x.getName());
                ds.writeUTF(x.getEmail());     
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

    @FXML
    private void A1SeatButtonOnClick(ActionEvent event) {
        a1.setDisable(true);
        a1.setStyle("-fx-background-color: green;");
        seathelperList.add(a1.getText());
        
    }

    @FXML
    private void A2SeatButtonOnClick(ActionEvent event) { 
        a2.setDisable(true);
        a2.setStyle("-fx-background-color: green;");
        seathelperList.add(a2.getText());
    }

    @FXML
    private void A3SeatButtonOnClick(ActionEvent event) { 
        a3.setDisable(true);a3.setStyle("-fx-background-color: green;");
        seathelperList.add(a3.getText());
    }

    @FXML
    private void A4SeatButtonOnClick(ActionEvent event) {
        a4.setDisable(true);a4.setStyle("-fx-background-color: green;");
        seathelperList.add(a4.getText());
    }

    @FXML
    private void A5SeatButtonOnClick(ActionEvent event) { 
        a5.setDisable(true);a5.setStyle("-fx-background-color: green;");
        seathelperList.add(a5.getText());
    }

    @FXML
    private void A6SeatButtonOnClick(ActionEvent event) { 
        a6.setDisable(true);a6.setStyle("-fx-background-color: green;");
        seathelperList.add(a6.getText());
    }

    @FXML
    private void A7SeatButtonOnClick(ActionEvent event) { 
        a7.setDisable(true);a7.setStyle("-fx-background-color: green;");seathelperList.add(a7.getText());
    }

    @FXML
    private void A8SeatButtonOnClick(ActionEvent event) { 
        a8.setDisable(true); a8.setStyle("-fx-background-color: green;");seathelperList.add(a8.getText());
    }

    @FXML
    private void A9SeatButtonOnClick(ActionEvent event) { 
        a9.setDisable(true);a9.setStyle("-fx-background-color: green;");seathelperList.add(a9.getText());
    }

    @FXML
    private void A10SeatButtonOnClick(ActionEvent event) { 
        a10.setDisable(true);a10.setStyle("-fx-background-color: green;");seathelperList.add(a10.getText());
    }

    @FXML
    private void B1SeatButtonOnClick(ActionEvent event) { 
        b1.setDisable(true);
        b1.setStyle("-fx-background-color: green;");seathelperList.add(b1.getText());
    }

    @FXML
    private void B2SeatButtonOnClick(ActionEvent event) { 
        b2.setDisable(true); b2.setStyle("-fx-background-color: green;");seathelperList.add(b2.getText());
    }

    @FXML
    private void B3SeatButtonOnClick(ActionEvent event) { 
        b3.setDisable(true); b3.setStyle("-fx-background-color: green;");seathelperList.add(b3.getText());
    }

    @FXML
    private void B4SeatButtonOnClick(ActionEvent event) { 
        b4.setDisable(true); b4.setStyle("-fx-background-color: green;");seathelperList.add(b4.getText());
    }

    @FXML
    private void B5SeatButtonOnClick(ActionEvent event) { 
        b5.setDisable(true); b5.setStyle("-fx-background-color: green;");seathelperList.add(b5.getText());
    }

    @FXML
    private void B6SeatButtonOnClick(ActionEvent event) { 
        b6.setDisable(true); b6.setStyle("-fx-background-color: green;");seathelperList.add(b6.getText());
    }

    @FXML
    private void B7SeatButtonOnClick(ActionEvent event) { 
        b7.setDisable(true); b7.setStyle("-fx-background-color: green;");seathelperList.add(b7.getText());
    }

    @FXML
    private void B8SeatButtonOnClick(ActionEvent event) {
        b8.setDisable(true); b8.setStyle("-fx-background-color: green;");seathelperList.add(b8.getText());
    }


    @FXML
    private void B10SeatButtonOnClick(ActionEvent event) {
        b10.setDisable(true); b9.setStyle("-fx-background-color: green;");seathelperList.add(b10.getText());
    }

    @FXML
    private void B9SeatButtonOnClick(ActionEvent event) { 
        b9.setDisable(true);b10.setStyle("-fx-background-color: green;");seathelperList.add(b9.getText());
    } 
}
