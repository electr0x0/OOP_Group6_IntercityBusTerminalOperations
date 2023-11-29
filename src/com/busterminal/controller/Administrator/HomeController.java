package com.busterminal.controller.Administrator;

import com.busterminal.model.AppendableObjectOutputStream;
import com.busterminal.model.Database;
import com.busterminal.model.Feedback;
import com.busterminal.model.Parts;
import com.busterminal.model.Reservation;
import com.busterminal.model.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author DELL
 */
public class HomeController implements Initializable {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    @FXML
    private AnchorPane homeAchorPane;
    @FXML
    private Label dailySaleReportLabel;
    @FXML
    private TextArea bulletinMassageTA;
    @FXML
    private TextField toTF;
    @FXML
    private TextField ccTF;
    @FXML
    private TextField subjectTF;
    @FXML
    private Button sendButton;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart<String, Number> weeklySaleBarChart;

    private ObservableList<Ticket> ticket = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monthlySaleBarChar();
        System.out.println(getItems());

    }

    @FXML
    private void sendMassageOnMouseClick(ActionEvent event) {

    }

    private void monthlySaleBarChar() {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("SAT", 20000));
        series.getData().add(new XYChart.Data<String, Number>("SUN", 30000));
        series.getData().add(new XYChart.Data<String, Number>("MON", 25000));
        series.getData().add(new XYChart.Data<String, Number>("TUES", 20000));
        series.getData().add(new XYChart.Data<String, Number>("WED", 40000));
        series.getData().add(new XYChart.Data<String, Number>("THU", 35000));
        series.setName("Weekly Sale");
        weeklySaleBarChart.getData().add(series);

    }

    private void dailyReport() {
        ticket = Database.getInstanceBinFile("Feedback.bin");
        System.out.println(ticket.size());
    }

    public static void addItems(Parts e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("PartsList.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(Parts.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Parts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ObservableList<Parts> getItems() {
        ObjectInputStream ois = null;
        ObservableList<Parts> list = FXCollections.observableArrayList();
        try {
            Parts e;
            ois = new ObjectInputStream(new FileInputStream("TicketList.bin"));

            while (true) {
                e = (Parts) ois.readObject();
                list.add(e);
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
