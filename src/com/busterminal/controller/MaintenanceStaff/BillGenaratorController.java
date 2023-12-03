package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.BillItems;
import com.busterminal.model.Parts;
import com.busterminal.model.PopUp;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.stage.FileChooser;

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
    private TableColumn<BillItems, Integer> unitCost;
    @FXML
    private TableColumn<BillItems, Float> totalCol;
    @FXML
    private ComboBox<String> partsModelCB;
    private ObservableList<Parts> cart;
    @FXML
    private TextField busId;
    @FXML
    private TextArea memo;

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

    }

    @FXML
    private void createBillOnMouseClick(ActionEvent event) {
        memo.clear();
        StringBuilder billDetails = new StringBuilder();

        billDetails.append("Bill ID: ").append(busId.getText()).append("\n\n");
        billDetails.append(String.format("%-20s %-15s %-10s %-10s %-10s\n", "Parts Name", "Model", "Unit Cost", "Quantity", "Total"));
        billDetails.append("---------------------------------------------------------\n");

        for (BillItems item : cartListTable.getItems()) {
            String line = String.format("%-20s %-15s %-10.2f %-10.2f %-10.2f\n",
                    item.getPartsName(),
                    item.getPartsModel(),
                    item.getPartsPrice(),
                    item.getQuantity(),
                    item.getTotal());
            billDetails.append(line);
        }

        float totalBill = Float.parseFloat(totalBillTF.getText());
        billDetails.append("\nTotal Bill: ").append(totalBill);

        memo.setText(billDetails.toString());

        // send to manager
        Transaction t = new Transaction(LocalDate.now(), "Maintenance Bill", Double.parseDouble(totalBillTF.getText()), "Unpaid", billDetails.toString());
        RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(t);

    }

    @FXML
    private void addPartsOnMouseClick(ActionEvent event) {
        try {
            int busIdValue = Integer.parseInt(busId.getText());
            String partsName = partsNameCB.getValue();
            String partsModel = partsModelCB.getValue();
            float partsCostValue = Float.parseFloat(partsCost.getText());
            float quantityValue = Float.parseFloat(Quantity.getValue());
            float totalBillValue = Float.parseFloat(totalLabel.getText());

            if (partsName.isEmpty() || partsModel.isEmpty()) {
                PopUp.showMessage("Warning", "Parts Name or Parts Model cannot be empty.");
                return;
            }

            // Check if the part is already in the table
            boolean partExists = false;
            for (BillItems item : cartListTable.getItems()) {
                if (item.getPartsName().equals(partsName) && item.getPartsModel().equals(partsModel)) {
                    item.setQuantity(item.getQuantity() + quantityValue);
                    item.setTotal(item.getTotal() + totalBillValue);
                    partExists = true;
                    cartListTable.refresh();
                    break;

                }
            }

            if (!partExists) {
                BillItems data = new BillItems(busIdValue, partsName, partsModel, (int) partsCostValue, quantityValue, totalBillValue);
                cartListTable.getItems().add(data);
                cartListTable.refresh();
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
        totalBillTF.setText(Integer.toString(calculateTotalAmount()));
        clear();
    }

    @FXML
    private void ConvertPdfOnMouseClick(ActionEvent event) {
        if (!memo.getText().isEmpty()) {
            try {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
                File f = fc.showSaveDialog(null);
                if (f != null) {
                    PdfWriter pw = new PdfWriter(new FileOutputStream(f));
                    PdfDocument pdf = new PdfDocument(pw);
                    pdf.addNewPage();

                    Document document = new Document(pdf);
                    document.setMargins(20, 20, 20, 20);

                    document.add(new Paragraph(memo.getText()).setBold().setTextAlignment(TextAlignment.CENTER));

                    // Close the document
                    document.close();

                    PopUp.showMessage("Success", "Bill Generated");
                } else {
                    PopUp.showMessage("Export Cancelled", "Bill export was cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            PopUp.showMessage("Empty Memo", "Please generate bill slip before creating pdf!");
        }

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

    private int calculateTotalAmount() {
        int totalAmount = 0;
        // Add All data after filtered and get the total 
        ObservableList<BillItems> filteredData = cartListTable.getItems();

        // Now traverse throgh the filterData list take salary and increase the total Amount local field
        for (BillItems b : filteredData) {
            totalAmount += b.getTotal();
        }
        return totalAmount;
    }

    private void clear() {
        partsNameCB.setValue(null);
        partsModelCB.setValue(null);
        Quantity.setValue(null);

    }

    @FXML
    private void DeletePartsOnMouseClick(ActionEvent event) {
        if (cartListTable.getSelectionModel().getSelectedItems().isEmpty()) {
            PopUp.showMessage("No value selected", "Please select row for delete.");
        } else {
            ObservableList<BillItems> selectedRows, addAllProduct;
            addAllProduct = cartListTable.getItems();
            selectedRows = cartListTable.getSelectionModel().getSelectedItems();
            System.out.println(selectedRows);
            addAllProduct.removeAll(selectedRows);
            PopUp.showConfirmationMessage();

        }
    }

}
