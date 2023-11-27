
package com.busterminal.controller.driver;
import com.busterminal.model.Passenger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
//import mainPkg.*;

import java.net.URL;
import java.util.ResourceBundle;
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
       //passengerSeatNumberCol.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
       passengerContactNumberCol.setCellValueFactory(new PropertyValueFactory<>("contactNum"));
       passengerEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
       pList= FXCollections.observableArrayList();
        
       
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("PassengerList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Passenger f2;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    f2= (Passenger)ois.readObject();
                    pList.add(f2);
                    tableViewOfPassengerInfo.setItems(pList);
                    
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(f2);
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch(Exception e){
                // handling code
            }//nested catch     
            //outputTextArea.appendText("All objects are loaded successfully...\n");            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }           
    
    
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
