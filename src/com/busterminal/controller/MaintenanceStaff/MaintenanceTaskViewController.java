package com.busterminal.controller.MaintenanceStaff;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MaintenanceTaskViewController implements Initializable {

    @FXML
    private TextArea vewTextArea;
    @FXML
    private Label driverIdLabel;
    @FXML
    private Label busIdLabel;
    @FXML
    private Label maintenanceTypeLabel;
    @FXML
    private Label requestLabel;
    @FXML
    private AnchorPane anchorPaneShow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setDescription(String description) {
        vewTextArea.appendText(description);
    }

    public void setDriverIdLabel(String driverIdLabel) {
        this.driverIdLabel.setText(driverIdLabel);
    }

    public void setBusIdLabel(String busIdLabel) {
        this.busIdLabel.setText(busIdLabel);
    }

    public void setMaintenanceTypeLabel(String maintenanceTypeLabel) {
        this.maintenanceTypeLabel.setText(maintenanceTypeLabel);
    }

    public void setRequestLabel(LocalDate requestLabel) {
        this.requestLabel.setText(requestLabel.toString());
    }

    @FXML
    private void goToLoginOnMouseClick(MouseEvent event) {

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
