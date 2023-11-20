package com.busterminal.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private MenuButton reportMenuButton;
    @FXML
    private MenuButton reservationMenuButton;
    @FXML
    private MenuButton userMenuButton;
    @FXML
    private AnchorPane anchorPaneShow;
    @FXML
    private MenuButton notificationMenuButton;
    @FXML
    private Button logoutButton;
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //sceneSwithch("Home.fxml");
        sceneSwithch("AddNewEmploye.fxml");
       

    }
    @FXML
    private void scheduleShowOnMouseClick(ActionEvent event) {
        
    }

    @FXML
    private void ticketCancleRequestShowOnMouseClick(ActionEvent event) {
        
    }

    @FXML
    private void ticketPriceInfoOnMouseClick(ActionEvent event) {

    }

    @FXML
    private void routeInfoOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void cochInfoOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void TransactionOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void fuelPaymentInfoOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void salaryInfoOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void staffDutyReportShowOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void saleReportSInfoShowOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void fuelConsumtionReportOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void customerJouurneyReportOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void dateWiseReportOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void reservationQuatationOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void reservationDetailsOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void userOnMouseClick(ActionEvent event) throws IOException {
          sceneSwithch("AddNewEmploye.fxml");
    }


    @FXML
    private void privilegeOnMouseClick(ActionEvent event) {
    }

    @FXML
    private void showComplainOnMouseClick(ActionEvent event) {
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
        // fxml file load
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // find the index of anchorPaneShow in the Hbox
        int index = root.getChildren().indexOf(anchorPaneShow);

        // Now remove anchorPaneshow and add the new contenet at the same index
        root.getChildren().remove(index);
        root.getChildren().add(index, fxml);

        // for debugging 
        System.out.println("Index" + index);
        System.out.println("Children after update" + root.getChildren());
    }

}
