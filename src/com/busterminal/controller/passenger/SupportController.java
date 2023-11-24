/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.Feedback;
import com.busterminal.model.Reservation;
import com.busterminal.model.SceneSwicth;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class SupportController implements Initializable {

    @FXML
    private AnchorPane anchorpaneSupport;
    @FXML
    private TextArea feedbackTextArea;
    @FXML
    private TextField feedbackIdTextField;
    @FXML
    private TextField passengerNameTextField;
    @FXML
    private TextField subjctTextField;
    @FXML
    private Button readOnClick;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Random rand = new Random();
    
        feedbackIdTextField.setText(Integer.toString(rand.nextInt(1000)));
    
    }    

    @FXML
    private void feedbackConfirmationOnClick(ActionEvent event) {
        
        if(passengerNameTextField.getText().isEmpty() == false &&  feedbackIdTextField.getText().isEmpty() ==false && subjctTextField.getText().isEmpty()==false && feedbackTextArea.getText().isEmpty() == false){
            
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(" Click on OK to return to Dashboard ");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Feedback shared");
        //alert.showAndWait();
        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Submit Another Feedback");
        alert.getButtonTypes().setAll(okButton, cancelButton);


        alert.showAndWait().ifPresent(buttonType -> {
    if (buttonType == okButton ) {
        try {
            new SceneSwicth (anchorpaneSupport,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        } catch (IOException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }             
    });
    
    
    
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("Feedback.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            Feedback f1 = new Feedback(passengerNameTextField.getText(),Integer.parseInt(feedbackIdTextField.getText()),subjctTextField.getText(),feedbackTextArea.getText());
            
            oos.writeObject(f1);

        } catch (IOException ex) {
            Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                
    
    
    
        }
        
        else{
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(" ");
        alert.setTitle("Error");
        alert.setHeaderText("Please give all informations");
        alert.showAndWait();
        }
    }

    @FXML
    private void switchToDashboardScene(ActionEvent event) throws IOException {
        new SceneSwicth( anchorpaneSupport,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        
        RelationshipDatabaseClass.loadFromFile();
        ArrayList<BusTripSchedule> b1 = new ArrayList();
        b1= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
        
        System.out.println(b1.toString());
        
        
        
    }

    
    @FXML
    private void readOnClick(ActionEvent event) {
        
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
