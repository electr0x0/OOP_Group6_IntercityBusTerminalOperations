/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickDashboardBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerMainDashboardContent.fxml"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickManageStaffBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickSchedulingBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickTicketPricingBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerTicketPricing.fxml"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickFinancialBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickCommunicationHubBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    @FXML
    private void onClickSignOutBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/"));

            // Clear existing content and set the new content
            dashboardContentPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }
    
}
