/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.InvoiceService;
import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

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
    
    private ObservableList<Transaction> txnsInTableFiltered = FXCollections.observableArrayList();
    
    LocalDateTime now;
    
    private boolean isUpdated = false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

    String formattedDateTime;
    @FXML
    private Label totalLabel;
    @FXML
    private MFXTextField textFieldSearch;
    @FXML
    private MFXComboBox<String> filterByComboBox;
    
    private int total;
    
    private int filteredTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        if(RelationshipDatabaseClass.getInstance().getAllAvailableTransactions() != null){
            allAvailableTransactions = RelationshipDatabaseClass.getInstance().getAllAvailableTransactions();
            txnsInTable.addAll(allAvailableTransactions);
            setupTotalLabel();
        }
        filterByComboBox.getItems().addAll("Date","Type","Status","Amount");
        tableViewTxns.setItems(txnsInTable);
        
        setupTable();
    }
    
    private void setupTotalLabel(){
        for(Transaction txn: allAvailableTransactions){
            total += txn.getTxnAmount();
        }
        totalLabel.setText(String.valueOf(total));
    }
    
    private void setupTable(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("txnDate"));
        colParticulars.setCellValueFactory(new PropertyValueFactory<>("txnParticular"));
        colTxnType.setCellValueFactory(new PropertyValueFactory<>("txnType"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("txnStatus"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("txnAmount"));
    }

    @FXML
    private void onClickExportPDF(ActionEvent event) {
        genPdf();
    }
    
    private void genPdf(){
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

                //Line separtor
                LineSeparator separator = new LineSeparator(new SolidLine(.3f));
                separator.setWidth(UnitValue.createPercentValue(100)); 
                separator.setMarginTop(10);
                separator.setMarginBottom(10);

 
                Paragraph companyName = new Paragraph("XYZ BUS TERMINAL Transactions")
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER); // Center the text
                document.add(companyName);
                document.add(separator);

  

                // Company Details
                
                Table fromDetailsTable = new Table(4);
                fromDetailsTable.addCell(new Cell().add("Business Name:").setBold());
                fromDetailsTable.addCell(new Cell().add(CompanyEnvironmentVariables.COMPANY.NAME));
                fromDetailsTable.addCell(new Cell().add("Address:").setBold());
                fromDetailsTable.addCell(new Cell().add(CompanyEnvironmentVariables.COMPANY.ADDRESS));
                fromDetailsTable.addCell(new Cell().add("Phone:").setBold());
                fromDetailsTable.addCell(new Cell().add(CompanyEnvironmentVariables.COMPANY.PHONENUMBER));
                fromDetailsTable.addCell(new Cell().add("Email:").setBold());
                fromDetailsTable.addCell(new Cell().add(CompanyEnvironmentVariables.COMPANY.EMAIL));

                document.add(fromDetailsTable);
                document.add(separator);


                // Transaction Item Table
                document.add(new Paragraph("Transactions").setBold());
                Table txnTable = new Table(new float[]{80, 230, 150, 50, 50});
                txnTable.addCell("Date");
                txnTable.addCell("Particular(s)");
                txnTable.addCell("Transaction Type");
                txnTable.addCell("Status");
                txnTable.addCell("Amount");

                for (Transaction txnObj : allAvailableTransactions) {
                    txnTable.addCell(txnObj.getTxnDate().toString());
                    txnTable.addCell(txnObj.getTxnParticular());
                    txnTable.addCell(txnObj.getTxnType());
                    txnTable.addCell(txnObj.getTxnStatus());
                    txnTable.addCell(String.valueOf(txnObj.getTxnAmount()));
                }

                document.add(txnTable);

                document.add(new Paragraph("CONFIDENTIALITY NOTICE: This message and any accompanying documents contain information belonging to XYZ Bus Company which may be confidential and legally privileged. This information is only for the use of the individual or entity to which it was intended.").setBold());
                document.add(separator);
                
                
                now = LocalDateTime.now();
                formattedDateTime = now.format(formatter);
                
                Paragraph generatorDetails = new Paragraph("Generated by Accountant on "+formattedDateTime)
                        .setItalic()
                        .setTextAlignment(TextAlignment.CENTER); // Center the text
                document.add(generatorDetails);

                
                // Close the document
                document.close();

                MFXDialog.showSuccessDialog("Success", "Invoice exported successfully.",rootPane);
            } else {
                MFXDialog.showErrorDialog("Export Cancelled", "Invoice export was cancelled.",rootPane);
            }
        } catch (Exception e) {
            MFXDialog.showErrorDialog("Export Error", "An error occurred while exporting the invoice: " + e.toString(),rootPane);
        } 
    }

    @FXML
    private void updateTableOnSearch(KeyEvent event) {
        String searchCriteria = textFieldSearch.getText().toLowerCase();

        if (filterByComboBox.getSelectedItem() == null) {
            textFieldSearch.clear();
            MFXDialog.showErrorDialog("No Fitler Category!", "Please select a filter category before trying to perform search!", rootPane);
            return;
        }

        if (!searchCriteria.isEmpty()) {
            txnsInTableFiltered.clear();
            String selectedFilterGroup = filterByComboBox.getSelectedItem();

            switch (selectedFilterGroup) {

                case ("Date"):
                    for (Transaction txn : allAvailableTransactions) {
                        if (txn.getTxnDate().toString().contains(searchCriteria)) {
                            txnsInTableFiltered.add(txn);
                        }
                    }
                    tableViewTxns.setItems(txnsInTableFiltered);
                    updateTotal();
                    break;
                case ("Type"):
                    for (Transaction txn : allAvailableTransactions) {
                        if (txn.getTxnType().toLowerCase().contains(searchCriteria)) {
                            txnsInTableFiltered.add(txn);
                        }
                    }
                    tableViewTxns.setItems(txnsInTableFiltered);
                    updateTotal();
                    break;
                case ("Status"):
                    for (Transaction txn : allAvailableTransactions) {
                        if (txn.getTxnStatus().toLowerCase().startsWith(searchCriteria)) {
                            txnsInTableFiltered.add(txn);
                        }
                    }
                    tableViewTxns.setItems(txnsInTableFiltered);
                    updateTotal();
                    break;
                case ("Amount"):
                    for (Transaction txn : allAvailableTransactions) {
                        if (String.valueOf(txn.getTxnAmount()).contains(searchCriteria)) {
                            txnsInTableFiltered.add(txn);
                        }
                    }
                    tableViewTxns.setItems(txnsInTableFiltered);
                    updateTotal();
                    break;
            }
        } else {
            tableViewTxns.setItems(txnsInTable);
            totalLabel.setText(String.valueOf(total));
        }
    }
    
    private void updateTotal(){
        filteredTotal = 0;
        
        for (Transaction txn: txnsInTableFiltered){
            filteredTotal += txn.getTxnAmount();
        }
        
        totalLabel.setText(String.valueOf(filteredTotal));
    }
    
    
}
