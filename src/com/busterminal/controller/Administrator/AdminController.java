package com.busterminal.controller.Administrator;

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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private AnchorPane slide_AnchorPane;
    @FXML
    private Pane inner_Pane;
    @FXML
    private MenuButton departureMenuButton;
    @FXML
    private MenuButton itsupportMenuButton;
    @FXML
    private AnchorPane anchorPaneShow;
    @FXML
    private MenuButton notificationMenuButton;
    @FXML
    private Button logoutButton;
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sceneSwithch("Home.fxml");
    }

    @FXML
    private void dashbordOnMouseClick(ActionEvent event) {
        sceneSwithch("Home.fxml");
    }

    @FXML
    private void userButtonOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/AddNewEmploye.fxml");

    }

    @FXML
    private void scheduleShowOnMouseClick(ActionEvent event) {
        sceneSwithch("ShowSchedule.fxml");
    }

    private void routeInfoOnMouseClick(ActionEvent event) {
        sceneSwithch("ff.fxml");
    }

    @FXML
    private void TransactionOnMouseClick(ActionEvent event) {
        sceneSwithch("ShowTransition.fxml");
    }

    // rest
    @FXML
    private void salaryInfoOnMouseClick(ActionEvent event) {
        sceneSwithch("SalaryStatus.fxml");
    }

    // rest
    @FXML
    private void customerJouurneyReportOnMouseClick(ActionEvent event) {
        sceneSwithch("ff.fxml");
    }

    // rest
    @FXML
    private void maintenanceCostOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void busStatusOnMouseClick(ActionEvent event) {
        sceneSwithch("ShowBusStatus.fxml");
    }

    @FXML
    private void ShowResignOnMouseClick(ActionEvent event) {
        sceneSwithch("ShowResignLetter.fxml");
    }

    @FXML
    private void showFeedBackOnMouseClick(ActionEvent event) {
        sceneSwithch("Feadback.fxml");

    }

    @FXML
    private void reservationOnMouseClick(ActionEvent event) {
        sceneSwithch("ReservationStatus.fxml");
    }

    @FXML
    private void logOutOnMoseClick(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
        Stage login = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            // Create a object For using Label which is located login scene    
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sceneSwithch(String path) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane newContent = loader.load();
            // Get the controller
            //TerminalManagerCurrentBusRoutesAndStandsController controller = loader.getController();
            anchorPaneShow.getChildren().setAll(newContent); //RootPane is AnchorPaneShow name
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
