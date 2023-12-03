/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.accountant;

import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantSetRefundPolicyController implements Initializable {

    @FXML
    private TextArea textAreaRefundPolicyContent;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        if (RelationshipDatabaseClass.getInstance().getRefundPolicy() != null) {
            textAreaRefundPolicyContent.setText(RelationshipDatabaseClass.getInstance().getRefundPolicy());
        }
    }

    @FXML
    private void onClickLoadFromTextFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getAbsolutePath())));
                textAreaRefundPolicyContent.setText(content);
                MFXDialog.showSuccessDialog("Success", "File loaded successfully", rootPane);
            } catch (IOException e) {
                MFXDialog.showErrorDialog("Error", "Failed to load file: " + e.getMessage(), rootPane);
            }
        } else {
            MFXDialog.showSuccessDialog("Failed", "Cancelled by User!", rootPane);
        }
    }

    @FXML
    private void onClickSetPolicy(ActionEvent event) {
        if (textAreaRefundPolicyContent.getText().isEmpty()) {
            MFXDialog.showErrorDialog("Empty Refund Policy", "Please define the refund policy before Setting", rootPane);
            return;
        }
        RelationshipDatabaseClass.getInstance().setRefundPolicy(textAreaRefundPolicyContent.getText());
        MFXDialog.showSuccessDialog("Sucess", "Refund Policy has been Set!", rootPane);
    }
    
    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            MFXDialog.showErrorDialog("FXML not Found", "Unable to switch scene, make sure FXML is present in the specified path\n"+e.getMessage(), rootPane);
        }
    }

    @FXML
    private void onClickGoBack(MouseEvent event) {
        SceneSwitch("/com/busterminal/views/accountantUser/AccountantManageRefundReq.fxml");
    }

}
