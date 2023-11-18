/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerDestinationAndTimeDefinerController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    private  ArrayList<String> allLocations;
    private  ArrayList<String> allTimes;
    @FXML
    private MFXTextField txtFieldLocation;
    @FXML
    private MFXTextField txtFieldTimeSchedule;
    @FXML
    private MFXTextField txtFieldBusStandLocation;
    
    private MFXDialog dialogBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickBackBtn(ActionEvent event) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerTicketPricing.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            TerminalManagerTicketPricingController controller = loader.getController();

            
            controller.setComboItems(allLocations,allTimes);
            controller.setAllCombo();
                    

            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            MFXDialog alertDialog = new MFXDialog("FXML not Found","Unable to update Anchor Pane, make sure FXML is present in the specified path", "Close",rootPane);
            alertDialog.openMFXDialog();
        }
    }

    public void setLocationAndTimeArrayList(ArrayList<String> allLocations, ArrayList<String> allTimes) {
        this.allLocations = allLocations;
        this.allTimes = allTimes;
    }

    @FXML
    private void onClickAddLocation(ActionEvent event) {
        if (!txtFieldLocation.getText().isEmpty()){
            for(String location: allLocations){
                if (location.equals(txtFieldLocation.getText())){
                    dialogBox = new MFXDialog("Duplicate Entry", "Sorry, the entry already exists in the database. Please add a new entry", "Close", rootPane);
                    dialogBox.openMFXDialog();
                    return;
                }
            }
            
            
            allLocations.add(txtFieldLocation.getText());
            dialogBox = new MFXDialog("Successfully Added", "The Location entry has been successfully Added", "Close", rootPane);
            dialogBox.openMFXDialog();
        }else{
            dialogBox = new MFXDialog("Empty Entry", "Sorry, the entry field is empty. Plese input the entry and then press Add", "Close", rootPane);
            dialogBox.openMFXDialog();
        }
    }

    @FXML
    private void onClickAddTimeSchedule(ActionEvent event) {
        if (!txtFieldTimeSchedule.getText().isEmpty()){
            for(String location: allTimes){
                if (location.equals(txtFieldTimeSchedule.getText())){
                    dialogBox = new MFXDialog("Duplicate Entry", "Sorry, the entry already exists in the database. Please add a new entry", "Close", rootPane);
                    dialogBox.openMFXDialog();
                    return;
                }
            }
            allTimes.add(txtFieldTimeSchedule.getText());
            dialogBox = new MFXDialog("Successfully Added", "The Time entry has been successfully Added", "Close", rootPane);
            dialogBox.openMFXDialog();
        }else{
            dialogBox = new MFXDialog("Empty Entry", "Sorry, the entry field is empty. Plese input the entry and then press Add", "Close", rootPane);
            dialogBox.openMFXDialog();
        }
    }

    @FXML
    private void onClickAddBusStandLocation(ActionEvent event) {
    }
    
    
    
}
