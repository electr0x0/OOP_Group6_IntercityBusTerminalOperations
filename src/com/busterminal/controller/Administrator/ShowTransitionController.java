package com.busterminal.controller.Administrator;

import com.busterminal.model.Ticket;
import com.busterminal.model.Transition;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ShowTransitionController implements Initializable {

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

    @FXML
    private LineChart<String, Number> lineChart;

    private ObservableList<Ticket> data;

    private ObservableList<Ticket> filterByCurrentDateList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                new Transition(1, "Bus 101", LocalDate.now(), "10:00 AM", "Airport", 5000),
                new Transition(2, "Bus 202", LocalDate.now(), "11:30 AM", "Shamoli", 7500),
                new Transition(3, "Bus 303", LocalDate.now(), "09:45 AM", "Josshore", 6000)
                // Add more entries as needed
        );

        transitionTable.setItems(transitionsData);

        // Initialize LineChart
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<String,Number>();
        series.getData().add(new XYChart.Data<>("Day 1", 5000));
        series.getData().add(new XYChart.Data<>("Day 2", 7500));
        series.getData().add(new XYChart.Data<>("Day 3", 6000));
        lineChart.getData().add(series);
        

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
            boolean dateMatches = entry.getTransitionDate().toString().toLowerCase().contains(transitionDateFilter);

            if (nameMatches && dateMatches) {
                filteredData.add(entry);
            }
        }

        transitionTable.setItems(filteredData);
        
    }}
        
        
        /*
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
                new Transition(1, "Bus 101", LocalDate.now(), "10:pm", "Airport", 50),
                new Transition(2, "Bus 202", LocalDate.now(), "11:30 AM", "Shamoli", 75),
                new Transition(3, "Bus 303", LocalDate.now(), "09:45 AM", "Josshore", 60)
        // Add more entries as needed
        );

        transitionTable.setItems(transitionsData);

        filterByCurrentDateList = FXCollections.observableArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        data = getItems();
        for (Ticket k: data){
            Transition transition = new Transition(Integer.parseInt(k.getDummy().getBusId()), k.getDummy().getManufacture(),
                    LocalDate.parse(k.getPurchaseDate(), formatter), k.getDummy().getTimeSlot(),
                    k.getDummy().getDestination(), k.getDummy().getAdultFare());
            transitionTable.getItems().add(transition);
        
        }

        LocalDate currentDate = LocalDate.now();
        for (Ticket t : data) {
            if (currentDate.toString().equals(t.getPurchaseDate())) {
                filterByCurrentDateList.add(t);
            }

            int ba = 0, bb = 0, bc = 0, bd = 0, be = 0, bf = 0;
            if (data != null) {
                for (Ticket r : filterByCurrentDateList) {
                    switch (r.getDummy().getManufacture()) {
                        case ("Bus A"):
                            ba += r.getDummy().getAdultFare();
                            break;
                        case ("Bus B"):
                            bb += r.getDummy().getAdultFare();
                            break;
                        case ("Bus C"):
                            bc += r.getDummy().getAdultFare();
                            break;
                        case ("Bus D"):
                            bd += r.getDummy().getAdultFare();
                            break;
                        case ("Bus E"):
                            be += r.getDummy().getAdultFare();
                            break;
                        case ("Bus F"):
                            bf += r.getDummy().getAdultFare();
                            break;
                    }

                }

            }

            // Initialize LineChart
            lineChart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.getData().add(new XYChart.Data<>("Bus A", ba));
            series.getData().add(new XYChart.Data<>("Bus B", bb));
            series.getData().add(new XYChart.Data<>("Bus C", bc));
            series.getData().add(new XYChart.Data<>("Bus D", bd));
            series.getData().add(new XYChart.Data<>("Bus E", be));
            series.getData().add(new XYChart.Data<>("Bus F", bf));
            lineChart.getData().add(series);
            // Add listeners to the filter fields for filtering the table
            filterBusNameField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());
            filterTransitionDateField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());
        }

    }

    private void filterTable() {
        String busNameFilter = filterBusNameField.getText().toLowerCase();
        String transitionDateFilter = filterTransitionDateField.getText().toLowerCase();

        ObservableList<Transition> filteredData = FXCollections.observableArrayList();

        for (Transition entry : transitionTable.getItems()) {
            boolean nameMatches = entry.getBusName().toLowerCase().contains(busNameFilter);
            boolean dateMatches = entry.getTransitionDate().toString().toLowerCase().contains(transitionDateFilter);

            if (nameMatches && dateMatches) {
                filteredData.add(entry);
            }
        }

        transitionTable.setItems(filteredData);*/

