package com.busterminal.controller.Administrator;

import java.net.URL;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monthlySaleBarChar();

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
}
