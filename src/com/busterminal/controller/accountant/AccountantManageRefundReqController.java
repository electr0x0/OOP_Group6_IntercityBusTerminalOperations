/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.Ticket;
import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantManageRefundReqController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<RefundRequest> refundReqViewTable;
    @FXML
    private TableColumn<RefundRequest, LocalDate> colDate;
    @FXML
    private TableColumn<RefundRequest, String> colPassengerName;
    @FXML
    private TableColumn<RefundRequest, Integer> colTicketID;
    @FXML
    private TableColumn<RefundRequest, Integer> colAmount;
    
    @FXML
    private TableColumn<RefundRequest, String> colStatus;
    
    @FXML
    private TableColumn<RefundRequest, String> colRefundReason;
    
    @FXML
    private TableColumn<RefundRequest, LocalDate> colJourneyDate;
    @FXML
    private TableColumn<RefundRequest, String> colEligible;

    
    private ArrayList<RefundRequest> allRefundRequest;
    
    private ObservableList<RefundRequest> allRefundRequestTableList = FXCollections.observableArrayList();
    
    private ArrayList<Ticket> allTicketList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        setupTable();
        if (RelationshipDatabaseClass.getInstance().getAllRefundRequest() != null){
            allRefundRequest = RelationshipDatabaseClass.getInstance().getAllRefundRequest();
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
        }
        System.out.println(allRefundRequest);
        allTicketList = loadTicketsFromFile();
//        else{
//            allRefundRequest = new ArrayList<>();
//            RefundRequest test = new RefundRequest(LocalDate.now(),"Passenger A",12345,5000,"Schedule Issue");
//            RefundRequest test1 = new RefundRequest(LocalDate.now(),"Passenger B",12345,5000,"Schedule Issue");
//            RefundRequest test2 = new RefundRequest(LocalDate.now(),"Passenger C",12345,5000,"Schedule Issue");
//            RefundRequest test3 = new RefundRequest(LocalDate.now(),"Passenger D",12345,5000,"Schedule Issue");
//            allRefundRequest.add(test);allRefundRequest.add(test1);allRefundRequest.add(test2);allRefundRequest.add(test3);
//            
//            allRefundRequestTableList.setAll(allRefundRequest);
//            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
//        }

        
    }
    
    private void setupTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPassengerName.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRefundReason.setCellValueFactory(new PropertyValueFactory<>("refundReason"));
        colJourneyDate.setCellValueFactory(new PropertyValueFactory<>("journeyDate"));
        colEligible.setCellValueFactory(new PropertyValueFactory<>("eligible"));
    }

    @FXML
    private void onClickRejectRefundRequest(ActionEvent event) {
        RefundRequest selected = refundReqViewTable.getSelectionModel().getSelectedItem();
        if( selected != null){
            for(RefundRequest refObj: allRefundRequest){
                if (refObj.equals(selected)){
                    refObj.setStatus("Rejected");
                    MFXDialog.showSuccessDialog("Sucess", "Successfully Rejected Refund Request", rootPane);
                    break;
                }
            }
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
            RelationshipDatabaseClass.getInstance().setAllRefundRequest(allRefundRequest);
        }
        else{
            MFXDialog.showErrorDialog("No Selection", "Please select an item first!", rootPane);
        }
    }
    
    

    @FXML
    private void onClickApproveRefundRequest(ActionEvent event) {
        RefundRequest selected = refundReqViewTable.getSelectionModel().getSelectedItem();
        if( selected != null){
            for(RefundRequest refObj: allRefundRequest){
                if (refObj.equals(selected)){
                    refObj.setStatus("Approved");
                    MFXDialog.showSuccessDialog("Sucess", "Successfully Approved Refund Request", rootPane);
                    break;
                }
            }
            changeTicketStatus();
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
            RelationshipDatabaseClass.getInstance().setAllRefundRequest(allRefundRequest);
        }
        else{
            MFXDialog.showErrorDialog("No Selection", "Please select an item first!", rootPane);
        }
    }
    
    private void changeTicketStatus(){
        for (Ticket ticketObj: allTicketList){
            if(ticketObj.getTicketId().equals(String.valueOf(refundReqViewTable.getSelectionModel().getSelectedItem().getTicketID()))){
                ticketObj.setBookingStatus("Cancelled");
            }
        }
        
        saveTicketsToFile();
    }

    @FXML
    private void onClickSetRefundPolicy(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantSetRefundPolicy.fxml");
    }
    
    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog.showErrorDialog("FXML not Found", "Unable to switch scene, make sure FXML is present in the specified path\n"+e.getMessage(), rootPane);
        }
    }
    
    public ArrayList<Ticket> loadTicketsFromFile() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        File f = new File("TicketList.bin");

        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    try {
                        Ticket ticket = (Ticket) ois.readObject();
                        tickets.add(ticket);
                    } catch (EOFException e) {
                        break; // End of file reached
                    } catch (ClassNotFoundException e) {
                        Logger.getLogger(AccountantManageRefundReqController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(AccountantManageRefundReqController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tickets;
    }
    
    public void saveTicketsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TicketList.bin"))) {
            for (Ticket ticketObj : allTicketList) {
                oos.writeObject(ticketObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
