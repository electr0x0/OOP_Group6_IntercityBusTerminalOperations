package com.busterminal.controller.Administrator;

import com.busterminal.model.Administrator;
import com.busterminal.model.BulletinMassage;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.PopUp;
import com.busterminal.model.Ticket;
import com.busterminal.model.User;
import com.busterminal.utilityclass.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class HomeController implements Initializable {

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
    private ObservableList<Ticket> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = getItems();
        int totalDaySell = 0;
        LocalDate currentDate = LocalDate.now();
        for (Ticket t : data) {
            if (currentDate.toString().equals(t.getPurchaseDate())) {
                totalDaySell += t.getDummy().getAdultFare();
            }

        }
        dailySaleReportLabel.setText("TODAY SALES " + "[Date:" + currentDate.toString() + "] " + "BDT " + totalDaySell);

        int saturday = 0, sunday = 0, monday = 0, tuesday = 0, wednesday = 0, thursday = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (data != null) {
            for (Ticket r : data) {
                switch (Validator.getDayOfWeek(LocalDate.parse(r.getPurchaseDate(), formatter))) {
                    case ("Monday"):
                        monday += r.getDummy().getAdultFare();
                        break;
                    case ("Saturday"):
                        saturday += r.getDummy().getAdultFare();
                        break;
                    case ("Sunday"):
                        sunday += r.getDummy().getAdultFare();
                        break;
                    case ("Tuesday"):
                        tuesday += r.getDummy().getAdultFare();
                        break;
                    case ("Wednesday"):
                        wednesday += r.getDummy().getAdultFare();
                        System.out.print("total:" + wednesday);
                        break;
                    case ("Thursday"):
                        thursday += r.getDummy().getAdultFare();
                        break;
                }

            }

        }

        // BarChart
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("SAT", saturday));
        series.getData().add(new XYChart.Data<String, Number>("SUN", sunday));
        series.getData().add(new XYChart.Data<String, Number>("MON", monday));
        series.getData().add(new XYChart.Data<String, Number>("TUES", tuesday));
        series.getData().add(new XYChart.Data<String, Number>("WED", wednesday));
        series.getData().add(new XYChart.Data<String, Number>("THU", thursday));
        series.setName("Weekly Sale");
        weeklySaleBarChart.getData().add(series);

    }

    @FXML
    private void sendMassageOnMouseClick(ActionEvent event) {
        
        String to = toTF.getText();
        String cc = ccTF.getText();
        String subject = subjectTF.getText();
        String massage = bulletinMassageTA.getText();
        
        if (!User.verifyEmailSuffix(to)){
            PopUp.showMessage("Alert", "Invalid Email....!");
            return;
        }else{
            if(subject.equals("")){
                PopUp.showMessage("Alert", "Please write email subject..!");
                return;
            }
            
        }
        
        // load all employee in employeelist
        ObservableList<DummyEmployee> employeelist = Administrator.getEmployeeList();
        if (to.equals("alluser@gmail.com") || cc.equals("alluser@gmail.com")) {
            for (DummyEmployee d : employeelist) {
                String alluser = d.getEmail();
                System.out.println(alluser);
                String ccc = "";
                BulletinMassage bm = new BulletinMassage(alluser, ccc, subject, LocalDate.now(), massage, true);
                System.out.println(bm.toString());
                BulletinMassage.sendMassageToUser(bm);
            }
        }else{
             BulletinMassage bm = new BulletinMassage(to, cc, subject, LocalDate.now(), massage, true);
        BulletinMassage.sendMassageToUser(bm);
            
        }
        PopUp.showMessage(to + "\n" + cc, "Massage Successfully sent..!");
        clear();

    }

    public static ObservableList<Ticket> getItems() {
        ObjectInputStream ois = null;
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        try {
            Ticket e;
            ois = new ObjectInputStream(new FileInputStream("TicketList.bin"));

            while (true) {
                e = (Ticket) ois.readObject();
                list.add(e);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private void clear() {
        toTF.clear();
        ccTF.clear();
        subjectTF.clear();
        bulletinMassageTA.clear();

    }

}
