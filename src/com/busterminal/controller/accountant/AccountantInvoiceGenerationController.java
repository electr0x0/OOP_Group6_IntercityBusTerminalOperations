/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.Invoice;
import com.busterminal.model.accountant.InvoiceService;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import javafx.scene.image.WritableImage;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import java.time.LocalDate;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantInvoiceGenerationController implements Initializable {

    @FXML
    private Label labelnvoiceID;
    @FXML
    private ImageView imageViewCompanyLogo;
    @FXML
    private MFXDatePicker datePickerInvDueDate;
    @FXML
    private MFXTextField txtFieldBillToClientName;
    @FXML
    private MFXTextField txtFieldBillToPhoneNumber;
    @FXML
    private MFXTextField txtFieldBillToAddress;
    @FXML
    private MFXLegacyTableView<InvoiceService> serviceListTableView;
    @FXML
    private MFXTextField txtFieldFromCompanyName;
    @FXML
    private MFXTextField txtFieldFromCompanyEmail;
    @FXML
    private MFXTextField txtFieldFromCompanyPhone;
    @FXML
    private MFXTextField txtFieldFromCompanyAddress;
    @FXML
    private MFXTextField txtFieldSubTotal;
    @FXML
    private MFXTextField txtFieldTotalTax;
    @FXML
    private MFXTextField txtFieldInvNote;

    private int currentInvoiceCounter;
    private String currentInvoiceID;

    private InvoiceService currentService;
    private Invoice currentInvoice;

    private ArrayList<InvoiceService> currentInvoiceServiceList = new ArrayList<>();

    private ObservableList<InvoiceService> serviceListForTable = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<InvoiceService, String> colServiceName;
    @FXML
    private TableColumn<InvoiceService, String> colServiceDescription;
    @FXML
    private TableColumn<InvoiceService, Integer> colServiceRate;
    @FXML
    private TableColumn<InvoiceService, Double> colServiceQty;
    @FXML
    private TableColumn<InvoiceService, Double> colServiceAmount;
    @FXML
    private TableColumn<InvoiceService, Double> colServiceTax;
    @FXML
    private MFXRadioButton radioInvPaid;
    @FXML
    private MFXRadioButton radioInvUnPaid;
    @FXML
    private MFXTextField txtFieldTotalAmount;

    private ToggleGroup paymentStatus;
    
    private Transaction currentTransaction;
    
    private String invoiceItems = "";
    
    private MFXStageDialog dialog;
    private MFXGenericDialog dialogContent;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        txtFieldFromCompanyName.setText(CompanyEnvironmentVariables.COMPANY.NAME);
        txtFieldFromCompanyEmail.setText(CompanyEnvironmentVariables.COMPANY.EMAIL);
        txtFieldFromCompanyAddress.setText(CompanyEnvironmentVariables.COMPANY.ADDRESS);
        txtFieldFromCompanyPhone.setText(CompanyEnvironmentVariables.COMPANY.PHONENUMBER);
        if (RelationshipDatabaseClass.getInstance().getCurrentInvoiceCounter() != 0) {
            currentInvoiceCounter = RelationshipDatabaseClass.getInstance().getCurrentInvoiceCounter();
        }

        paymentStatus = new ToggleGroup();
        radioInvPaid.setToggleGroup(paymentStatus);
        radioInvUnPaid.setToggleGroup(paymentStatus);
        setupServiceItemTable();
    }

    public void setupServiceItemTable() {
        colServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colServiceDescription.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
        colServiceRate.setCellValueFactory(new PropertyValueFactory<>("serviceRate"));
        colServiceQty.setCellValueFactory(new PropertyValueFactory<>("serviceQty"));
        colServiceAmount.setCellValueFactory(new PropertyValueFactory<>("itemTotal"));
        colServiceTax.setCellValueFactory(new PropertyValueFactory<>("serviceTaxAmount"));
    }

    @FXML
    private void onClickGenerateInvoiceID(ActionEvent event) {
        String format = "INV-BUS-";
        currentInvoiceID = format + currentInvoiceCounter;

        labelnvoiceID.setText(currentInvoiceID);

        currentInvoiceCounter++;
        RelationshipDatabaseClass.getInstance().setCurrentInvoiceCounter(currentInvoiceCounter);

    }

    public void setOldValuesBeforeSceneSwitching(MFXDatePicker datePickerInvDueDate, MFXTextField txtFieldBillToClientName, MFXTextField txtFieldBillToPhoneNumber, MFXTextField txtFieldFromCompanyName, MFXTextField txtFieldFromCompanyEmail, MFXTextField txtFieldFromCompanyPhone, MFXTextField txtFieldFromCompanyAddress, MFXTextField txtFieldInvNote, MFXTextField txtFieldBillToAddress) {
        this.datePickerInvDueDate.setValue(datePickerInvDueDate.getValue());
        this.txtFieldBillToClientName.setText(txtFieldBillToClientName.getText());
        this.txtFieldBillToPhoneNumber.setText(txtFieldBillToPhoneNumber.getText());
        this.txtFieldFromCompanyName.setText(txtFieldFromCompanyName.getText());
        this.txtFieldFromCompanyEmail.setText(txtFieldFromCompanyEmail.getText());
        this.txtFieldFromCompanyPhone.setText(txtFieldFromCompanyPhone.getText());
        this.txtFieldFromCompanyAddress.setText(txtFieldFromCompanyAddress.getText());
        this.txtFieldInvNote.setText(txtFieldInvNote.getText());
        this.txtFieldBillToAddress.setText(txtFieldBillToAddress.getText());
        calcualteSubTotalAndTax();
    }

    public void setAddedServiceFromSceneSwitch(ArrayList<InvoiceService> currentInvoiceServiceList) {
        this.currentInvoiceServiceList = currentInvoiceServiceList;
        tableListUpdate();
        serviceListTableView.setItems(serviceListForTable);
    }

    public void tableListUpdate() {
        serviceListForTable.clear();
        serviceListForTable.setAll(currentInvoiceServiceList);
    }

    @FXML
    private void onClickAddItemSceneSwitch(ActionEvent event) {
        ModificationSceneSwitch("/com/busterminal/views/accountantUser/AccountantInvoiceGenerationAddItem.fxml");
    }

    private Image getCompanyLogoAsImage() throws Exception {
        WritableImage writableImage = imageViewCompanyLogo.snapshot(null, null);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            ImageData imageData = ImageDataFactory.create(baos.toByteArray());
            Image image = new Image(imageData);
            image.setHorizontalAlignment(HorizontalAlignment.CENTER); // Center the image
            return image;
        }
    }

    @FXML
    private void onClickExportInvoice(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new ExtensionFilter("PDF files", "*.pdf"));
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

                // Set logo and Company Name
                Image companyLogo = getCompanyLogoAsImage();
                document.add(companyLogo);
                Paragraph companyName = new Paragraph("XYZ BUS TERMINAL")
                        .setBold()
                        .setTextAlignment(TextAlignment.CENTER); // Center the text
                document.add(companyName);
                document.add(separator);

                // Adding invoice and due date fields
                document.add(new Paragraph("Invoice Info").setBold());
                Table invoiceInfoTable = new Table(2);
                invoiceInfoTable.addCell(new Cell().add("Invoice Number:").setBold());
                invoiceInfoTable.addCell(new Cell().add(labelnvoiceID.getText()));
                invoiceInfoTable.addCell(new Cell().add("Due Date:").setBold());
                invoiceInfoTable.addCell(new Cell().add(datePickerInvDueDate.getValue().toString()));

                document.add(invoiceInfoTable);
                document.add(separator);

                // From Company Details
                document.add(new Paragraph("From").setBold());
                Table fromDetailsTable = new Table(4);
                fromDetailsTable.addCell(new Cell().add("Business Name:").setBold());
                fromDetailsTable.addCell(new Cell().add(txtFieldFromCompanyName.getText()));
                fromDetailsTable.addCell(new Cell().add("Address:").setBold());
                fromDetailsTable.addCell(new Cell().add(txtFieldFromCompanyAddress.getText()));
                fromDetailsTable.addCell(new Cell().add("Phone:").setBold());
                fromDetailsTable.addCell(new Cell().add(txtFieldFromCompanyPhone.getText()));
                fromDetailsTable.addCell(new Cell().add("Email:").setBold());
                fromDetailsTable.addCell(new Cell().add(txtFieldFromCompanyEmail.getText()));

                document.add(fromDetailsTable);
                document.add(separator);

                // To Company Company Details
                document.add(new Paragraph("To").setBold());
                Table toCompanyTable = new Table(3);
                toCompanyTable.addCell(new Cell().add("Client Name:").setBold());
                toCompanyTable.addCell(new Cell().add(txtFieldBillToClientName.getText()));
                toCompanyTable.addCell(new Cell().add("Phone:").setBold());
                toCompanyTable.addCell(new Cell().add(txtFieldBillToPhoneNumber.getText()));
                toCompanyTable.addCell(new Cell().add("Address:").setBold());
                toCompanyTable.addCell(new Cell().add(txtFieldBillToAddress.getText()));

                document.add(toCompanyTable);
                document.add(separator);

                // Service Item Table
                document.add(new Paragraph("Service Items").setBold());
                Table servicesTable = new Table(new float[]{50, 100, 50, 50, 50, 100});
                servicesTable.addCell("Service");
                servicesTable.addCell("Description");
                servicesTable.addCell("Rate");
                servicesTable.addCell("QTY");
                servicesTable.addCell("Amount");
                servicesTable.addCell("Tax");

                for (InvoiceService service : currentInvoiceServiceList) {
                    servicesTable.addCell(service.getServiceName());
                    servicesTable.addCell(service.getServiceDescription());
                    servicesTable.addCell(String.valueOf(service.getServiceRate()));
                    servicesTable.addCell(String.valueOf(service.getServiceQty()));
                    servicesTable.addCell(String.valueOf(service.getItemTotal()));
                    servicesTable.addCell(String.valueOf(service.getServiceTaxAmount()));
                }

                document.add(servicesTable);

                if (!txtFieldInvNote.getText().isEmpty()) {
                    document.add(new Paragraph("Note:").setBold());
                    document.add(new Paragraph(txtFieldInvNote.getText()));
                }

                // Close the document
                document.close();

                showSuccessDialog("Success", "Invoice exported successfully.");
            } else {
                showErrorDialog("Export Cancelled", "Invoice export was cancelled.");
            }
        } catch (Exception e) {
            showErrorDialog("Export Error", "An error occurred while exporting the invoice: " + e.toString());
        }
    }

    @FXML
    private void onClickDeleteSelection(ActionEvent event) {
        InvoiceService serviceToDelete = serviceListTableView.getSelectionModel().getSelectedItem();
        if (serviceToDelete == null) {
            showErrorDialog("Invalid/No Selection!", "Please select an item to delete!");
        } else {
            for (InvoiceService invSrv : currentInvoiceServiceList) {
                if (invSrv == serviceToDelete) {
                    currentInvoiceServiceList.remove(invSrv);
                }
            }
        }
        tableListUpdate();
    }

    private void ModificationSceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmllocation));
            AnchorPane newContent = loader.load();

            AccountantInvoiceGenerationAddItemController controller = loader.getController();

            controller.SceneSwitchTempDataSet(datePickerInvDueDate, txtFieldBillToClientName, txtFieldBillToPhoneNumber,
                    txtFieldBillToAddress, txtFieldFromCompanyName, txtFieldFromCompanyEmail,
                    txtFieldFromCompanyPhone, txtFieldFromCompanyAddress, txtFieldInvNote, currentInvoiceServiceList);

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    private boolean fieldValidation() {
        // Validate Date
        LocalDate dateValue = datePickerInvDueDate.getValue();
        if (dateValue == null) {
            showErrorDialog("Select Date", "Please select an invoice date!");
            return false;
        }

        // Validate Client Name
        if (txtFieldBillToClientName.getText().trim().isEmpty()) {
            showErrorDialog("Invalid Input", "Please enter the client's name.");
            return false;
        }

        // Validate Phone Number
        if (!Validator.isInteger(txtFieldBillToPhoneNumber.getText().trim())) {
            showErrorDialog("Invalid Phone Number", "Please enter a valid phone number.");
            return false;
        }

        // Validate Company Name
        if (txtFieldFromCompanyName.getText().trim().isEmpty()) {
            showErrorDialog("Invalid Input", "Please enter your company name.");
            return false;
        }

        // Validate Email
        if (!Validator.validateEmail(txtFieldFromCompanyEmail.getText().trim())) {
            showErrorDialog("Invalid Email", "Please enter a valid email.");
            return false;
        }

        // Validate Company Phone
        if (!Validator.isInteger(txtFieldFromCompanyPhone.getText().trim())) {
            showErrorDialog("Invalid Phone Number", "Please enter a valid phone number.");
            return false;
        }

        // Validate Company Address
        if (txtFieldFromCompanyAddress.getText().trim().isEmpty()) {
            showErrorDialog("Invalid Input", "Please enter your company address.");
            return false;
        }

         //Validate Invoice Note (if necessary, assuming it's optional)
         if (txtFieldInvNote.getText().trim().isEmpty()) {
             showErrorDialog("Invalid Input", "Please enter an invoice note.");
             return false;
         }
         
         //Validate Client Address
        if (txtFieldBillToAddress.getText().trim().isEmpty()) {
            showErrorDialog("Invalid Input", "Please enter the client's address.");
            return false;
        }
        
        if(paymentStatus.getSelectedToggle() == null){
           showErrorDialog("Empty Payment Status", "Please select a payment status!");
            return false;   
          }
        
        

        // All checks passed
        return true;
    }
    
    private void calcualteSubTotalAndTax(){
        double subTotal = 0,totalTax = 0;
        double totalAmount = 0;
        
        for (InvoiceService serviceObj:currentInvoiceServiceList ){
            subTotal += serviceObj.getItemTotal();
            totalTax += serviceObj.getServiceTaxAmount();
            invoiceItems += "|" + serviceObj.getServiceName();
        }
        totalAmount = totalTax + subTotal;
        
        txtFieldSubTotal.setText(String.valueOf(subTotal));
        txtFieldTotalTax.setText(String.valueOf(totalTax));
        txtFieldTotalAmount.setText(String.valueOf(totalAmount));
    }
    
    private void customDialogForTransactionRecord(){
        this.dialogContent = MFXGenericDialogBuilder.build().makeScrollable(true).get();
        
        this.dialog = MFXGenericDialogBuilder.build(dialogContent)
					.toStageDialogBuilder()
					.initModality(Modality.APPLICATION_MODAL)
					.setDraggable(true)
					.setTitle("Modification Options")
					.setOwnerNode(rootPane)
					.setScrimPriority(ScrimPriority.WINDOW)
					.setScrimOwner(true)
					.get();
        
        MFXButton backButton = new MFXButton("Go Back");
        
        MFXButton clearAllButton = new MFXButton("Clear All");
        
        backButton.setOnAction(event -> {
            dialog.close();
        });
        
        clearAllButton.setOnAction(event -> {
            clearAllFields();
            dialog.close();
        });
        
        dialogContent.addActions(backButton,clearAllButton);
        
        
        
        dialogContent.setMaxSize(15, 10);
	dialogContent.setHeaderText("Transaction Recorded");
        dialogContent.setContentText("Transaction has been recorded, you may choose to clear all fields or close this dialog!");
        dialog.showDialog();
        
	}
    
    private void clearAllFields(){
       txtFieldBillToAddress.clear();
       txtFieldBillToClientName.clear();
       txtFieldBillToPhoneNumber.clear();
       txtFieldFromCompanyAddress.clear();
       txtFieldFromCompanyEmail.clear();
       txtFieldFromCompanyName.clear();
       txtFieldFromCompanyPhone.clear();
       txtFieldInvNote.clear();
       txtFieldSubTotal.clear();
       txtFieldTotalAmount.clear();
       txtFieldTotalTax.clear();
       paymentStatus.getSelectedToggle().setSelected(false);
    }
    
    @FXML
    private void onClickRecordTransaction(ActionEvent event) {
        
        if(fieldValidation()){
          String currentPaymentStatus;
          if(paymentStatus.getSelectedToggle().equals(radioInvPaid)){currentPaymentStatus = "Paid";}
          else{currentPaymentStatus = "Unpaid";}
          currentTransaction = new Transaction(datePickerInvDueDate.getValue(),"INV",
                                               Double.parseDouble(txtFieldTotalAmount.getText()),
                                               currentPaymentStatus,invoiceItems);
          RelationshipDatabaseClass.getInstance().addItemToAllAvailableTransactions(currentTransaction);
          System.out.println(currentTransaction.toString());
          customDialogForTransactionRecord();
        }
    }

}
