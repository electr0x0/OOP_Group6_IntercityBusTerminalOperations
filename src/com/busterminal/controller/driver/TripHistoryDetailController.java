
package com.busterminal.controller.driver;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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
}
