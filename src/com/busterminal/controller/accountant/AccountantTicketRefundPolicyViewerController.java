/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantTicketRefundPolicyViewerController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXCheckbox acknowledgementCheckBox;
    @FXML
    private TextArea txtAreaRefundPolicy;
    
    
    private String oldScenePassengerName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        if(RelationshipDatabaseClass.getInstance().getRefundPolicy() != null){
            txtAreaRefundPolicy.setText(RelationshipDatabaseClass.getInstance().getRefundPolicy());
        }
    }

    
    public void setDataFromSceneSwitch(String passengerName){
        oldScenePassengerName = passengerName;
    }

    @FXML
    private void onClickGoBack(ActionEvent event) {
        SceneSwtichBackToRefundApply();
    }
    
    private void SceneSwtichBackToRefundApply(){
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantTicketRefundApplyView.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantTicketRefundApplyViewController controller = loader.getController();

            
            controller.setPassengerDataFromSceneSwitch(oldScenePassengerName);
            controller.setRefundPolicyAgereementStatus(acknowledgementCheckBox.isSelected());
            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }
    
}
