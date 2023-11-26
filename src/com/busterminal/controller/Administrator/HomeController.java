package com.busterminal.controller.Administrator;

import com.busterminal.model.Database;
import com.busterminal.model.Feedback;
import com.busterminal.model.Reservation;
import com.busterminal.model.Ticket;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
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
        readFile();
       
        
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
    
    private void readFile() {
        
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("ReservationList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Reservation f2;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    f2= (Reservation)ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(f2.toString());
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch(Exception e){
                // handling code
            }//nested catch     
            //outputTextArea.appendText("All objects are loaded successfully...\n");            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }           
    
    }

   
}


