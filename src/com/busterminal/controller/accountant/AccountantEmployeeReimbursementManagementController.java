/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantEmployeeReimbursementManagementController implements Initializable {

    @FXML
    private TableColumn<?, ?> colEmployeeName;
    @FXML
    private TableColumn<?, ?> colEmployeeDesignation;
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
    private TextField txtFieldSearch;

    @FXML
    private MFXLegacyTableView<ReimbursementInfo> reimbTableView;

    private ObservableList<ReimbursementInfo> reimbTableViewList = FXCollections.observableArrayList();

    private ObservableList<ReimbursementInfo> reimbTableViewFilteredList = FXCollections.observableArrayList();

    private ArrayList<ReimbursementInfo> allAvailableReimbList;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXRadioButton radioSearchName;
    @FXML
    private MFXRadioButton radioSearchDesignation;
    @FXML
    private MFXRadioButton radioSearchDate;
    @FXML
    private MFXButton markPaidButton;

    private ToggleGroup searchToggleGroup;
    
    private static final String TXN_TYPE = "REIMB";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();

        if (RelationshipDatabaseClass.getInstance().getReimbursementList() != null) {
            allAvailableReimbList = RelationshipDatabaseClass.getInstance().getReimbursementList();
            reimbTableViewList.setAll(allAvailableReimbList);
            reimbTableView.setItems(reimbTableViewList);
        } else {
            showErrorDialog("Info", "There are currently zero Reimbursement Request");
        }

        searchToggleGroup = new ToggleGroup();
        radioSearchDate.setToggleGroup(searchToggleGroup);
        radioSearchDesignation.setToggleGroup(searchToggleGroup);
        radioSearchName.setToggleGroup(searchToggleGroup);
    }

    public void setupTable() {
        //colEmployeeName.setCellValueFactory(new PropertyValueFactory<>(""));
        //colEmployeeDesignation.setCellValueFactory(new PropertyValueFactory<>(""));
        colDateOfSubmission.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        colCauseForReimb.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        colExpenseAmount.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        colReimbState.setCellValueFactory(new PropertyValueFactory<>("status"));
        colRecevingMethod.setCellValueFactory(new PropertyValueFactory<>("prefPaymentMethod"));

    }

    @FXML
    private void onClickExpenseReportReim(ActionEvent event) {
    }

    @FXML
    private void onClickReimDashboard(ActionEvent event) {
    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onClickTableUpdateSelectedItem(MouseEvent event) {
        markPaidButton.setDisable(false);
    }

    @FXML
    private void onClickDeleteSelectedItem(ActionEvent event) {
        ReimbursementInfo selected = reimbTableView.getSelectionModel().getSelectedItem();
        if( selected != null){
            if(selected.getStatus().equals("Paid")){
                MFXDialog.showErrorDialog("Already Paid!", "Selected Entry is already marked as paid!", rootPane);
                return;
            }
            int txnAmount = 0;
            String txnParticular = "";
            for(ReimbursementInfo reimObj: allAvailableReimbList){
                if (reimObj.equals(selected)){
                    reimObj.setStatus("Paid");
                    txnAmount = reimObj.getExpenseAmount();
                    txnParticular = reimObj.getExpenseType();
                    MFXDialog.showSuccessDialog("Sucess", "Successfully Marked the Request As Paid", rootPane);
                    break;
                }
            }
            
            reimbTableViewList.clear();
            reimbTableViewList.setAll(allAvailableReimbList);

            reimbTableView.setItems(reimbTableViewList);
            
            RelationshipDatabaseClass.getInstance().setReimbursementList(allAvailableReimbList);
            
            Transaction reimTxn = new Transaction(LocalDate.now(), TXN_TYPE, txnAmount, "Paid", txnParticular);
            
            RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(reimTxn);
            
        }
        else{
            MFXDialog.showErrorDialog("No Selection", "Please select an item first!", rootPane);
        }
    }

    @FXML
    private void searchActionTableView(KeyEvent event) {

        if (searchToggleGroup.getSelectedToggle() == null) {
            showErrorDialog("No Filter Group Selected", "Please select a filter group before searching");
            return;
        }
        if (!txtFieldSearch.getText().isEmpty()){
            if (searchToggleGroup.getSelectedToggle().equals(radioSearchDate)) {
                reimbTableViewFilteredList.clear();
                for (ReimbursementInfo reimbObj : allAvailableReimbList) {
                    if(reimbObj.getPrefPaymentMethod().contains(txtFieldSearch.getText())){
                        
                        reimbTableViewFilteredList.add(reimbObj);
                        reimbTableView.setItems(reimbTableViewFilteredList);
                    }
                }
            }
        }
        else{
            reimbTableView.setItems(reimbTableViewList);
        }
    }

}
