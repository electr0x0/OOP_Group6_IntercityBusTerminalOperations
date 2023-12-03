/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.controller.CommunicationHubController;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.views.Employee.EmployeeDashboardController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantDashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane logoContainer;
    @FXML
    private MFXScrollPane scrollPane;
    @FXML
    private AnchorPane dashboardContentPane;
    @FXML
    private MFXButton btnInvoice;
    @FXML
    private MFXButton btnReim;
    @FXML
    private MFXButton btnComHub;
    @FXML
    private MFXButton btnPurchase;
    @FXML
    private MFXButton btnRefund;
    @FXML
    private MFXButton btnTransaction;
    @FXML
    private MFXButton btnDashboard;
    @FXML
    private MFXButton btnMyAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startingContentSetter();
        TransitionUtility.materialScale(rootPane);
    }

    @FXML
    private void onClickSignOutBtn(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/login.fxml"));
        root = (Parent) someLoader.load();

        Scene someScene = new Scene(root);

        Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        someStage.setScene(someScene);
        someStage.show();
    }

    public void updateCurrentButtonStyle(MFXButton selectedButton) {
        // List of all buttons
        MFXButton[] buttons = {btnInvoice, btnReim, btnComHub, btnPurchase, btnRefund, btnTransaction, btnDashboard};

        // Set all buttons to btnNotSelected
        for (MFXButton button : buttons) {
            button.getStyleClass().removeAll("btnCurrentSelectedAcc", "btnNotSelectedAcc"); // Remove both to avoid duplication
            button.getStyleClass().add("btnNotSelectedAcc");
        }

        // Set the selected button to btnSelected
        selectedButton.getStyleClass().remove("btnNotSelectedAcc");
        selectedButton.getStyleClass().add("btnCurrentSelectedAcc");
    }

    @FXML
    private void onClickGenerateInvoiceButton(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantInvoiceGeneration.fxml");
        updateCurrentButtonStyle(btnInvoice);
    }

    @FXML
    private void onClickReimbButton(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantEmployeeReimbursementManagement.fxml");
        updateCurrentButtonStyle(btnReim);
    }

    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }

    @FXML
    private void onClickComHub(ActionEvent event) {
        SceneSwitchComHubWithDataPass("/com/busterminal/views/generic/CommunicationHub.fxml", "Ms. Accountant", "Accountant");
        updateCurrentButtonStyle(btnComHub);
    }

    private void SceneSwitchToHREmployeeScene() {

    }

    private void SceneSwitchComHubWithDataPass(String fxmllocation, String empName, String empType) {
        try {
            // Load the new content FXML file

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmllocation));
            AnchorPane newContent = loader.load();

            CommunicationHubController controller = loader.getController();

            controller.setCurrentUserWhileSceneSwitch(empName, empType);

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
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

    @FXML
    private void onClickPurchaseBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantPurhcaseView.fxml");
        updateCurrentButtonStyle(btnPurchase);
    }

    @FXML
    private void onClickRefundButton(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantManageRefundReq.fxml");
        updateCurrentButtonStyle(btnRefund);
    }

    @FXML
    private void onClickTransactions(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantDashboardTransactionView.fxml");
        updateCurrentButtonStyle(btnTransaction);
    }

    @FXML
    private void onClickDashboardBtn(ActionEvent event) {
        startingContentSetter();
    }

    private void startingContentSetter() {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantDashboardMainContent.fxml");
        updateCurrentButtonStyle(btnDashboard);
    }

    @FXML
    private void onClickMyAccount(ActionEvent event) {
        try {

            Parent root = null;
            FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            
            root = (Parent) someLoader.load();
            EmployeeDashboardController controller = someLoader.getController();
            
            controller.setEmpID(RelationshipDatabaseClass.getInstance().getCurrentLoggedIn());
            controller.LoadData();
            
            Scene someScene = new Scene(root);

            Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            someStage.setScene(someScene);
            someStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }

}
