/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;


import com.busterminal.model.Contact;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ViewInquiryController implements Initializable {

    @FXML
    private TextArea viewInquiryTextArea;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Contact.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Contact t;

            while (true) {
                t = (Contact) ois.readObject();

               
                viewInquiryTextArea.appendText("Name : " + t.getName() + "\n");
                viewInquiryTextArea.appendText("E-mail : " + t.getEmail() + "\n" );
                viewInquiryTextArea.appendText("Inquiry Type : " + t.getInquiryType() + "\n" );
                viewInquiryTextArea.appendText("Subject : " + t.getSubject()+ "\n" + "\n");
                viewInquiryTextArea.appendText("Details : " + t.getDetails()+ "\n" + "\n");
                 
  

            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException ex) {
                // Handle the exception if needed
            }
        }
        
       
    }    

    private void backOnClick(ActionEvent event) throws IOException {
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Addministrator/Home.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
        
    }
    
}
