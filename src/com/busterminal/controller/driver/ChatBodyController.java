
package com.busterminal.controller.driver;

import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.controller.passenger.SupportController;
import com.busterminal.model.Chat1;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class ChatBodyController implements Initializable {

    @FXML
    private TextArea chatTextArea;
    @FXML
    private ComboBox<String> selectUserCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectUserCombo.getItems().addAll("Terminal Manager","HR Manager");
    }    

    @FXML
    private void sendMessageOnClick(ActionEvent event) {
        
       // if (chatTextArea.getText() ! = null && selectUserCombo.getSelectionModel().getSelectedItem() != null){
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("Chat.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            //Feedback f1 = new Feedback(passengerNameTextField.getText(),Integer.parseInt(feedbackIdTextField.getText()),subjctTextField.getText(),feedbackTextArea.getText());
            Chat1 c = new Chat1(selectUserCombo.getValue(),chatTextArea.getText());
            
            oos.writeObject(c);

        } catch (IOException ex) {
            Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                
    
        //else{
                
                //}
   }

   

    @FXML
    private void switchToDashboardScene(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
        
    }
    
}
