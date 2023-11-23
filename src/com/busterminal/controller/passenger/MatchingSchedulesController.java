/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.passenger;

import com.busterminal.model.Bus;
import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.DummyClassForTableViewSchedule;
import com.busterminal.model.SceneSwicth;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class MatchingSchedulesController implements Initializable {

    @FXML
    private AnchorPane anchorpane4;

    @FXML
    private TableView<DummyClassForTableViewSchedule > tableViewAvailableBuses;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, Bus> busCol;   
    
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, String> sourceCol;
    @FXML
    private TableColumn< DummyClassForTableViewSchedule, String> destinationCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, String> fareCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule, LocalDate> dateCol;
    @FXML
    private TableColumn<DummyClassForTableViewSchedule,String > timeCol;
    
    private ObservableList<DummyClassForTableViewSchedule> tableviewList = FXCollections.observableArrayList();
    
    ArrayList<BusTrip> b1 = new ArrayList();

    public ObservableList<DummyClassForTableViewSchedule> getTableviewList() {
        return tableviewList;
    }

    public void setTableviewList(ObservableList<DummyClassForTableViewSchedule> tableviewList) {
        this.tableviewList = tableviewList;
        tableViewAvailableBuses.setItems(this.tableviewList);
        
    }
    ArrayList<BusTripSchedule> b2 = new ArrayList();

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busCol.setCellValueFactory(new PropertyValueFactory<>("bus"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        sourceCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        fareCol.setCellValueFactory(new PropertyValueFactory<>("adultFare"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("scheduleDate"));
        
        //RelationshipDatabaseClass.loadFromFile();
        //b2= RelationshipDatabaseClass.getInstance().getAllAvailableTripSchedules();
       // b1= RelationshipDatabaseClass.getInstance().getAllTripList();
        
        
        //for(BusTrip x : b1){
            //for(BusTripSchedule y : b2){
               // if (x.getScheduleId() == y.getScheduleId()){
                   // DummyClassForTableViewSchedule d = new DummyClassForTableViewSchedule(x.getBus(),x.getSource(), x.getDestination(),x.getAdultFare(),x.getTimeSlot(),y.getScheduleDate());
                    
                   // tableviewList.add(d);
                   // tableViewAvailableBuses.setItems(tableviewList);
                //}
           // }
            
        //}
        
        
        
    }    

    @FXML
    private void switchToSchedule(ActionEvent event) throws IOException {
        new SceneSwicth(anchorpane4,"/com/busterminal/views/passenger/Schedule.fxml");
               
    }

        
        
   

    @FXML
    private void switchToTicketbuyScene(ActionEvent event) throws IOException {
        
       DummyClassForTableViewSchedule selectedBus = tableViewAvailableBuses.getSelectionModel().getSelectedItem();

    if (selectedBus != null) {
        
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Do you want to confirm ?");
        confirmationAlert.setContentText("Bus Type: " + selectedBus.getBus().getBusType()+
                "\nFare: " + selectedBus.getAdultFare());

        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            //handleSelectedRow(selectedBus);
             Bus bus = selectedBus.getBus();
        //String bustype = selectedBus.getBus().getBusType();
            String fare = Integer.toString(selectedBus.getAdultFare());
            String source = selectedBus.getSource();
            String destination = selectedBus.getDestination();
            String time = selectedBus.getTimeSlot();
            String date = selectedBus.getScheduleDate().toString();
            
            Parent root = null;
            FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/TicketDetails.fxml"));
            root = (Parent) someLoader.load();
        
            Scene someScene = new Scene (root);
             TicketDetailsController p = someLoader.getController();
            p.setValues(source,destination,time,date,bus,fare);
        
        
            Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
            someStage.setScene(someScene);
            someStage.show();
       
        }
    } else {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Bus Selected");
        alert.setHeaderText("Please select a bus before confirming.");
        alert.showAndWait();
    }
        
        
        
        
        
        
    }
private void handleSelectedRow(DummyClassForTableViewSchedule selectedBus) {
        
    
        Bus bus = selectedBus.getBus();
        //String bustype = selectedBus.getBus().getBusType();
        String fare = Integer.toString(selectedBus.getAdultFare());
        String source = selectedBus.getSource();
        String destination = selectedBus.getDestination();
        String time = selectedBus.getTimeSlot();
        String date = selectedBus.getScheduleDate().toString();
       
        

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/passenger/TicketDetails.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ReservedBusListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TicketDetailsController p = loader.getController();
        p.setValues(source,destination,time,date,bus,fare); 
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        
    } 



}