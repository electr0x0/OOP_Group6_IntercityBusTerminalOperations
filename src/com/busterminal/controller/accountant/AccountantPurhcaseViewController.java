/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.PurchaseEntry;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
public class AccountantPurhcaseViewController implements Initializable {

    @FXML
    private MFXDatePicker itemPurchaseDate;
    @FXML
    private MFXTextField txtFieldSellerName;
    @FXML
    private MFXTextField txtFieldPurchaseRate;
    @FXML
    private MFXTextField txtFieldTotalAmount;
    @FXML
    private MFXComboBox<String> comboBoxPurchaseItems;
    @FXML
    private MFXSlider spinnerItemQty;
    @FXML
    private MFXLegacyTableView<PurchaseEntry> purchaseItemTableView;
    @FXML
    private TableColumn<PurchaseEntry, LocalDate> colDate;
    @FXML
    private TableColumn<PurchaseEntry, String> colSeller;
    @FXML
    private TableColumn<PurchaseEntry, String> colItem;
    @FXML
    private TableColumn<PurchaseEntry, Double> colQty;
    @FXML
    private TableColumn<PurchaseEntry, Double> colRate;
    @FXML
    private TableColumn<PurchaseEntry, Double> colAmount;
    @FXML
    private TableColumn<PurchaseEntry, String> colStatus;
    
    private ArrayList<PurchaseEntry> currentInventory;
    
    private ObservableList<PurchaseEntry> currentInventoryTableList = FXCollections.observableArrayList();
    @FXML
    private MFXRadioButton radioPaid;
    @FXML
    private MFXRadioButton radioUnpaid;
    
    private ToggleGroup purchasePaymentStaus;
    @FXML
    private AnchorPane rootPane;
    
    private PurchaseEntry currentPurchaseEnry;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable();
        if (RelationshipDatabaseClass.getInstance().getCurrentInventory() != null){
            currentInventory = RelationshipDatabaseClass.getInstance().getCurrentInventory();
            System.out.println("Loaded"+currentInventory);
            currentInventoryTableList.setAll(currentInventory);
            purchaseItemTableView.setItems(currentInventoryTableList);
        } else{
            currentInventory = new ArrayList<>();
        }
        comboBoxPurchaseItems.getItems().addAll("Bus Parts","Maintenance Supplies","Fuel and Energy","Terminal Infrastructure","Miscellaneous");
        
        purchasePaymentStaus = new ToggleGroup();
        radioPaid.setToggleGroup(purchasePaymentStaus);
        radioUnpaid.setToggleGroup(purchasePaymentStaus);
    }
    
    private void setupTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        colSeller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    private boolean fieldValidate(){
        if(itemPurchaseDate.getValue() == null){
            MFXDialog.showErrorDialog("No date selected!", "Please specify Date of Purchase", rootPane);
            return false;
        }
        if(txtFieldSellerName.getText().isEmpty()){
            MFXDialog.showErrorDialog("No seller name", "Please specify Seller Informaiton", rootPane);
            return false;
        }
        if(txtFieldPurchaseRate.getText().isEmpty() || !Validator.isInteger(txtFieldPurchaseRate.getText())){
            MFXDialog.showErrorDialog("Invalid Item Rate", "Please input a valid item rate", rootPane);
            return false;
        }
        if(spinnerItemQty.getValue() == 0){
            MFXDialog.showErrorDialog("Invalid Item Qty", "Please provide item qty >0", rootPane);
            return false;
        }
        if(comboBoxPurchaseItems.getSelectedItem() == null){
            MFXDialog.showErrorDialog("No item selected", "Please select an item!", rootPane);
            return false;
        }
        if(purchasePaymentStaus.getSelectedToggle() == null){
            MFXDialog.showErrorDialog("No Payment Status", "Please specify Payment Status", rootPane);
            return false;
        }
        
        return true;
        
    }

    @FXML
    private void onClickCreatePurchaseEntry(ActionEvent event) {
        if(fieldValidate()){
            String paymentStatus = "";
            if(purchasePaymentStaus.getSelectedToggle().equals(radioPaid)){paymentStatus = "Paid";}
            else{paymentStatus = "Unpaid";}
            currentPurchaseEnry = new PurchaseEntry(itemPurchaseDate.getValue(), txtFieldSellerName.getText(),
                                                    comboBoxPurchaseItems.getSelectedItem(), spinnerItemQty.getValue(), 
                                                    Integer.parseInt(txtFieldPurchaseRate.getText()),paymentStatus);
            currentInventory.add(currentPurchaseEnry);
            RelationshipDatabaseClass.getInstance().setCurrentInventory(currentInventory);
            currentInventoryTableList.setAll(currentInventory);
            purchaseItemTableView.setItems(currentInventoryTableList);
        }
    }

    @FXML
    private void onRateChangeCalUpdateTotalAmount(KeyEvent event) {
        
    }

    @FXML
    private void onQtyChangeCalUpdateTotalAmount(MouseEvent event) {
        if(txtFieldPurchaseRate.getText().isEmpty() || !Validator.isInteger(txtFieldPurchaseRate.getText())){
            MFXDialog.showErrorDialog("Invalid Item Rate", "Please input a valid item rate before setting quantity", rootPane);
            return;
        }
        double totalAmount = spinnerItemQty.getValue() * Integer.parseInt(txtFieldPurchaseRate.getText());
        txtFieldTotalAmount.setText(String.valueOf(totalAmount));
    }
    
}
