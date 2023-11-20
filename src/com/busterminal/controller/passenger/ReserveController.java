/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.ReserveBus;
import com.busterminal.model.SceneSwicth;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReserveController implements Initializable {

    @FXML
    private AnchorPane anchorpaneReserve;
    private ToggleGroup acType;
    @FXML
    private ComboBox<String> citySelectCombo;
    @FXML
    private ComboBox<Integer> daySelectCombo;
    @FXML
    private ComboBox<String> busTypeCombo;
    private RadioButton acRB;
    private RadioButton nonacRB;
    
    @FXML
    private Label errorLabel;

    public RadioButton getAcRB() {
        return acRB;
    }

    public RadioButton getNonacRB() {
        return nonacRB;
    }

    public ToggleGroup getAcType() {
        return acType;
    }

    public ComboBox<String> getCitySelectCombo() {
        return citySelectCombo;
    }

    public ComboBox<Integer> getDaySelectCombo() {
        return daySelectCombo;
    }

    public ComboBox<String> getBusTypeCombo() {
        return busTypeCombo;
    }

    public DatePicker getDateDatePicker() {
        return dateDatePicker;
    }
    @FXML
    private DatePicker dateDatePicker;
    
    private ObservableList<ReserveBus> reserveBusList = FXCollections.observableArrayList();
    private ObservableList<ReserveBus> helperList = FXCollections.observableArrayList();

    public ObservableList<ReserveBus> getReserveBusList() {
        return reserveBusList;
    }

    public void setReserveBusList(ObservableList<ReserveBus> reserveBusList) {
        this.reserveBusList = reserveBusList;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        citySelectCombo.getItems().addAll("Dhaka","Rajshahi","Sylhet","Chittagong","Khulna");
        daySelectCombo.getItems().addAll(1,2,3,4,5);
        busTypeCombo.getItems().addAll("Mini Bus","Coach","Double Decker Bus");
        //reserveBusList = FXCollections.observableArrayList();
    }    


    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpaneReserve,"/com/busterminal/views/passenger/Dashboard_Passenger.fxml");
        
    }

    @FXML
    private void swichSceneToReservedBusListsScene(ActionEvent event) throws IOException {
        
        ReserveBus  r1 = new ReserveBus("Mini Bus","AC","Dhaka",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r2 = new ReserveBus("Coach","Non AC","Dhaka",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r3 = new ReserveBus("Coach","Non AC","Rajshahi", daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r4 = new ReserveBus("Mini Bus","AC","Sylhet",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r5 = new ReserveBus("Double Decker Bus","Non AC","Chittagong",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r6 = new ReserveBus("Coach","AC","Dhaka",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r7 = new ReserveBus("Double Decker Bus","Non AC","Khulna",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r8 = new ReserveBus("Mini Bus","AC","Khulna",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r9 = new ReserveBus("Coach","Non AC","Rajshahi",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r10 = new ReserveBus("Mini Bus","Non AC","Dhaka",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r11 = new ReserveBus("Mini Bus","AC","Sylhet",daySelectCombo.getValue(), dateDatePicker.getValue());
        ReserveBus  r12 = new ReserveBus("Coach","AC","Dhaka",daySelectCombo.getValue(), dateDatePicker.getValue());
        
        reserveBusList.addAll(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12);
        //System.out.println(reserveBusList);
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/ReservedBusList.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        ReservedBusListController p = someLoader.getController();
        p.setReserveBusList(reserveBusList);
        //p.setBus(busTypeCombo.getValue());
        //p.setCity(citySelectCombo.getValue());
        //p.setDate(dateDatePicker.getValue());
        //p.setDay(daySelectCombo.getValue());
        
       String city = citySelectCombo.getValue();
       String bus = busTypeCombo.getValue();
        System.out.println(bus);
       if (bus !=null){
           
      
        //System.out.println(reserveBusList);
       for(ReserveBus x: reserveBusList){
           //System.out.println(x.getBusType());
           if (x.getBusType().equals(bus) && x.getCity().equals(city)){
               //System.out.println(x);
               helperList.add(x);
               p.setHelperList(helperList);
               System.out.println(helperList);
            
               
           }
       }}
       else{
           errorLabel.setText("Please select all fields");
       }
        System.out.println(helperList);
        if (helperList.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(" ");
        alert.setTitle("No Available bus Found");
        alert.setHeaderText("Sorry, there are no available buses in your desired city");
        alert.show();
       }
        //System.out.println(helperList);
       
        //System.out.println(helperList);
        //p.setHelperList(helperList);
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
        
        
        
    }
    
}
