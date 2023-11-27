package com.busterminal.controller.MaintenanceStaff;
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
    private TableView<?> cartListTable;
    @FXML
    private TextArea totalBillTF;
    @FXML
    private TableColumn<?, ?> partsNameCol;
    @FXML
    private TableColumn<?, ?> quantityCol;
    @FXML
    private TableColumn<?, ?> unitCost;
    @FXML
    private TableColumn<?, ?> totalCol;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
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
