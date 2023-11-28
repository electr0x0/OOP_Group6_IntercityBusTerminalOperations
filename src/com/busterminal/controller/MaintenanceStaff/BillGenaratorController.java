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
    private ComboBox<String> Quantity;
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
    @FXML
    private TextField busId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Quantity.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("partsName"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitCost.setCellValueFactory(new PropertyValueFactory<>("partsPrice"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        BillItems.getItems().addAll(BillItems.getItems());
        // set items in combobox from the Parts file
        cart = BillItems.getItems();
        for (Parts p : cart) {
            if (!partsNameCB.getItems().contains(p.getName())) {

                partsNameCB.getItems().add(p.getName());

            }
            if (!partsModelCB.getItems().contains(p.getModel())) {
                partsModelCB.getItems().add(p.getModel());
            }

        }
        //totalLabel.setText(Integer.toString(Integer.parseInt(unitCost.getText())*Integer.parseInt(Quantity.getText())));

    }

    @FXML
    private void createBillOnMouseClick(ActionEvent event) {

        
        
    }

    @FXML
    private void addPartsOnMouseClick(ActionEvent event) {
        try {
            int busIdValue = Integer.parseInt(busId.getText());
            String partsName = partsNameCB.getValue();
            String partsModel = partsModelCB.getValue();
            float partsCostValue = Float.parseFloat(partsCost.getText());
            float quantityValue = Float.parseFloat(Quantity.getValue());
            float totalBillValue = Float.parseFloat(totalBillTF.getText());

            // Check for empty strings before parsing
            if (partsName.isEmpty() || partsModel.isEmpty()) {
                PopUp.showMessage("Warning","Parts Name or Parts Model cannot be empty.");
                return;
            }

            // Create a new BillItems object
            BillItems data = new BillItems(busIdValue, partsName, partsModel, partsCostValue, quantityValue, totalBillValue);

            // Add the new object to the table
            cartListTable.getItems().add(data);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error parsing numeric values. Check if all fields are filled correctly.");
        }
    }

    @FXML
    private void ConvertPdfOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void selectPartsMouseClick(ActionEvent event) {

        if (!partsNameCB.getValue().isEmpty() && !partsModelCB.getValue().isEmpty()) {
            boolean partFound = false;

            for (Parts p : cart) {
                System.out.println(p.toString());
                if (p.getName().equals(partsNameCB.getValue()) && p.getModel().equals(partsModelCB.getValue())) {
                    partsCost.setText(Integer.toString(p.getPrice()));
                    partFound = true;
                    break;
                }
            }
            if (!partFound) {
                PopUp.showMessage("Alert", "Selected parts are not available in the cart.");
                partsCost.setText("");
                partsModelCB.setValue(null);
                partsNameCB.setValue(null);

            }
        } else {
            PopUp.showMessage("Alert", "Please select necessary parts..!");
        }
    }

    @FXML
    private void qunatityOnMouseClick(ActionEvent event) {
        if (!Quantity.getValue().equals("") && !partsCost.getText().equals("")) {
            try {
                float total = Float.parseFloat(partsCost.getText()) * (float) Float.parseFloat(Quantity.getValue());
                totalLabel.setText(Float.toString(total));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
