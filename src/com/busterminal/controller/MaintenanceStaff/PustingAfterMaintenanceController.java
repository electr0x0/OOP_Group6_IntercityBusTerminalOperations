package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.MaintenanceHistory;
import com.busterminal.model.PopUp;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PustingAfterMaintenanceController implements Initializable {

    @FXML
    private TextField busIdTF;
    @FXML
    private TextField issueResolvedTF;
    @FXML
    private TableView<MaintenanceHistory> maintenanceHistoryTable;
    @FXML
    private TableColumn<MaintenanceHistory, Integer> busIdCol;
    @FXML
    private TableColumn<MaintenanceHistory, LocalDate> lastServicingCol;
    @FXML
    private TableColumn<MaintenanceHistory, String> issueResolvedCol;

    private ObservableList<MaintenanceHistory> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        busIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastServicingCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        issueResolvedCol.setCellValueFactory(new PropertyValueFactory<>("issueResolved"));
        maintenanceHistoryTable.getItems().addAll(MaintenanceHistory.getItems());
        maintenanceHistoryTable.setVisible(true);
    }

    @FXML
    private void sendOnMouseClick(ActionEvent event) {
        if (!busIdTF.getText().equals("") && !issueResolvedTF.getText().equals("")) {
            try {
                int busId = Integer.parseInt(busIdTF.getText());
                String issueResolved = issueResolvedTF.getText();
                MaintenanceHistory mlist = new MaintenanceHistory(busId, LocalDate.now(), issueResolved, true);
                MaintenanceHistory.addItems(mlist);
                data.add(mlist);

                maintenanceHistoryTable.setItems(data);
            } catch (Exception e) {
                PopUp.showMessage("Warning", "Bus Id shoud be Integer" + "\n Please try agin with valid Bus Id");
            }
        } else {
            PopUp.showMessage("Alert", "Please Enter the busId" + "\n Enter the Resolved issue..!");
        }
        clear();

    }

    @FXML
    private void viewMaintenanceHistoryOnMouseClick(ActionEvent event) {

        ObservableList<MaintenanceHistory> mTable = maintenanceHistoryTable.getItems();
        String selectedCategory = busIdTF.getText();
        if (selectedCategory != null) {
            ObservableList<MaintenanceHistory> filteredData = FXCollections.observableArrayList();

            for (MaintenanceHistory m : mTable) {
                if (m.getId() == Integer.parseInt(selectedCategory)) {
                    filteredData.add(m);
                } else {
                    PopUp.showMessage("Information", "No available task..!");
                }
            }

            maintenanceHistoryTable.setItems(filteredData);
        } else {
            maintenanceHistoryTable.setItems(data);
        }

    }

    private void clear() {
        busIdTF.clear();
        issueResolvedTF.clear();
    }

    public void setBusIdTF(String busIdTF) {
        this.busIdTF.setText(busIdTF);
    }

    @FXML
    private void backOnMouseClick(MouseEvent event) {
        try {
            Parent root = null;
            FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml"));
            root = (Parent) someLoader.load();

            Scene someScene = new Scene(root);

            Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            someStage.setScene(someScene);
            someStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
