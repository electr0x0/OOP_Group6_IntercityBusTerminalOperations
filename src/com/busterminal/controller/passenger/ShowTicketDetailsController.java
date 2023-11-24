/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import com.busterminal.model.Ticket;
import java.io.EOFException;
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
            Ticket t;
            //String str="";
            try{
                //outputTextArea.setText("");
                while(true){
                   
                    //System.out.println("Printing objects.");
                    t= (Ticket)ois.readObject();
                    
                   
                    System.out.println( "name: " +t.getPassenger().getName());
                    
                    ticketDetailsTextArea.appendText("Passenger Info: "+"\n");
                    
                    ticketDetailsTextArea.appendText("Passenger Name :  " + t.getPassenger().getName()+ "\n");
                    ticketDetailsTextArea.appendText("Contact Number :   " + t.getPassenger().getContactNum()+"\n"+"\n");
                    
                    ticketDetailsTextArea.appendText("Journey Info: "+"\n");
                    ticketDetailsTextArea.appendText("BusID :  "+ t.getDummy().getBusId()+"\n");
                    ticketDetailsTextArea.appendText("Ticket ID :  "+ t.getTicketId()+ "\n" );
                    ticketDetailsTextArea.appendText( "Seats :  " + t.getSeatNumber1() + "\n");
                    ticketDetailsTextArea.appendText( "Quantity :  " + t.getTicketQty() + "\n"+"\n");
                  
                    ticketDetailsTextArea.appendText("Source :   "+ t.getDummy().getSource()+ "\n");
                    ticketDetailsTextArea.appendText( "Destination : " + t.getDummy().getDestination() + "\n"+"\n");
                    
                    
                    ticketDetailsTextArea.appendText( "Time :   " + t.getDummy().getTimeSlot()+ "\n");
                    ticketDetailsTextArea.appendText( "Date :   "+ t.getDummy().getScheduleDate()+"\n"+"\n");
                    ticketDetailsTextArea.appendText( "Booking Date:  "+ t.getPurchaseDate());
                    
                    
                   
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
