
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

        
        //Parent root = FXMLLoader.load(getClass().getResource("views/passenger/Dashboard_Passenger.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("views/driver/Dashboard_Driver.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("views/terminalManagerUser/TerminalManagerDashboard.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("views/CreateEmployee.fxml"));

      

        //Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("views/AdminDashbord.fxml"));


         // Parent root = FXMLLoader.load(getClass().getResource("views/passenger/Dashboard_Passenger.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("views/driver/Dashboard_Driver.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



