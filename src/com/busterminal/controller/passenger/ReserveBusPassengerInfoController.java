/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

//import com.busterminal.views.passenger.*;
import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.model.Passenger;
import com.busterminal.model.Reservation;
import com.busterminal.model.ReserveBus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
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
    private ArrayList<Reservation> rervationList = new ArrayList();
    @FXML
    private TextField reserveIdTextField;
    @FXML
    private TextField busTypeTextField;
    @FXML
    private TextField fareTextField;
    @FXML
    private TextField dateTextField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Random rand = new Random();
        //rand.nextInt(1000);
         reserveIdTextField.setText(Integer.toString(rand.nextInt(1000)));
    }    

    @FXML
    private void confirmInfromationOnClick(ActionEvent event) throws IOException {
        
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //alert.setContentText(" Click on OK to return to Dashboard");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Bus Reservation Succesful");
        alert.showAndWait();
       
    
    //int reserveId = Integer.parseInt(reserveIdTextField.getText());
    //String name = nameTextField1.getText();
    
    //String email = emailTextField.getText();
    
    //int phone = Integer.parseInt(phoneTextField.getText());
            
    //ReserveBus re = new ReserveBus(reserveId,busTypeTextField.getText(),Integer.parseInt(fareTextField.getText()),LocalDate.parse(dateTextField.getText()));
    //Reservation r1 = new Reservation(name,email,phone,re);
    Passenger p = new Passenger();
    p.doReserve(reserveIdTextField.getText(),nameTextField1.getText(), emailTextField.getText(), Integer.parseInt(phoneTextField.getText()), 
                             busTypeTextField.getText(), Integer.parseInt(fareTextField.getText()), LocalDate.parse(dateTextField.getText()));
    
   
    
    
  
            
  }
    
    
    public void setValues(String bus, int fare, String date){
        busTypeTextField.setText(bus);
        fareTextField.setText(String.valueOf(fare));
        dateTextField.setText(date);
        
    }

    @FXML
    private void readFile(ActionEvent event) {
        
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("ReservationList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Reservation f2;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    f2= (Reservation)ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(f2);
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch(Exception e){
                // handling code
            }//nested catch     
            //outputTextArea.appendText("All objects are loaded successfully...\n");            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }           
    
    }

    }

    

