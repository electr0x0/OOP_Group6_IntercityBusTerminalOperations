
package com.busterminal.controller.driver;

import com.busterminal.model.TripHistory;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class TripHistoryController implements Initializable {

    @FXML
    private TableView<TripHistory> tableViewTripHistory;
    @FXML
    private TableColumn<TripHistory, LocalDate> dateCol;
    @FXML
    private TableColumn<TripHistory, String> routeCol;
    @FXML
    private TableColumn<TripHistory, String> statusCol;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }    

    @FXML
    private void switchToDashboardOnClick(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void viewDetailsOnClick(ActionEvent event) {
        
        
    }

    @FXML
    private void confirmTripCompletedOnClick(ActionEvent event) {
        
    }

    @FXML
    private void deleteOnClick(ActionEvent event) {
        
    }
    
}
