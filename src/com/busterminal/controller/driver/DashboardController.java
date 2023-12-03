
package com.busterminal.controller.driver;


import com.busterminal.controller.MaintenanceStaff.CheckMaintenanceTaskController;
import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.BulletinMassage;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.views.Employee.EmployeeDashboardController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DashboardController implements Initializable {

    @FXML
    private Label countLabel;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int count = BulletinMassage.countUpcomingMassage(RelationshipDatabaseClass.getInstance().getCurrentUserEmail());
        
        if (count==0){
            countLabel.setText("");
        }else{
            countLabel.setText(Integer.toString(count));
            
        }
    }    

    @FXML
   private void switchToRouteSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Route.fxml"));
        root = (Parent) someLoader.load();
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
}
   
  
    
   
  
   
    @FXML
    private void switchToPassangerInfoSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/PassengerInfo.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
   
    }

    @FXML
    private void switchToBusInfoSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/BusInfo.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToReportSceneOnClick(ActionEvent event) throws IOException {
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Resign.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

   
   

    

    @FXML
    private void switchToChatSceneOnClick(ActionEvent event) throws IOException {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Noticiation.fxml"));
            Scene newScene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(CheckMaintenanceTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToDashboardScene(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/login.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToTripHistorySceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/TripHistory.fxml"));
        root = (Parent) someLoader.load();       
        Scene someScene = new Scene (root);
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        someStage.setScene(someScene);
        someStage.show();
    }

  

    @FXML
    private void switchToReqMaintenaceSceneOnClick(ActionEvent event) throws IOException {
        
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/RequestMaintenance.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
    }

    @FXML
    private void switchToReimbursementSceneOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantReimbursementApplyView.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        AccountantReimbursementApplyViewController controller = someLoader.getController();
        
        controller.setEmployeeIDFromSceneSwitch(RelationshipDatabaseClass.getInstance().getCurrentLoggedIn());
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
    }

    @FXML
    private void switchToMyAccountOnClick(ActionEvent event) {
        try {

            Parent root = null;
            FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            
            root = (Parent) someLoader.load();
            EmployeeDashboardController controller = someLoader.getController();
            
            controller.setEmpID(RelationshipDatabaseClass.getInstance().getCurrentLoggedIn());
            
            Scene someScene = new Scene(root);

            Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            someStage.setScene(someScene);
            someStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            //showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }
    

    
}
