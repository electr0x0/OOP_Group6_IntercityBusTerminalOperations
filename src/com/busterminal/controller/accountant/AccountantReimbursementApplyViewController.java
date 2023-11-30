/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantReimbursementApplyViewController implements Initializable {

    @FXML
    private MFXTextField txtFieldEmployeeID;
    @FXML
    private TextField txtFieldEmpFirstName;
    @FXML
    private TextField txtFieldEmpLastName;
    @FXML
    private TextField txtFieldEmpAddress;
    @FXML
    private TextField txtFieldEmpSalary;
    @FXML
    private TextField txtFieldEmpDesignation;
    @FXML
    private MFXComboBox<String> combBoxSelectExpense;
    @FXML
    private MFXTextField txtFieldExpenseAmount;
    @FXML
    private MFXComboBox<String> combBoxPrefPaymentMethod;
    
    private ReimbursementInfo currentReimbursementInfo;
    
    private boolean loadButtonClickStaus = false;
    @FXML
    private AnchorPane rootPane;
    
    private LocalDate currentDate = LocalDate.now();

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combBoxSelectExpense.getItems().setAll("Internet Bill",
                                                "Telephone Bill",
                                                "Bus Equipment", 
                                                "Terminal Equipment");
        
        combBoxPrefPaymentMethod.getItems().setAll("Bkash",
                                                   "Bank ACC", 
                                                   "Nagad",
                                                   "Cash", 
                                                   "Company Credit");
    }    

    @FXML
    private void onClickLoadEmployeeDetails(ActionEvent event) {
        String employeeID = txtFieldEmployeeID.getText();
        if(employeeID == null || employeeID.isEmpty()){
            showErrorDialog("No Employee Info!","Please Input a valid Employee ID before Trying to Load ");
            return;
        }
        loadButtonClickStaus = true;
    }

    @FXML
    private void onClickSubmitEmployeeReimb(ActionEvent event) {
        if (!loadButtonClickStaus){
            showErrorDialog("No Employee Info!","Please Input Employee ID and Load Info before Trying to Submit");
            return;
        }
        
        String expenseItem = combBoxSelectExpense.getSelectedItem();
        if(expenseItem == null){
            showErrorDialog("No Selection!","Please select an expense cause before proceeding"
                                + "to Submit the Reimbursement Request!");
            return;
        }
        
        String prefPaymentMethod = combBoxPrefPaymentMethod.getSelectedItem();
        if(expenseItem == prefPaymentMethod){
            showErrorDialog("No Selection!","Please select your preferred payment receving method before proceeding"
                                + "to Submit the Reimbursement Request!");
            return;
        }
        
        String reimAmount = txtFieldExpenseAmount.getText();
        if( !Validator.isInteger(reimAmount) || Integer.parseInt(reimAmount) == 0  ){
            showErrorDialog("Invalid Amount!","Please input non-zero postive integer value as Reimbursement Amount");
        }
        
        String employeeID = txtFieldEmployeeID.getText();
        
        currentReimbursementInfo = new ReimbursementInfo(employeeID,Integer.parseInt(reimAmount),expenseItem,prefPaymentMethod,currentDate);
        RelationshipDatabaseClass.getInstance().addItemToReimbursementList(currentReimbursementInfo);
        showSuccessDialog("Success!","Your Reimbursement Request has been successfully submitted!");
      
    }
    
    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert",rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success",rootPane);
        alertDialog.openMFXDialog();
    }
    
}
