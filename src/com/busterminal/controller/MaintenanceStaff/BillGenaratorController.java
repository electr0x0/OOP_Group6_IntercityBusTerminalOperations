package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.BillItems;
import java.net.URL;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("partsName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>(""));
        unitCost.setCellValueFactory(new PropertyValueFactory<>(""));
        totalCol.setCellValueFactory(new PropertyValueFactory<>(""));

    }

    @FXML
    private void createBillOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void addPartsOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void ConvertPdfOnMouseClick(ActionEvent event) {
    }

}
