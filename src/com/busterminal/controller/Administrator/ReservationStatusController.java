package com.busterminal.controller.Administrator;

import com.busterminal.model.BusReservation;
import com.busterminal.model.Database;
import com.busterminal.model.Reservation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationStatusController implements Initializable {

    @FXML
    private TextField filter;

    @FXML
    private PieChart weeklyPieChart;

    @FXML
    private TableView<BusReservation> busReservationTable;

    @FXML
    private TableColumn<BusReservation, String> reservationIdColumn;

    @FXML
    private TableColumn<BusReservation, String> passengerNameColumn;

    @FXML
    private TableColumn<BusReservation, String> busIdColumn;

    @FXML
    private TableColumn<BusReservation, String> departureDateColumn;

    @FXML
    private TableColumn<BusReservation, String> fareColumn;
    
    private ObservableList<BusReservation> data;

     @Override
    public void initialize(URL location, ResourceBundle resources) {
         System.out.println(Database.getInstanceBinFile("ReservationList.bin"));
        data= FXCollections.observableArrayList();
        // Initialize the TableView columns
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        fareColumn.setCellValueFactory(new PropertyValueFactory<>("fare"));

      /*
       ObservableList<BusReservation> reservationsData = FXCollections.observableArrayList(
                new BusReservation(2, "Jane Smith", "Bus202", LocalDate.now(), 75),
                new BusReservation(3, "Bob Johnson", "Bus303", LocalDate.now(), 60));
      
       Database.writeToBinFile("ReservationList.bin", reservationsData);
      
      
      System.out.println(Database.getInstanceBinFile("ReservationList.bin"));*/
       
       /*ObservableList<Reservation> re = Database.getInstanceBinFile("ReservationList.bin");
       if (re!=null){
           for (Reservation r: re){
               System.out.print("fare" + r.getReserveBus().getFare());
               BusReservation br = new BusReservation(r.getReserveBus().getReserveId() ,r.getPassengerName(),
                       r.getReserveBus().getBusType(),r.getReserveBus().getDate(), r.getReserveBus().getFare());
               data.add(br);
               
           }
           
       }
       */
        busReservationTable.getItems().addAll(data);

        // pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Sturday", 15),
                new PieChart.Data("Sunday", 25),
                new PieChart.Data("Monday", 30),
                new PieChart.Data("Tuesday", 20),
                new PieChart.Data("Wednesday", 25),
                new PieChart.Data("Thursday", 15)
        );

        weeklyPieChart.setData(pieChartData);

        // Add listener to the filter field for filtering the table by date
        filter.textProperty().addListener((observable, oldValue, newValue) -> filterTableByDate());
        //explain in feedback
    }


    private void filterTableByDate() {
       /* String dateFilter = filter.getText();

        ObservableList<BusReservation> filteredData = FXCollections.observableArrayList();
        

        for (BusReservation entry : busReservationTable.getItems()) {
            boolean dateMatches = entry.getDepartureDate().equals(dateFilter);

            if (dateMatches) {
                filteredData.add(entry);
            }
        }

        busReservationTable.setItems(filteredData);*/
    }
  
}