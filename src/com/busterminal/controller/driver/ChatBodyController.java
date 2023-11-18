
package com.busterminal.controller.driver;

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
        selectUserCombo.getItems().addAll("Terminal Manager","Maintenance Staff","Accountant","Ticket Seller");
    }    

    @FXML
    private void sendMessageOnClick(ActionEvent event) {
    }

    @FXML
    private void switchToChatScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
        
    }
    
}
