
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class TripHistoryDetailController implements Initializable {

    @FXML
    private TextField timeText;
    @FXML
    private TextField busText;
    @FXML
    private TextField fleetText;
    @FXML
    private TextField distanceText;
    @FXML
    private TextField fareText;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    public void setValues(String time, String bus, String fleet, String distance , String fare){
        
        timeText.setText(time);
        busText.setText(bus);
        fleetText.setText(fleet);
        distanceText.setText(distance);
        fareText.setText(fare);
        
        
        
        
        
        
    }

    @FXML
    private void switchToTripHistoryScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/TripHistory.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
    }
}
