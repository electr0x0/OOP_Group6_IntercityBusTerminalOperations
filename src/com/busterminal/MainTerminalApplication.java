package com.busterminal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainTerminalApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Addministrator/ReservationStatus.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/MaintenanceStaff/AddParts.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Addministrator/ShowResignLetter.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Addministrator/Feadback.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Addministrator/AdminDashbord.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Addministrator/Home.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/MaintenanceStaff/AddParts.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
