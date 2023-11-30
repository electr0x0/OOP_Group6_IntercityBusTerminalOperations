/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.driver;


import java.io.DataOutputStream;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReqReimbursementController implements Initializable {

    @FXML
    private TextField empNameTextField;
    @FXML
    private TextField expenseAmountTextField;
    @FXML
    private DatePicker reqDateCombo;
    @FXML
    private ComboBox<String> expenseCombo;
    @FXML
    private ToggleGroup paymentMethod;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        expenseCombo.getItems().setAll("Fuel Costs","Parking Fees","Meals","Tolls","Miscellaneous Expenses");
        
    }    

    @FXML
    private void submitReqOnClick(ActionEvent event) {
        
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Reimbursement Request Sent");
        a.setTitle("Confirmation");
        a.showAndWait();
        
         try{
                FileOutputStream fos = new FileOutputStream("DriverImbursement.bin", true);
                DataOutputStream dos = new DataOutputStream(fos);
                
                    dos.writeUTF("Employee Name: " + empNameTextField.getText());
                    dos.writeUTF("Expense Amount: " +expenseAmountTextField.getText());
                    dos.writeUTF("Expense Type: "+ expenseCombo.getValue());
                    dos.writeUTF("Request Date: "+reqDateCombo.getValue().toString());
                    dos.writeUTF("Payment MethodL: "+ paymentMethod.getSelectedToggle().toString());
                    
                    
                
                    dos.close();
            }
            catch(Exception e){
                //SHOW e.toString() IN AN ALERT
            }           
        
        
    }

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }
    
}
