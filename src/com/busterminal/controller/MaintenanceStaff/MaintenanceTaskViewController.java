/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.MaintenanceStaff;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MaintenanceTaskViewController implements Initializable {

    @FXML
    private TextArea vewTextArea;
    @FXML
    private Label driverIdLabel;
    @FXML
    private Label busIdLabel;
    @FXML
    private Label maintenanceTypeLabel;
    @FXML
    private Label requestLabel;
    @FXML
    private AnchorPane anchorPaneShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   public void setDescription(String description){
       vewTextArea.appendText(description);
   }

    public void setDriverIdLabel(String driverIdLabel) {
        this.driverIdLabel.setText(driverIdLabel);
    }

    public void setBusIdLabel(String busIdLabel) {
        this.busIdLabel.setText(busIdLabel);
    }

    public void setMaintenanceTypeLabel(String  maintenanceTypeLabel) {
        this.maintenanceTypeLabel.setText(maintenanceTypeLabel);
    }

    public void setRequestLabel(LocalDate requestLabel) {
        this.requestLabel.setText(requestLabel.toString());
    }

    @FXML
    private void goToLoginOnMouseClick(MouseEvent event) {
         Stage stage = (Stage) anchorPaneShow.getScene().getWindow();

        // Close the stage
        stage.close();
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/MaintenanceStaff/CheckMaintenanceTask.fxml"));
            AnchorPane newContent = loader.load();
            // Get the controller
            anchorPaneShow.getChildren().setAll(newContent); //RootPane is AnchorPaneShow name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
