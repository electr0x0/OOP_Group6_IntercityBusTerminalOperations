package com.busterminal.controller.MaintenanceStaff;
import com.busterminal.model.Database;
import com.busterminal.model.MaintenanceTask;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class CheckMaintenanceTaskController implements Initializable{

    @FXML
    private TableView<MaintenanceTask> maintenanceTable;

    @FXML
    private TableColumn<MaintenanceTask, String> driverIdCol;
    @FXML
    private TableColumn<MaintenanceTask, String> busIdCol;
    @FXML
    private TableColumn<MaintenanceTask, String> lastMaintenanceDateCol;

    @FXML
    private TableColumn<MaintenanceTask, String> requestDateCol;

    @FXML
    private TableColumn<MaintenanceTask, String> miantenanceTypeCol;
    @FXML
    private Label timeLabel;
    @FXML
    private Label totalNoOfTecnehianLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.print(Database.getInstanceBinFile("MaintenanceList.bin"));
        driverIdCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("busId"));
        miantenanceTypeCol.setCellValueFactory(new PropertyValueFactory<>("maintenanceType"));
        lastMaintenanceDateCol.setCellValueFactory(new PropertyValueFactory<>("lastMaintenanceDate"));
        requestDateCol.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        MaintenanceTask task1 = new MaintenanceTask("S123", "John", "D123", "B456", "Oil Change", "2022-01-01", "2022-01-15", "Check engine oil.");
        MaintenanceTask task2 = new MaintenanceTask("S456", "Jane", "D456", "B789", "Brake Inspection", "2022-02-01", "2022-02-15", "Inspect brake system.");
        
        maintenanceTable.getItems().add(task1);
        maintenanceTable.getItems().add(task2);
        
        
        // for show time
        updateDateTime();
        totalNoOfTecnehianLabel.setText(Integer.toString(MaintenanceTask.totalstaff()));
        
    }
  
    
    
    @FXML
    private void sendMaintenanceOnMouseClick() {
        // Handle logic to delete selected task and send it for maintenance
        MaintenanceTask selectedTask = maintenanceTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            
            maintenanceTable.getItems().remove(selectedTask);
        }
    }

    @FXML
    private void viewProblemDescriptionOnMouseClick() {
        // Handle logic to show the description for the selected maintenance request
        MaintenanceTask selectedTask = maintenanceTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {
            String description = selectedTask.getDescription();
            System.out.println("Problem Description: " + description);
    }
}
    
    private void updateDateTime() {
        // Get current date and time
        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE"); // EEEE for full day name

        // Set text on labels
        timeLabel.setText("Time:    " + timeFormat.format(now) + "\nDate:     " + dateFormat.format(now)+"\nDay:       " + dayFormat.format(now));
    }
}