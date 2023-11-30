/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Resignation;
import static com.busterminal.views.Employee.EmployeeSalaryDashboardController.getEmployeeById;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EmployeeResignationController implements Initializable {

    private Button loadButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane salaryPane;
    @FXML
    private TextArea ResignationLetter;
    @FXML
    private TextField incrementAmount;
    
     String empID;
    Employee user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ArrayList<Employee> empList = new ArrayList<>();
    @FXML
    private AnchorPane resignForm;
    @FXML
    private AnchorPane disPlane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    public void LoadData(){
         user = getEmployeeById("MyEmployee.bin",empID);
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    
    @FXML
    private void toHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            root = loader.load();
            EmployeeSalaryDashboardController controller = loader.getController();

            controller.setEmpID(empID);
            controller.LoadData();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void switchOvertime(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeOvertime.fxml"));
            root = loader.load();
            EmployeeOvertimeController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void submitForm(ActionEvent event) {
        if(ResignationLetter.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Please add your resignation letter");
            a.show();
            return;
        }
        Resignation resign = new Resignation(empID,true,ResignationLetter.getText());
        empList = user.readEmployeesFromFile("MyEmployee.bin");
            for (Employee emp : empList) {
                if (emp.getId().equals(empID)) {
                    emp.setResignation(resign);
                    Employee.writeEmployeesToFile("MyEmployee.bin", empList);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Request submitted successfully!");
                    a.show();
                    resignForm.setVisible(false);
                    disPlane.setVisible(true);
                    return;
                }
            }
    }

    @FXML
    private void toSalary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeSalaryDashboard.fxml"));
            root = loader.load();
            EmployeeSalaryDashboardController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
        
    }

    @FXML
    private void resign(ActionEvent event) {
         user = getEmployeeById("MyEmployee.bin",empID);
        if(!user.getResignation().getAskedForResignation()){
            Alert a = new Alert(Alert.AlertType.WARNING,"Are you sure you want to resign?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = a.showAndWait();
                    
                    resignForm.setVisible(false);
                    disPlane.setVisible(false);
                    resignForm.setVisible(true);
                    
            }else{
            Alert b = new Alert(Alert.AlertType.INFORMATION);
                        b.setContentText("You have a reuest pending!");
                        b.show();
        }
        }

    @FXML
    private void toHoliday(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeHoliday.fxml"));
            root = loader.load();
            EmployeeHolidayController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    

         
    
}
