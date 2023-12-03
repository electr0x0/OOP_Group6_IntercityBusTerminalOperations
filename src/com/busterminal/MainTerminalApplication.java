package com.busterminal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author electr0
 */
public class MainTerminalApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

       //Parent root = FXMLLoader.load(getClass().getResource("views/Addministrator/AdminDashbord.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("views/passenger/Dashboard_Passenger.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("views/driver/Dashboard_Driver.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("views/accountantUser/AccountantDashboard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("views/terminalManagerUser/TerminalManagerDashboard.fxml"));   
        //Parent root = FXMLLoader.load(getClass().getResource("views/accountantUser/AccountantTicketRefundApplyView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("views/MaintenanceStaff/MaintenanceStaffDashbord.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Large inter-city bus terminal Operations - Group 6 - Team Garbage Collectors");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
