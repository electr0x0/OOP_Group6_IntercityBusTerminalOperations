/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantDashboardTransactionViewController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<Transaction> tableViewTxns;
    @FXML
    private TableColumn<Transaction, LocalDate> colDate;
    @FXML
    private TableColumn<Transaction, String> colParticulars;
    @FXML
    private TableColumn<Transaction, String> colTxnType;
    @FXML
    private TableColumn<Transaction, String> colStatus;
    @FXML
    private TableColumn<Transaction, Double> colAmount;
    
    private ArrayList<Transaction> allAvailableTransactions;

    private ObservableList<Transaction> txnsInTable = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(RelationshipDatabaseClass.getInstance().getAllAvailableTransactions() != null){
            allAvailableTransactions = RelationshipDatabaseClass.getInstance().getAllAvailableTransactions();
            txnsInTable.addAll(allAvailableTransactions);
        }
        
        tableViewTxns.setItems(txnsInTable);
        
        setupTable();
    }
    
    private void setupTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("txnDate"));
        colParticulars.setCellValueFactory(new PropertyValueFactory<>("txnParticular"));
        colTxnType.setCellValueFactory(new PropertyValueFactory<>("txnType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("txnStatus"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("txnAmount"));
    }
    
    
}
