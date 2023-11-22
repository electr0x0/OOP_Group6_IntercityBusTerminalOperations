/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.model.Contact;
import com.busterminal.model.Feedback;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ContactController implements Initializable {

    @FXML
    private AnchorPane anchorpaneContact;
    @FXML
    private ComboBox<String> selectTypeCombo;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private TextArea detailsTextField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectTypeCombo.getItems().addAll("General Inquiry","Technical Support","Job Opportunities","Other");
    }    

    @FXML
    private void switchtoDashboardScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/Dashboard_Passenger.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
    }

    @FXML
    private void submitResponseOnClick(ActionEvent event) {
        
        
        String type = selectTypeCombo.getValue();
        String name =nameTextField.getText();
        String email= emailTextField.getText();
        String subject= subjectTextField.getText();
        
        String details = detailsTextField.getText();
           
        
        if( !name.isEmpty() && !email.isEmpty()  && !subject.isEmpty() && !details.isEmpty()  ){
           
           
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("");
        alert.setTitle("Sent");
        alert.setHeaderText("Successful");
        alert.show();
     
        Contact c = new Contact(type,name,email,subject,details);
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("Contact.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            
            
            oos.writeObject(c);

        } catch (IOException ex) {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                
        
        
        }
        else{
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.setContentText("");
        alert1.setTitle("Fields cant be empty");
        alert1.setHeaderText("Plese give all details");
        alert1.show();
        }
        
        
    }
    
}
