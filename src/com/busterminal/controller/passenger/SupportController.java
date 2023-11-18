/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.SceneSwicth;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class SupportController implements Initializable {

    @FXML
    private AnchorPane anchorpaneSupport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void feedbackConfirmationOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(" Click on OK to return to Dashboard ");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Feedback shared");
        //alert.showAndWait();
        ButtonType okButton = new ButtonType("OK");
    ButtonType cancelButton = new ButtonType("Submit Another Complaint");
    alert.getButtonTypes().setAll(okButton, cancelButton);


    alert.showAndWait().ifPresent(buttonType -> {
    if (buttonType == okButton ) {
        try {
            new SceneSwicth (anchorpaneSupport,"views/passenger/Dashboard_Passenger.fxml");
        } catch (IOException ex) {
            Logger.getLogger(TicketDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }             
    });
    }

    @FXML
    private void switchToDashboardScene(ActionEvent event) throws IOException {
        new SceneSwicth( anchorpaneSupport,"views/passenger/Dashboard_Passenger.fxml");
    }
    
}
