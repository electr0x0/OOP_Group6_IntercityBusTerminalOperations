package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.AppendableObjectOutputStream;
import com.busterminal.model.Database;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.Maintenance;
import com.busterminal.model.MaintenanceStaff;
import com.busterminal.model.MaintenanceTask;
import com.busterminal.model.Parts;
import com.busterminal.model.PopUp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CheckMaintenanceTaskController implements Initializable {

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
    @FXML
    private Label availableParts;
    @FXML
    private DatePicker filterCB;

    private ObservableList<Maintenance> mt;
    private ObservableList<MaintenanceTask> mtt;
    @FXML
    private Label totalTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        System.out.println(getItems().size());
        mt = FXCollections.observableArrayList();
        mtt = FXCollections.observableArrayList();

        driverIdCol.setCellValueFactory(new PropertyValueFactory<>("driverId"));
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("busId"));
        miantenanceTypeCol.setCellValueFactory(new PropertyValueFactory<>("maintenanceType"));
        lastMaintenanceDateCol.setCellValueFactory(new PropertyValueFactory<>("lastMaintenanceDate"));
        requestDateCol.setCellValueFactory(new PropertyValueFactory<>("requestDate"));

        mt = getItems();
        for (Maintenance m : mt) {
            MaintenanceTask task = new MaintenanceTask(m.getDetails(), m.getDriverId(), m.getBusId(), m.getMaintenanceType(), LocalDate.now(), m.getReqDate());
            mtt.add(task);
            
        }
        
        maintenanceTable.setItems(mtt);
       
        // for show time
        updateDateTime();

        totalNoOfTecnehianLabel.setText(Integer.toString(MaintenanceStaff.totalstaff()));

        availableParts.setText((Integer.toString(Database.getInstanceBinFile("PartsList.bin").size())));
        totalTask.setText(Integer.toString(getItems().size()));

    

    }

    @FXML
    private void sendMaintenanceOnMouseClick(ActionEvent event) throws IOException {
        // Handle logic to delete selected task and send it for maintenance
        MaintenanceTask selectedTask = maintenanceTable.getSelectionModel().getSelectedItem();

        if (selectedTask != null) {

            maintenanceTable.getItems().remove(selectedTask);
        }
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/MaintenanceStaff/pustingAfterMaintenance.fxml"));
        root = (Parent) someLoader.load();
        PustingAfterMaintenanceController pamc = someLoader.getController();
        pamc.setBusIdTF(selectedTask.getBusId());
        Scene someScene = new Scene (root);
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
        deleteItems(selectedTask);
        
        
        
        
    }

    @FXML
    private void viewProblemDescriptionOnMouseClick(ActionEvent event) throws IOException {
        // Handle logic to show the description for the selected maintenance request
        MaintenanceTask selectedTask = maintenanceTable.getSelectionModel().getSelectedItem();
        String description = selectedTask.getDescription();
        

        if (selectedTask != null) {
            System.out.println("Problem Description: " + description);
        }
        
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/MaintenanceStaff/maintenanceTaskView.fxml"));
        root = (Parent) someLoader.load();
        MaintenanceTaskViewController mtv = someLoader.getController();
        mtv.setDescription(description);
        mtv.setBusIdLabel(selectedTask.getBusId());
        mtv.setMaintenanceTypeLabel(selectedTask.getMaintenanceType());
        mtv.setDriverIdLabel(selectedTask.getBusId());
        mtv.setRequestLabel(selectedTask.getRequestDate());
        Scene someScene = new Scene (root);
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
        
    }

    private void updateDateTime() {
        // Get current date and time
        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE"); // EEEE for full day name

        // Set text on labels
        timeLabel.setText("Time:    " + timeFormat.format(now) + "\nDate:     " + dateFormat.format(now) + "\nDay:       " + dayFormat.format(now));
    }

    @FXML
    private void searchButtonOnMouseClick(ActionEvent event) {
        ObservableList<MaintenanceTask> mTable = maintenanceTable.getItems();
        LocalDate selectedCategory = filterCB.getValue();
        if (selectedCategory != null) {
            ObservableList<MaintenanceTask> filteredData = FXCollections.observableArrayList();

            for (MaintenanceTask m : mTable) {
                if (m.getRequestDate().equals(selectedCategory)) {
                    filteredData.add(m);
                } else {
                    PopUp.showMessage("Information", "No available task..!");
                }
            }

            maintenanceTable.setItems(filteredData);
        } else {
            maintenanceTable.setItems(mtt);
        }
    }

    private static void addItems(Maintenance e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("MaintenanceList.bin");
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

    private static ObservableList<Maintenance> getItems() {
        ObjectInputStream ois = null;
        ObservableList<Maintenance> list = FXCollections.observableArrayList();
        try {
            Maintenance e;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceList.bin"));

            while (true) {
                e = (Maintenance) ois.readObject();
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
    
    public static void deleteItems(MaintenanceTask p) {
        // create a arraylist for storing all intance from bin file
        ArrayList<Maintenance> tasklist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            Maintenance c;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceList.bin"));

            while (true) {
                c = (Maintenance) ois.readObject();
                if (!(c.getBusId() == p.getBusId())) {
                    tasklist.add(c);
                }
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

        // get file path
        File file = new File("MaintenanceList.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("MaintenanceList.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for (Maintenance e : tasklist) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }
            // System.out.println(employeelist);
        }

    }

}
