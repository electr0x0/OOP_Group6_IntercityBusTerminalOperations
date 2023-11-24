package com.busterminal.controller.Administrator;

import com.busterminal.model.BusReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ReservationStatusController {
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

    public void initialize() {
        // Initialize the TableView columns
        reservationIdColumn.setCellValueFactory(cellData -> cellData.getValue().reservationIdProperty());
        passengerNameColumn.setCellValueFactory(cellData -> cellData.getValue().passengerNameProperty());
        busIdColumn.setCellValueFactory(cellData -> cellData.getValue().busIdProperty());
        departureDateColumn.setCellValueFactory(cellData -> cellData.getValue().departureDateProperty());
        fareColumn.setCellValueFactory(cellData -> cellData.getValue().fareProperty());

        
        ObservableList<BusReservation> reservationsData = FXCollections.observableArrayList(
                new BusReservation("1", "John Doe", "Bus101", "2022-12-01", "50"),
                new BusReservation("2", "Jane Smith", "Bus202", "2022-12-02", "75"),
                new BusReservation("3", "Bob Johnson", "Bus303", "2022-12-03", "60")
                // Add more entries as needed
        );

        busReservationTable.setItems(reservationsData);
        
        // pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Sturday",15),
                new PieChart.Data("Sunday",25),
                new PieChart.Data("Monday", 30),
                new PieChart.Data("Tuesday", 20),
                new PieChart.Data("Wednesday", 25),
                new PieChart.Data("Thursday", 15)
        );

        weeklyPieChart.setData(pieChartData);

        // Add listener to the filter field for filtering the table by date
        filter.textProperty().addListener((observable, oldValue, newValue) -> filterTableByDate());
    }

    private void filterTableByDate() {
        String dateFilter = filter.getText();

        ObservableList<BusReservation> filteredData = FXCollections.observableArrayList();

        for (BusReservation entry : busReservationTable.getItems()) {
            boolean dateMatches = entry.getDepartureDate().contains(dateFilter);

            if (dateMatches) {
                filteredData.add(entry);
            }
        }

        busReservationTable.setItems(filteredData);
    }
}
