package com.busterminal.controller.Administrator;

import com.busterminal.model.BusReservation;
import com.busterminal.model.Database;
import com.busterminal.model.Reservation;
import com.busterminal.utilityclass.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        data = FXCollections.observableArrayList();
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        fareColumn.setCellValueFactory(new PropertyValueFactory<>("fare"));
        
        ObservableList<Reservation> re = getInstance();
        
        int saturday=0,sunday=0,monday=0,tuesday=0,wednesday=0,thursday=0;
        
        if (re != null) {
            for (Reservation r : re) {
                System.out.print("fare" + r.getReserveBus().getFare());
                BusReservation br = new BusReservation(r.getReserveBus().getReserveId(), r.getPassengerName(),
                        r.getReserveBus().getBusType(), r.getReserveBus().getDate(), r.getReserveBus().getFare());
                data.add(br);
                
                switch(Validator.getDayOfWeek(r.getReserveBus().getDate())){
                    case("Monday"):
                        monday+=r.getReserveBus().getFare();
                        break;
                    case("Saturday"):
                        saturday+=r.getReserveBus().getFare();
                        break;
                    case("Sunday"):
                        sunday+=r.getReserveBus().getFare();
                        break;
                    case("Tuesday"):
                        tuesday+=r.getReserveBus().getFare();
                        break;
                    case("Wednesday"):
                        wednesday+=r.getReserveBus().getFare();
                        System.out.print("total:"+wednesday);
                        break;
                    case("Thursday"):
                        thursday+=r.getReserveBus().getFare();
                        break;  
                }

            }

        }
        System.out.print("total:"+wednesday);

        busReservationTable.getItems().addAll(data);
        
        
        

        // pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Sturday", saturday),
                new PieChart.Data("Sunday", sunday),
                new PieChart.Data("Monday", monday),
                new PieChart.Data("Tuesday", tuesday),
                new PieChart.Data("Wednesday", wednesday),
                new PieChart.Data("Thursday", thursday)
        );

        weeklyPieChart.setData(pieChartData);

        // Add listener to the filter field for filtering the table by date
        filter.textProperty().addListener((observable, oldValue, newValue) -> filterTableByDate());
        //explain in feedback
    }

    private void filterTableByDate() {
        String dateFilter = filter.getText();

        ObservableList<BusReservation> filteredData = FXCollections.observableArrayList();

        for (BusReservation entry : busReservationTable.getItems()) {
            boolean dateMatches = entry.getDepartureDate().equals(dateFilter);

            if (dateMatches) {
                filteredData.add(entry);
            }
        }

        busReservationTable.setItems(filteredData);
    }

    public static ObservableList<Reservation> getInstance() {
        ObjectInputStream ois = null;
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        try {
            Reservation c;
            ois = new ObjectInputStream(new FileInputStream("ReservationList.bin"));

            while (true) {
                c = (Reservation) ois.readObject();
                list.add(c);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return list;
    }

}
