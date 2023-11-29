package com.busterminal.controller.Administrator;

import com.busterminal.model.BusReservation;
import com.busterminal.model.Client;
import com.busterminal.model.Database;
import com.busterminal.model.Reservation;
import java.io.File;
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
        // Initialize the TableView columns
        reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("passengerName"));
        busIdColumn.setCellValueFactory(new PropertyValueFactory<>("busId"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        fareColumn.setCellValueFactory(new PropertyValueFactory<>("fare"));
        //readbinFile();
        System.out.println(getClientList());

        /*
       ObservableList<BusReservation> reservationsData = FXCollections.observableArrayList(
                new BusReservation(2, "Jane Smith", "Bus202", LocalDate.now(), 75),
                new BusReservation(3, "Bob Johnson", "Bus303", LocalDate.now(), 60));
      
       Database.writeToBinFile("ReservationList.bin", reservationsData);
      
      
      System.out.println(Database.getInstanceBinFile("ReservationList.bin"));*/
        /*ObservableList<Reservation> re = Database.getInstanceBinFile("ReservationList.bin");
        if (re != null) {
            for (Reservation r : re) {
                System.out.print("fare" + r.getReserveBus().getFare());
                BusReservation br = new BusReservation(r.getReserveBus().getReserveId(), r.getPassengerName(),
                        r.getReserveBus().getBusType(), r.getReserveBus().getDate(), r.getReserveBus().getFare());
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

    private void readbinFile() {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("ReservationList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Reservation f2;
            try {
                //outputTextArea.setText("");
                while (true) {
                    //System.out.println("Printing objects.");
                    f2 = (Reservation) ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(f2);
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch (Exception e) {
                // handling code
            }//nested catch
            //outputTextArea.appendText("All objects are loaded successfully...\n");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {

                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }

    }

    public static ObservableList<Reservation> getClientList() {
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
