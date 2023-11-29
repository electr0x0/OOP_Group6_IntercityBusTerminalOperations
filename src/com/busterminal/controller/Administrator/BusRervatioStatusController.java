package com.busterminal.controller.Administrator;
import com.busterminal.model.BusReservation;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class BusRervatioStatusController {

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

    @FXML
    private void initialize() {
        // Sample data (replace with your actual data)
       /* busReservationTable.getItems().addAll(
                new BusReservation("1", "John Doe", "Bus101", "2023-12-01", "50"),
                new BusReservation("2", "Jane Smith", "Bus202", "2023-12-02", "75"),
                new BusReservation("3", "Bob Johnson", "Bus303", "2023-12-03", "60")
        );*/
        
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        fareColumn.setCellValueFactory(new PropertyValueFactory<>("fare"));
        
    }
}