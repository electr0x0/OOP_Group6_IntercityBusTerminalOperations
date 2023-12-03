/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.ReimbursementInfo;
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
public class AccountantEmployeeViewCurrentRequestsController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<ReimbursementInfo> reimbTableView;
    @FXML
    private TableColumn<ReimbursementInfo, String> colEmployeeDesignation;
    @FXML
    private TableColumn<ReimbursementInfo, LocalDate> colDateOfSubmission;
    @FXML
    private TableColumn<ReimbursementInfo, String> colCauseForReimb;
    @FXML
    private TableColumn<ReimbursementInfo, Integer> colExpenseAmount;
    @FXML
    private TableColumn<ReimbursementInfo, String> colReimbState;
    @FXML
    private TableColumn<ReimbursementInfo, String> colRecevingMethod;
    @FXML
    private TableColumn<ReimbursementInfo, String> colFirstName;
    @FXML
    private TableColumn<ReimbursementInfo, String> colLastName;
    @FXML
    private TableColumn<ReimbursementInfo, String> colReimID;
    @FXML
    private TableColumn<ReimbursementInfo, String> colEmployeeID;
    
    private ObservableList<ReimbursementInfo> reimbTableViewList = FXCollections.observableArrayList();
    
    private ArrayList<ReimbursementInfo> allAvailableReimbList;
    
    private String currentID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        setupTable();
    }
    
    public void setEmployeeIDFromSceneSwitch(String id){
        this.currentID = id;
        
        if (RelationshipDatabaseClass.getInstance().getReimbursementList() != null) {
            allAvailableReimbList = RelationshipDatabaseClass.getInstance().getReimbursementList();
            for (ReimbursementInfo reimObj: allAvailableReimbList){
                if(reimObj.getEmpID().equals(currentID)){
                    reimbTableViewList.add(reimObj);
                }
            }
            reimbTableView.setItems(reimbTableViewList);
        }
    }
    
    public void setupTable() {
        colReimID.setCellValueFactory(new PropertyValueFactory<>("reimbursementID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmployeeDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colDateOfSubmission.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        colCauseForReimb.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        colExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        colReimbState.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRecevingMethod.setCellValueFactory(new PropertyValueFactory<>("prefPaymentMethod"));

    }


    @FXML
    private void onClickTableUpdateSelectedItem(MouseEvent event) {
    }

    @FXML
    private void onClickBackIcon(MouseEvent event) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantReimbursementApplyView.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantReimbursementApplyViewController controller = loader.getController();

            
            controller.setEmployeeIDFromSceneSwitch(currentID);
            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }
    
}
