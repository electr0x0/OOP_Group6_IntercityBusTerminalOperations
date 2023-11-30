package com.busterminal.controller.Administrator;

import com.busterminal.model.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class ShowTransitionController {

    @FXML
    private TextField filterBusNameField;

    @FXML
    private TextField filterTransitionDateField;

    @FXML
    private TableView<Transition> transitionTable;

    @FXML
    private TableColumn<Transition, String> busIdColumn;

    @FXML
    private TableColumn<Transition, String> busNameColumn;

    @FXML
    private TableColumn<Transition, String> transitionDateColumn;

    @FXML
    private TableColumn<Transition, String> departureTimeColumn;

    @FXML
    private TableColumn<Transition, String> destinationColumn;

    @FXML
    private TableColumn<Transition, String> tkColumn;

    private LineChart<String, Number> lineChart;

    public void initialize() {
        // Initialize the TableView columns
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        busNameColumn.setCellValueFactory(new PropertyValueFactory<>("busName"));
        transitionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transitionDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        tkColumn.setCellValueFactory(new PropertyValueFactory<>("tk"));

        // Enable cell editing for the 'Bus Name' column
        busNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Sample data initialization (replace with your actual data)
        ObservableList<Transition> transitionsData = FXCollections.observableArrayList(
                new Transition("1", "Bus 101", "2022-11-15", "10:00 AM", "Airport", "50"),
                new Transition("2", "Bus 202", "2022-11-16", "11:30 AM", "Shamoli", "75"),
                new Transition("3", "Bus 303", "2022-11-17", "09:45 AM", "Josshore", "60")
                // Add more entries as needed
        );

        transitionTable.setItems(transitionsData);

        // Initialize LineChart
        /*lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        series.getData().add(new XYChart.Data<>("Day 1", 50));
        series.getData().add(new XYChart.Data<>("Day 2", 75));
        series.getData().add(new XYChart.Data<>("Day 3", 60));
        lineChart.getData().add(series);*/
        

        // Add listeners to the filter fields for filtering the table
        filterBusNameField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());
        filterTransitionDateField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());
    }

    private void filterTable() {
        String busNameFilter = filterBusNameField.getText().toLowerCase();
        String transitionDateFilter = filterTransitionDateField.getText().toLowerCase();

        ObservableList<Transition> filteredData = FXCollections.observableArrayList();

        for (Transition entry : transitionTable.getItems()) {
            boolean nameMatches = entry.getBusName().toLowerCase().contains(busNameFilter);
            boolean dateMatches = entry.getTransitionDate().toLowerCase().contains(transitionDateFilter);

            if (nameMatches && dateMatches) {
                filteredData.add(entry);
            }
        }

        transitionTable.setItems(filteredData);
    }
}
