package com.busterminal.controller.MaintenanceStaff;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MaintenanceStaffController implements Initializable {

    @FXML
    private AnchorPane anchorPaneShow;

    @FXML
    private Button logoutButton1;
    @FXML
    private HBox root;
    @FXML
    private AnchorPane slide_AnchorPane;
    @FXML
    private Pane inner_Pane;
    @FXML
    private Button resignButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/CheckMaintenanceTask.fxml");
    }


    @FXML
    private void historyAccessOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/pustingAfterMaintenance.fxml");
    }

    @FXML
    private void safetyGuidelinesOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/GuideLine.fxml");
    }

    @FXML
    private void addPartsOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/AddParts.fxml");
    }

    @FXML
    private void LogOutOnMouseClick(ActionEvent event) {
        logoutButton1.getScene().getWindow().hide();
        Stage login = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/login.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            // Create a object For using Label which is located login scene    
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void addRemoveOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/AddMaintenanceMember.fxml");
    }

    @FXML
    private void dashboardOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/CheckMaintenanceTask.fxml");
    }

    @FXML
    private void billGenatarorONMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/BillGenarator.fxml");
    }

    @FXML
    private void resignOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/MaintenanceStaff/Resignation.fxml");
    }

    private void sceneSwithch(String path) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane newContent = loader.load();
            // Get the controller
            anchorPaneShow.getChildren().setAll(newContent); //RootPane is AnchorPaneShow name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewScheduleButtonOnMouseClick(ActionEvent event) {
    }

}