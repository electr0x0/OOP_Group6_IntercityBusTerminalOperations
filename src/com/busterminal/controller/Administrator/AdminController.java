package com.busterminal.controller.Administrator;

import com.busterminal.controller.MaintenanceStaff.CheckMaintenanceTaskController;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.User;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.views.Employee.EmployeeDashboardController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private FontAwesomeIconView myAccount;
    @FXML
    private Label addressLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String currentEmail = RelationshipDatabaseClass.getInstance().getCurrentUserEmail();
        DummyEmployee instance = new DummyEmployee();

        instance = User.getEmployeeInstance(currentEmail);

        addressLabel.setText(instance.getFirstName() + " " + instance.getLastName() + "\n" + "User Id :" + " " + instance.getiD() + "\n" + currentEmail);

        sceneSwithch("/com/busterminal/views/Addministrator/Home.fxml");

    }

    @FXML
    private void dashbordOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/Home.fxml");
    }

    @FXML
    private void userButtonOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/AddNewEmploye.fxml");

    }

    @FXML
    private void scheduleShowOnMouseClick(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/passenger/viewSchedule.fxml"));
            Scene newScene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void TransactionOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/ShowTransition.fxml");
    }

    // rest
    @FXML
    private void salaryInfoOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/SalaryStatus.fxml");
    }

    // rest
    @FXML
    private void customerJouurneyReportOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/ClientStatus.fxml");
    }

    // rest
    @FXML
    private void maintenanceCostOnMouseClick(ActionEvent event) throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/passenger/ViewInquiry.fxml"));
            Scene newScene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void busStatusOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/ShowBusStatus.fxml");
    }

    @FXML
    private void ShowResignOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/ShowResignLetter.fxml");
    }

    @FXML
    private void showFeedBackOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/Feadback.fxml");

    }

    @FXML
    private void reservationOnMouseClick(ActionEvent event) {
        sceneSwithch("/com/busterminal/views/Addministrator/ReservationStatus.fxml");
    }

    @FXML
    private void logOutOnMoseClick(ActionEvent event) {
        logoutButton.getScene().getWindow().hide();
        Stage login = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/login.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.setResizable(false);
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

    @FXML
    private void myAccountOnMouseClick(MouseEvent event) {
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
