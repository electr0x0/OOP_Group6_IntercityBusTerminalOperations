/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

//import com.busterminal.model.Ticket;
import com.busterminal.model.Ticket;
import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantTicketRefundApplyViewController implements Initializable {

    @FXML
    private MFXComboBox<Ticket> comboTicketList;
    @FXML
    private TextArea txtCustomReasonRefund;
    @FXML
    private MFXComboBox<String> refundReasonList;
    @FXML
    private TextArea txtAreaTicketInformation;

    private ArrayList<Ticket> allPassengerTicketList;
    @FXML
    private MFXTextField passengerNameField;
    
    
    private Ticket selectedTicket;
    
    private boolean isCustomReason;
    @FXML
    private AnchorPane rootPane;
    
    private boolean isPolicyViewedAndAgreed;
    @FXML
    private Label txtFiledWarningPolicy;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        refundReasonList.getItems().addAll("Schedule Issue", "Custom");

        allPassengerTicketList = loadTicketsFromFile();
        
    }

    @FXML
    private void updateTicketDetails(ActionEvent event) {
        txtAreaTicketInformation.clear();
        selectedTicket = comboTicketList.getSelectionModel().getSelectedItem();
        if( selectedTicket != null){
            txtAreaTicketInformation.setDisable(false);
            txtAreaTicketInformation.appendText("ID: "+selectedTicket.getTicketId());
            txtAreaTicketInformation.appendText("\nPurchase Date: "+selectedTicket.getPurchaseDate());
            txtAreaTicketInformation.appendText("\nSource: "+selectedTicket.getDummy().getSource());
            txtAreaTicketInformation.appendText("\nDestination: "+selectedTicket.getDummy().getDestination());
            txtAreaTicketInformation.appendText("\nTime: "+selectedTicket.getDummy().getTimeSlot());
            txtAreaTicketInformation.appendText("\nQuantity: "+selectedTicket.getTicketQty());
            txtAreaTicketInformation.appendText("\nPrice: "+selectedTicket.getDummy().getAdultFare());
            txtAreaTicketInformation.appendText("\nTotal Price: "+(selectedTicket.getDummy().getAdultFare() * selectedTicket.getTicketQty()));
            txtAreaTicketInformation.appendText("\nDate of Journey: "+selectedTicket.getDummy().getScheduleDate());
            txtAreaTicketInformation.appendText("\nBooking Status: "+selectedTicket.getBookingStatus());
        }
    }

    @FXML
    private void onClickSubmitRefundReq(ActionEvent event) {
        if(selectedTicket != null){
            if(refundReasonList.getSelectedItem().isEmpty()){
                MFXDialog.showErrorDialog("No reason defined", "Please define a refund reason!", rootPane);
                return;
            }
            if(isCustomReason && txtCustomReasonRefund.getText().isEmpty()){
                MFXDialog.showErrorDialog("No reason defined", "Please input your custom reason before proceeding", rootPane);
                return;
            }
            if(!isPolicyViewedAndAgreed){
                MFXDialog.showErrorDialog("Refund policy not acknowledged", "Please acknowledge our Refund Policy!", rootPane);
                return;
            }
            
            RefundRequest refundObj = new RefundRequest(LocalDate.now(),
                                 selectedTicket.getDummy().getScheduleDate(),
                            selectedTicket.getPassenger().getName(),
                                 Integer.parseInt(selectedTicket.getTicketId()),
                                        (selectedTicket.getDummy().getAdultFare() * selectedTicket.getTicketQty()),
                                        (isCustomReason)?txtCustomReasonRefund.getText():refundReasonList.getSelectedItem());
            System.out.println(refundObj);
            RelationshipDatabaseClass.getInstance().addItemToAllRefundRequest(refundObj);
            System.out.println(RelationshipDatabaseClass.getInstance().getAllRefundRequest());
            MFXDialog.showSuccessDialog("Sucess!", "We have received your refund request! Please wait some time and check your status!", rootPane);
        }
        else{
            MFXDialog.showErrorDialog("No Ticket Selected", "Please select a Ticket that you would like to request refund for", rootPane);
        }
    }

    @FXML
    private void onClickViewCurrentRequests(ActionEvent event) {
        SceneSwitchViewAllCurrentRequests();
    }
    
    public void setRefundPolicyAgereementStatus(boolean policyViewed){
        isPolicyViewedAndAgreed = policyViewed;
        if(isPolicyViewedAndAgreed){
            txtFiledWarningPolicy.setVisible(false);
        }
    }

    @FXML
    private void updateReasonIfCustom(ActionEvent event) {
        if (refundReasonList.getSelectedItem().equals("Custom")) {
            txtCustomReasonRefund.setDisable(false);
            isCustomReason = true;
        } else {
            txtCustomReasonRefund.setDisable(true);
            txtCustomReasonRefund.clear();
            isCustomReason = false;
        }
    }

    public void setPassengerDataFromSceneSwitch(String name) {
        passengerNameField.setText(name);

        for(Ticket tObj: allPassengerTicketList){
            if(tObj.getPassenger().getName().equals(name) && tObj.getBookingStatus().equals("Confirmed")){
                comboTicketList.getItems().add(tObj);
            }
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
                        Logger.getLogger(AccountantTicketRefundApplyViewController.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(AccountantTicketRefundApplyViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tickets;
    }

    @FXML
    private void onClickViewRefundPolicy(ActionEvent event) {
        SceneSwitchViewRefundPolicy();
    }
    
    private void SceneSwitchViewRefundPolicy(){
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantTicketRefundPolicyViewer.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantTicketRefundPolicyViewerController controller = loader.getController();

            
            controller.setDataFromSceneSwitch(passengerNameField.getText());
            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }
    
    private void SceneSwitchViewAllCurrentRequests(){
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantViewCurrentRefundRequestForPassenger.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantViewCurrentRefundRequestForPassengerController controller = loader.getController();

            
            controller.setPassengerName(passengerNameField.getText());
            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }

    @FXML
    private void onClickGoBackToPassenger(MouseEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/PurchaseHistory.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);

        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

}
