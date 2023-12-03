package com.busterminal.controller.Administrator;

import com.busterminal.model.BusStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;
import java.time.LocalDate;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowBusStatusController {

    @FXML
    private TableView<BusStatus> busStatusTable;

    @FXML
    private TableColumn<BusStatus, String> busIdColumn;

    @FXML
    private TableColumn<BusStatus, String> busNameColumn;

    @FXML
    private TableColumn<BusStatus, String> driverNameColumn;

    @FXML
    private TableColumn<BusStatus, String> fuelConsumptionColumn;

    @FXML
    private TableColumn<BusStatus, LocalDate> lastMaintenanceDateColumn;

    @FXML
    private TextField filterBusNameField;

    @FXML
    private TextField filterMaintenanceDateField;

    private final ObservableList<BusStatus> originalData = FXCollections.observableArrayList();

    public void initialize() {
        originalData.addAll(
                new BusStatus("1", "Bus 101", "Samin", "10 mpg", LocalDate.of(2022, 8, 15)),
                new BusStatus("2", "Bus 202", "Alif", "8 mpg", LocalDate.of(2022, 9, 22)),
                new BusStatus("3", "Bus 303", "Mantaka", "12 mpg", LocalDate.of(2022, 7, 5))
        /*
                fuel consumption
                
                Fuel Consumption = (Final Distance - Starting Distance) / Fuel Usage
                Fuel Consumption = (23,889 mi - 23,500 mi) / 12.5 gallons
                Fuel Consumption = 389 mi / 12.5 gallons
                Fuel Consumption = 31.1 mpg
         */
        );

        busStatusTable.setItems(originalData);
        busStatusTable.setEditable(true);

        // Initialize the table columns
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        busNameColumn.setCellValueFactory(new PropertyValueFactory<>("busName"));
        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        fuelConsumptionColumn.setCellValueFactory(new PropertyValueFactory<>("fuelConsumption"));
        lastMaintenanceDateColumn.setCellValueFactory(new PropertyValueFactory<>("lastMaintenanceDate"));

        // Enable cell editing for the 'Bus Name' column
        busNameColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        // Add listeners to the filter fields for filtering the table
        filterBusNameField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());
        filterMaintenanceDateField.textProperty().addListener((observable, oldValue, newValue) -> filterTable());

        /*
        filterBusNameField.textProperty(): Retrieves the text property of the filterBusNameField TextField.
        addListener((observable, oldValue, newValue) -> filterTable()): Adds a listener to the text property. 
        The listener is a lambda expression that takes three parameters:
        observable: The property that changed, in this case, the text property of the TextField.
        oldValue: The old value of the property (text before the change).
        newValue: The new value of the property (text after the change).
         */
    }

    private void filterTable() {
        String busNameFilter = filterBusNameField.getText().toLowerCase();
        String maintenanceDateFilter = filterMaintenanceDateField.getText().toLowerCase();

        ObservableList<BusStatus> filteredData = FXCollections.observableArrayList();

        for (BusStatus entry : originalData) {
            boolean nameMatches = entry.getBusName().toLowerCase().contains(busNameFilter);
            boolean dateMatches = entry.getLastMaintenanceDate().toString().toLowerCase().contains(maintenanceDateFilter);

            if (nameMatches && dateMatches) {
                filteredData.add(entry);
            }
        }

        busStatusTable.setItems(filteredData);
    }
}
