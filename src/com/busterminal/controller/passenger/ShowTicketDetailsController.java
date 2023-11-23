/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import com.busterminal.model.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ShowTicketDetailsController implements Initializable {

    @FXML
    private AnchorPane anchorpaneShowTicketDetails;
    @FXML
    private TextArea ticketDetailsTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("TicketList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Ticket f2;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    f2= (Ticket)ois.readObject();
                    ticketDetailsTextArea.appendText(f2.toString());
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    //System.out.println(f2.toString());
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
    
        
        
        
     

    @FXML
    private void swicthToPurchaseHistoryScene(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpaneShowTicketDetails,"/com/busterminal/views/passenger/PurchaseHistory.fxml");
        
        
        
    }
    
}
