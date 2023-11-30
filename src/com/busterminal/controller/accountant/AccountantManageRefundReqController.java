/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    
    private ArrayList<RefundRequest> allRefundRequest;
    
    private ObservableList<RefundRequest> allRefundRequestTableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        if (RelationshipDatabaseClass.getInstance().getAllRefundRequest() != null){
            allRefundRequest = RelationshipDatabaseClass.getInstance().getAllRefundRequest();
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
        } else{
            allRefundRequest = new ArrayList<>();
            RefundRequest test = new RefundRequest(LocalDate.now(),"Passenger A",12345,5000,"Schedule Issue");
            RefundRequest test1 = new RefundRequest(LocalDate.now(),"Passenger B",12345,5000,"Schedule Issue");
            RefundRequest test2 = new RefundRequest(LocalDate.now(),"Passenger C",12345,5000,"Schedule Issue");
            RefundRequest test3 = new RefundRequest(LocalDate.now(),"Passenger D",12345,5000,"Schedule Issue");
            allRefundRequest.add(test);allRefundRequest.add(test1);allRefundRequest.add(test2);allRefundRequest.add(test3);
            
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
        }

        
    }
    
    private void setupTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPassengerName.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRefundReason.setCellValueFactory(new PropertyValueFactory<>("refundReason"));
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
            allRefundRequestTableList.setAll(allRefundRequest);
            refundReqViewTable.getItems().setAll(allRefundRequestTableList);
            RelationshipDatabaseClass.getInstance().setAllRefundRequest(allRefundRequest);
        }
        else{
            MFXDialog.showErrorDialog("No Selection", "Please select an item first!", rootPane);
        }
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
    
}
