/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.controller.CommunicationHubController;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerDashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane logoContainer;
    @FXML
    private MFXScrollPane scrollPane;
    @FXML
    private AnchorPane dashboardContentPane;
    @FXML
    private MFXButton dashboardBtn;
    @FXML
    private MFXButton busManagementBtn;
    @FXML
    private MFXButton tripFareButton;
    @FXML
    private MFXButton busTripScheBtn;
    @FXML
    private MFXButton manageStaffBtn;
    @FXML
    private MFXButton finanCialReportBtn;
    @FXML
    private ImageView financialReportBtn;
    @FXML
    private MFXButton comHubBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void updateCurrentButtonStyle(MFXButton selectedButton) {
        // List of all buttons
        MFXButton[] buttons = {dashboardBtn, busManagementBtn, tripFareButton, busTripScheBtn, manageStaffBtn, finanCialReportBtn, comHubBtn};

        // Set all buttons to btnNotSelected
        for (MFXButton button : buttons) {
            button.getStyleClass().removeAll("btnSelected", "btnNotSelected"); // Remove both to avoid duplication
            button.getStyleClass().add("btnNotSelected");
        }

        // Set the selected button to btnSelected
        selectedButton.getStyleClass().remove("btnNotSelected");
        selectedButton.getStyleClass().add("btnCurrentSelected");
    }

    @FXML
    private void onClickDashboardBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerMainDashboardContent.fxml");
        updateCurrentButtonStyle(dashboardBtn);
    }

    private void onClickManageStaffBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/");
    }

    private void onClickSchedulingBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/");
    }

    @FXML
    private void onClickTicketPricingBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerTicketPricing.fxml");
        updateCurrentButtonStyle(tripFareButton);

    }

    @FXML
    private void onClickFinancialBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerFinancialReports.fxml");
        updateCurrentButtonStyle(finanCialReportBtn);
    }

    @FXML
    private void onClickCommunicationHubBtn(ActionEvent event) {
        SceneSwitchComHubWithDataPass("/com/busterminal/views/generic/CommunicationHub.fxml", "Mr. Manager", "Manager");
        updateCurrentButtonStyle(comHubBtn);
    }

    @FXML
    private void onClickSignOutBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/");
    }

    @FXML
    private void onClickBusManagementBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerBusManagement.fxml");
        updateCurrentButtonStyle(busManagementBtn);
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
    
    private void SceneSwitchComHubWithDataPass(String fxmllocation, String empName, String empType){
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

    @FXML
    private void onClickBusTripSchedule(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerScheduleSetter.fxml");
        updateCurrentButtonStyle(busTripScheBtn);
    }

    @FXML
    private void onClickManageStafFBtn(ActionEvent event) {
        SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerStaffMangement.fxml");
        updateCurrentButtonStyle(manageStaffBtn);
    }
    
    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

}
