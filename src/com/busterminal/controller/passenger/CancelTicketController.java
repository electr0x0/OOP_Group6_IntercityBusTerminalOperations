/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import com.busterminal.views.passenger.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class CancelTicketController implements Initializable {

    @FXML
    private TableView<Ticket> tableViewPurchasedTickets;
    @FXML
    private TableColumn<Ticket, Integer> ticketIdCol;
    @FXML
    private TableColumn<Ticket ,Integer > quantityCol;
    @FXML
    private TableColumn<Ticket, ArrayList<String>> seatNumbersCol;
    
    private ObservableList<Ticket> ticketList1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Ticket, String> statusCol;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("ticketQty"));
        seatNumbersCol.setCellValueFactory(new PropertyValueFactory<>("seatNumber1"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("bookingStatus"));
        
                
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
                    ticketList1.add(t);
                    tableViewPurchasedTickets.setItems(ticketList1);
                    
                   
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
    private void selectAndCancelTicket(ActionEvent event) {
      
        Ticket selected = tableViewPurchasedTickets.getSelectionModel().getSelectedItem();
        selected.setBookingStatus("Cancelled");
        ObservableList<Ticket> allRows =FXCollections.observableArrayList();
        allRows.add(selected);
        
       // allRows.remove(selected);
       tableViewPurchasedTickets.setItems(null);
       tableViewPurchasedTickets.layout();
       tableViewPurchasedTickets.setItems(allRows);
       
       
        }
        
        
  

    @FXML
    private void switchToPurchaseHistory(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/PurchaseHistory.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }
    
}
