/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.model.accountant.InvoiceService;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantInvoiceGenerationAddItemController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXComboBox<String> comboBoxService;
    @FXML
    private MFXTextField txtFieldServiceRate;
    @FXML
    private MFXSlider spinnerServiceQty;
    @FXML
    private MFXSlider spinnerServiceTaxPercent;

    private MFXDatePicker datePickerInvDueDate;
    private MFXTextField txtFieldBillToClientName;
    private MFXTextField txtFieldBillToPhoneNumber;
    private MFXTextField txtFieldBillToAddress;
    private MFXTextField txtFieldFromCompanyName;
    private MFXTextField txtFieldFromCompanyEmail;
    private MFXTextField txtFieldFromCompanyPhone;
    private MFXTextField txtFieldFromCompanyAddress;
    private MFXTextField txtFieldInvNote;

    private MFXStageDialog dialog;
    private MFXGenericDialog dialogContent;
    @FXML
    private MFXTextField txtFieldServiceDescriptionA;

    private InvoiceService currInvService;

    private ArrayList<InvoiceService> currentInvoiceServiceList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        comboBoxService.getItems().addAll("Service A", "Service B", "Service C");

    }

    private void initInvoice() {

        String textServiceDescription = txtFieldServiceDescriptionA.getText();
        if (textServiceDescription.isEmpty()) {
            showErrorDialog("Invalid Service Description", "Please input brief description!");
            return;
        }

        String selectedService = comboBoxService.getSelectedItem();
        if (selectedService == null) {
            showErrorDialog("Invalid Selection", "Please select a service!");
            return;
        }

        String serviceRate = txtFieldServiceRate.getText();
        if (!Validator.isInteger(serviceRate)) {
            showErrorDialog("Invalid Service Rate", "Please input only integer on Service Rate");
            return;
        }

        double serviceQtyy = spinnerServiceQty.getValue();
        if (serviceQtyy == 0) {
            showErrorDialog("Invalid Service Quantity", "Please select a valid quantity >0 !");
            return;
        }

        double serviceTax = spinnerServiceTaxPercent.getValue();

        boolean updated = false;

        if (currentInvoiceServiceList != null) {
            for (InvoiceService invObj : currentInvoiceServiceList) {
                if (invObj.getServiceName().equals(selectedService)) {
                    double newTotal = (serviceQtyy * Integer.parseInt(serviceRate)) + invObj.getItemTotal();
                    invObj.setItemTotal(newTotal);
                    invObj.setServiceQty(invObj.getServiceQty() + serviceQtyy);
                    double newTax = (serviceQtyy * Integer.parseInt(serviceRate)) * (spinnerServiceTaxPercent.getValue()/100);
                    invObj.setServiceTaxAmount(invObj.getServiceTaxAmount() + newTax);
                    updated = true;
                    break;
                }
            }
        }

        if (!updated) {
            currInvService = new InvoiceService(selectedService, textServiceDescription, serviceQtyy, Integer.parseInt(serviceRate), serviceTax);
            currentInvoiceServiceList.add(currInvService);
        }
        
        goBackDialog();
    }

    public void goBackDialog() {
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

        backButton.setOnAction(event -> {
            ModificationSceneSwitch("/com/busterminal/views/accountantUser/AccountantInvoiceGeneration.fxml");
            dialog.close();
        });

        dialogContent.addActions(backButton);

        dialogContent.setMaxSize(15, 10);
        dialogContent.setHeaderText("Service added!");
        dialogContent.setContentText("Service has been succesfully added to invoice, please click Go Back!");
        dialog.showDialog();

    }

    private void ModificationSceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmllocation));
            AnchorPane newContent = loader.load();

            AccountantInvoiceGenerationController controller = loader.getController();

            controller.setAddedServiceFromSceneSwitch(currentInvoiceServiceList);
            controller.setOldValuesBeforeSceneSwitching(datePickerInvDueDate, txtFieldBillToClientName, txtFieldBillToPhoneNumber,
                    txtFieldFromCompanyName, txtFieldFromCompanyEmail, txtFieldFromCompanyPhone,
                    txtFieldFromCompanyAddress, txtFieldInvNote, txtFieldBillToAddress);

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }

    public void SceneSwitchTempDataSet(MFXDatePicker datePickerInvDueDate, MFXTextField txtFieldBillToClientName, MFXTextField txtFieldBillToPhoneNumber, MFXTextField txtFieldBillToAddress, MFXTextField txtFieldFromCompanyName, MFXTextField txtFieldFromCompanyEmail, MFXTextField txtFieldFromCompanyPhone, MFXTextField txtFieldFromCompanyAddress, MFXTextField txtFieldInvNote, ArrayList<InvoiceService> currentInvoiceServiceList) {
        this.datePickerInvDueDate = datePickerInvDueDate;
        this.txtFieldBillToClientName = txtFieldBillToClientName;
        this.txtFieldBillToPhoneNumber = txtFieldBillToPhoneNumber;
        this.txtFieldBillToAddress = txtFieldBillToAddress;
        this.txtFieldFromCompanyName = txtFieldFromCompanyName;
        this.txtFieldFromCompanyEmail = txtFieldFromCompanyEmail;
        this.txtFieldFromCompanyPhone = txtFieldFromCompanyPhone;
        this.txtFieldFromCompanyAddress = txtFieldFromCompanyAddress;
        this.txtFieldInvNote = txtFieldInvNote;
        this.currentInvoiceServiceList = currentInvoiceServiceList;
    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onClickAddInvoiceToTable(ActionEvent event) {
        initInvoice();
    }

    @FXML
    private void updateIfAlreadyAvailable(ActionEvent event) {
        String selectedService = comboBoxService.getSelectedItem();
        if(!selectedService.isEmpty()){
            for(InvoiceService invObj: currentInvoiceServiceList){
                if(invObj.getServiceName().equals(selectedService)){
                    txtFieldServiceRate.setText(String.valueOf(invObj.getServiceRate()));
                    txtFieldServiceDescriptionA.setText(invObj.getServiceDescription());
                    spinnerServiceTaxPercent.setValue(invObj.getServiceTaxPercent());
                }
            }
        }
    }

}
