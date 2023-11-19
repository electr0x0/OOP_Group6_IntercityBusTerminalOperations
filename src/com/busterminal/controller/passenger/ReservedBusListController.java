/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.ReserveBus;
import java.io.IOException;
//import com.busterminal.views.passenger.*;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ReservedBusListController implements Initializable {

    @FXML
    private TableView<ReserveBus> tableViewReserveBus;
    @FXML
    private TableColumn<ReserveBus, String> busTypeCol;
    @FXML
    private TableColumn<ReserveBus, LocalDate> dateCol;
    @FXML
    private TableColumn<ReserveBus, String> acCol;
    @FXML
    private TableColumn<ReserveBus, String> cityCol;
    @FXML
    private TableColumn<ReserveBus, Integer> durationCol;

    private ObservableList<ReserveBus> reserveBusList=FXCollections.observableArrayList();
    private ObservableList<ReserveBus> helperList= FXCollections.observableArrayList();

    public ObservableList<ReserveBus> getHelperList() {
        return helperList;
    }

    public void setHelperList(ObservableList<ReserveBus> helperList) {
        this.helperList = helperList;
       
        tableViewReserveBus.setItems(this.helperList);
       
        
    }
    private String bus;
    private String city;
    private int day;
    private LocalDate date;
    private String acType;

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        
    }

    public String getAcType() {
        return acType;
    }

    public void setAcType(String acType) {
        this.acType = acType;
    }
   
    public ObservableList<ReserveBus> getReserveBusList() {
        return reserveBusList;
    }

    public void setReserveBusList(ObservableList<ReserveBus> reserveBusList) {
        this.reserveBusList = reserveBusList;
        
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
       busTypeCol.setCellValueFactory(new PropertyValueFactory<>("busType"));
       dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
       acCol.setCellValueFactory(new PropertyValueFactory<>("acType"));
       cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
       durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
       //helperList= FXCollections.observableArrayList();
       //reserveBusList =FXCollections.observableArrayList();
       
         
                                     
       
    }    

    @FXML
    private void reserveConfirmAlert(ActionEvent event) throws IOException {
        
     
        Parent fileChooserViewParent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/passenger/ReserveBusPassengerInfo.fxml"));
        Scene fileChooserViewScene = new Scene(fileChooserViewParent);
        //Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage newWindow  = new Stage();
        
        newWindow.setTitle("Enter Details");
        newWindow.setScene(fileChooserViewScene);
        newWindow.show();
        }
        
     
   
    
     
        
    @FXML
    private void switchToReserveBusSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/Reserve.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }
    
}
