package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.BillItems;
import com.busterminal.model.Parts;
import com.busterminal.model.PopUp;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class BillGenaratorController implements Initializable {

    @FXML
    private AnchorPane anchorPaneShow;
    @FXML
    private ComboBox<String> partsNameCB;
    @FXML
    private Label partsCost;
    @FXML
    private Label totalLabel;
    @FXML
    private TextField Quantity;
    @FXML
    private TableView<BillItems> cartListTable;
    @FXML
    private TextArea totalBillTF;
    @FXML
    private TableColumn<BillItems, String> partsNameCol;
    @FXML
    private TableColumn<BillItems, Integer> quantityCol;
    @FXML
    private TableColumn<BillItems, Float> unitCost;
    @FXML
    private TableColumn<BillItems, Float> totalCol;
    @FXML
    private ComboBox<String> partsModelCB;
    private ObservableList<Parts> cart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("partsName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitCost.setCellValueFactory(new PropertyValueFactory<>("partsPrice"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        BillItems.getItems().addAll(BillItems.getItems());

    }

    @FXML
    private void createBillOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void addPartsOnMouseClick(ActionEvent event) {
        String partsName;
        String partsModel;
        int unit;
        
        cart = BillItems.getItems();
        for(Parts p : cart){
            if (!partsNameCB.getItems().contains(p.getName())){
                
                partsNameCB.getItems().add(p.getName());
            }
            if (!partsModelCB.getItems().contains(p.getModel())){
                partsModelCB.getItems().add(p.getModel());              
            }
            
        }
         
        
    }

    @FXML
    private void ConvertPdfOnMouseClick(ActionEvent event) {
    }

}
