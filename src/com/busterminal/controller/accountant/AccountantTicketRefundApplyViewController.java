/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

//import com.busterminal.model.Ticket;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantTicketRefundApplyViewController implements Initializable {

    @FXML
    private MFXComboBox<?> comboTicketList;
    @FXML
    private TextArea txtCustomReasonRefund;
    @FXML
    private MFXComboBox<String> refundReasonList;
    @FXML
    private TextArea txtAreaTicketInformation;
    
    //private ArrayList<Ticket> = currentPassengerticketList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refundReasonList.getItems().addAll("Schedule Issue","Custom");
    }    

    @FXML
    private void updateTicketDetails(ActionEvent event) {
    }

    @FXML
    private void onClickSubmitRefundReq(ActionEvent event) {
    }

    @FXML
    private void onClickViewCurrentRequests(ActionEvent event) {
    }

    @FXML
    private void updateReasonIfCustom(ActionEvent event) {
        if(refundReasonList.getSelectedItem().equals("Custom")){
            txtCustomReasonRefund.setDisable(false);
        }
        else{
            txtCustomReasonRefund.setDisable(true);
            txtCustomReasonRefund.clear();
        }
    }
    
//    private void setPassengerDataFromSceneSwitch(ArrayList<Ticket> tickets){
//        currentPassengerticketList = tickets;
//    }
    
}
