/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantViewCurrentRefundRequestForPassengerController implements Initializable {

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

    private ArrayList<RefundRequest> allRefundRequest;

    private ObservableList<RefundRequest> RefundRequestTableListSpecificPassenger = FXCollections.observableArrayList();

    private String passengerName;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        if (RelationshipDatabaseClass.getInstance().getAllRefundRequest() != null) {
            allRefundRequest = RelationshipDatabaseClass.getInstance().getAllRefundRequest();
        }

    }

    public void setPassengerName(String name) {
        this.passengerName = name;
        filterPassengerAndSetTable();
    }

    private void filterPassengerAndSetTable() {
        setupTable();
        if (allRefundRequest != null) {
            for (RefundRequest refreqObj : allRefundRequest) {
                if (refreqObj.getPassengerName().equals(passengerName)) {
                    RefundRequestTableListSpecificPassenger.add(refreqObj);
                }
            }
            refundReqViewTable.setItems(RefundRequestTableListSpecificPassenger);
        }
    }

    private void setupTable() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colPassengerName.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRefundReason.setCellValueFactory(new PropertyValueFactory<>("refundReason"));
        colJourneyDate.setCellValueFactory(new PropertyValueFactory<>("journeyDate"));
    }

    @FXML
    private void goBackToRefundApplyView(MouseEvent event) {
        SceneSwtichBackToRefundApply();
    }

    private void SceneSwtichBackToRefundApply() {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantTicketRefundApplyView.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantTicketRefundApplyViewController controller = loader.getController();

            controller.setPassengerDataFromSceneSwitch(passengerName);
            rootPane.getChildren().setAll(newContent);

        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }

}
