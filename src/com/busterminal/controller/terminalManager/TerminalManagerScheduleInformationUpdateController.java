/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.BusTripSchedule;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.utilityclass.Validator;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerScheduleInformationUpdateController implements Initializable {

    @FXML
    private MFXDatePicker modifiedDatePicker;
    @FXML
    private MFXTextField txtFieldAdFare;
    @FXML
    private MFXTextField txtFieldChFare;
    @FXML
    private MFXTextField txtFieldWkndFare;
    @FXML
    private AnchorPane rootPane;
    
    private BusTripSchedule modifiedSchedule;
    @FXML
    private MFXToggleButton tripStatus;
    
    ArrayList<BusTripSchedule> allAvailableTripSchedules = RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
    }

    @FXML
    private void onClickConfirmModification(ActionEvent event) {
        String modifiedFareAd = txtFieldAdFare.getText();
        String modifiedFareCh = txtFieldChFare.getText();
        String modifiedFareWknd = txtFieldWkndFare.getText();
        LocalDate modifiedDate = modifiedDatePicker.getValue();
        
        boolean intValidationStatus = Validator.isInteger(modifiedFareAd) && 
                                   Validator.isInteger(modifiedFareCh) && 
                                   Validator.isInteger(modifiedFareWknd);
        
        boolean dateValidationStatus = Validator.isValidDate(modifiedDate);
        
        if(!intValidationStatus){
            showErrorDialog("Invalid Fare Value", 
                            "One or more fare value is invalid, please correct it!")
                    ;return;}
        
        if(!dateValidationStatus){showErrorDialog("Invalid Date","Only today's or future dates are valid");return;}
        
        //Modify Parameters        
        modifiedSchedule.getTrip().setAdultFare(Integer.parseInt(modifiedFareAd));
        modifiedSchedule.getTrip().setChildrenFare(Integer.parseInt(modifiedFareCh));
        modifiedSchedule.getTrip().setWeekendFare(Integer.parseInt(modifiedFareWknd));
        modifiedSchedule.setScheduleDate(modifiedDate);
        
        if(tripStatus.isSelected()){
            modifiedSchedule.setTripScheduleStatus(true);
        }else{
            modifiedSchedule.setTripScheduleStatus(false);
        }
        
        
        // Update Object in Object Database
        for (int i = 0; i < allAvailableTripSchedules.size(); i++ ){
            if(allAvailableTripSchedules.get(i).getScheduleId() == modifiedSchedule.getScheduleId()){
                allAvailableTripSchedules.set(i, modifiedSchedule);
                break;
            }
        }
        
        RelationshipDatabaseClass.getInstance().setAllAvailableTripSchedules(allAvailableTripSchedules);
        
        showSuccessDialog("Success!","Schedule Information has been updated!");
        backToScheduleSetter();
    }

    @FXML
    private void onClickGoBackIcon(MouseEvent event) {
        backToScheduleSetter();
    }
    
    private void backToScheduleSetter(){
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerScheduleSetter.fxml"));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found","Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }
    
    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert",rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success",rootPane);
        alertDialog.openMFXDialog();
    }
    
    public void setDataFromSceneSwitch(BusTripSchedule scheduleToMod){
        modifiedSchedule = scheduleToMod;
        modifiedDatePicker.setValue(modifiedSchedule.getScheduleDate());
        txtFieldAdFare.setText(String.valueOf(modifiedSchedule.getTrip().getAdultFare()));
        txtFieldChFare.setText(String.valueOf(modifiedSchedule.getTrip().getChildrenFare()));
        txtFieldWkndFare.setText(String.valueOf(modifiedSchedule.getTrip().getWeekendFare()));
        
        if(modifiedSchedule.isTripScheduleStatus()){
            tripStatus.setSelected(true);
            tripStatus.setText("Active");
        }else{
            tripStatus.setSelected(false);
            tripStatus.setText("Inactive");
        }
        
    }

    @FXML
    private void updateToggleStatus(MouseEvent event) {
        if(tripStatus.isSelected()){
            tripStatus.setText("Active");
        }else{tripStatus.setText("Inactive");}
    }
    
}
