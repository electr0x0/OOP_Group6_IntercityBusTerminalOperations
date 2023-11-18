
package com.busterminal.controller.driver;
import com.busterminal.controller.*;
import com.busterminal.model.Passenger;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//import mainPkg.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PassengerInfoController implements Initializable {

    @FXML
    private TableView<Passenger> tableViewOfPassengerInfo;
    @FXML
    private TableColumn<Passenger, String> passengerNameCol;
    
    @FXML
    private TableColumn<Passenger, String> passengerSeatNumberCol;
    @FXML
    private TableColumn<Passenger, Integer> passengerContactNumberCol;
    @FXML
    private TableColumn<Passenger, String> passengerEmailCol;
    
    private ObservableList<Passenger> pList;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       passengerNameCol.setCellValueFactory(new PropertyValueFactory<>("name")); 
       passengerSeatNumberCol.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
       passengerContactNumberCol.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
       passengerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
       pList= FXCollections.observableArrayList();
       FileInputStream fi = null;
       DataInputStream di = null;
        
        try{
            File f = new File("PassengerData.bin");
            fi = new FileInputStream(f);
            di = new DataInputStream(fi);
            
            
            
            while(true){
                
                  Passenger p1 = new Passenger(di.readUTF(),di.readUTF(),di.readUTF(),di.readInt());     
                  
                  pList.add(p1);
                  
                  tableViewOfPassengerInfo.setItems(pList);
                
            }
            
        }
        catch (Exception e){
           try {
               di.close();
           } catch (IOException ex) {
               Logger.getLogger(PassengerInfoController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
                
    }    

    @FXML
    private void switchToDashboardScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
        
    }
    
}
