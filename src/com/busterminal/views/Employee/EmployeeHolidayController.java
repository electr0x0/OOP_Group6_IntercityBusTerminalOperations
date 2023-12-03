/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Leave;
import static com.busterminal.views.Employee.EmployeeSalaryDashboardController.getEmployeeById;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EmployeeHolidayController implements Initializable {

    private Button loadButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private Label onLeaveGrid;
    @FXML
    private Label leaveStartGrid;
    @FXML
    private Label leaveEndGrid;
    @FXML
    private AnchorPane Form;
    @FXML
    private TextArea LeaveReasonInput;
    @FXML
    private DatePicker leaveStartInput;
    @FXML
    private DatePicker leaveEndInput;
    
    String empID;
    Employee user;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ArrayList<Employee> empList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    
    public void LoadData(){
        user = getEmployeeById("MyEmployee.bin",empID);
        onLeaveGrid.setText("On Leave: "+ user.getHoliday().getOnLeave());
        if(user.getHoliday().getOnLeave() != true){
            leaveStartGrid.setText("Not on leave.");
            leaveEndGrid.setText("Not on leave.");
            return;
        }
        leaveStartGrid.setText(user.getHoliday().getLeaveStartDate().toString());
        leaveEndGrid.setText(user.getHoliday().getLeaveEndDate().toString());

    }

    @FXML
    private void toHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            root = loader.load();
            EmployeeDashboardController controller = loader.getController();

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
            if(user.getEmpType().equals("Administrator")){
               SceneSwitch(event,"/com/busterminal/views/Addministrator/AdminDashbord.fxml");
            } else if(user.getEmpType().equals("Maintenance Staff")){
                SceneSwitch(event,"/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml");
            } else if(user.getEmpType().equals("Driver")){
                SceneSwitch(event,"/com/busterminal/views/accountUser/AccountDashbord.fxml");
            } else if(user.getEmpType().equals("Terminal Manager")){
                SceneSwitch(event,"/com/busterminal/views/terminalManagerUser/TerminalManagerDashboard.fxml");
            } else if(user.getEmpType().equals("Human Resource")){
                
                SceneSwitch(event,"/com/busterminal/views/HumanResourceViews/MyEmployee.fxml");
                
            } else if(user.getEmpType().equals("Accountant")){
                SceneSwitch(event,"/com/busterminal/views/accountantUser/AccountantDashboard.fxml");
            }                      
        
    }
    
      public void SceneSwitch(ActionEvent e, String fxmlLocal) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlLocal));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception (e.g., logging or displaying an error message)
        }   
    }

    @FXML
    private void applyForLeave(ActionEvent event) {
        if(user.getHoliday().getAskedForLeave()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You already have a pending request!");
            a.show();
        } else if(user.getHoliday().getOnLeave()){
             Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You have already on leave");
            a.show();
        } else {
           Form.setVisible(true);
        }
        
    }

    @FXML
    private void submitForm(ActionEvent event) {
        LocalDate leaveStart = leaveStartInput.getValue();
        LocalDate leaveEnd = leaveEndInput.getValue();

        if (leaveStart == null || leaveEnd == null || LeaveReasonInput.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Empty Fields.");
            a.show();
        } else if (leaveEnd.isBefore(leaveStart)) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Inappropriate Dates.");
            a.show();
        } else {
            Leave chuti = new Leave(empID, false, true, LeaveReasonInput.getText(), leaveStart, leaveEnd);
            empList = user.readEmployeesFromFile("MyEmployee.bin");
            for (Employee emp : empList) {
                if (emp.getId().equals(empID)) {
                    emp.setHoliday(chuti);
                    Employee.writeEmployeesToFile("MyEmployee.bin", empList);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Request submitted successfully!");
                    a.show();
                    Form.setVisible(false);
                    return;
                }
            }
        }
    }

    @FXML
    private void toResign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeResignation.fxml"));
            root = loader.load();
            EmployeeResignationController controller = loader.getController();

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
    private void toReimburs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantReimbursementApplyView.fxml"));
            root = loader.load();
            AccountantReimbursementApplyViewController controller = loader.getController();

            controller.setEmployeeIDFromSceneSwitch(empID);
             
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void toMemo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeMemo.fxml"));
            root = loader.load();
            EmployeeMemoController controller = loader.getController();

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
    private void finishHoliday(ActionEvent event) {
        System.out.println(user.getId());
        empList = user.readEmployeesFromFile("MyEmployee.bin");
        if(user.getHoliday().getOnLeave()){
            Leave chuti = new Leave(empID, false, false,"");
            for(Employee emp: empList){
                    if(emp.getId().equals(empID)){
                        emp.setOnLeave(false);
                        emp.setHoliday(chuti);
                        Employee.writeEmployeesToFile("MyEmployee.bin", empList);
                         Alert a = new Alert(AlertType.CONFIRMATION,"We are thrilled to have you back!");
                        a.show();
                        onLeaveGrid.setText("On Leave: "+ emp.getHoliday().getOnLeave());
                         leaveStartGrid.setText("Not on leave.");
                           leaveEndGrid.setText("Not on leave.");
                           return;
                     }
            }
        } else{
            Alert a = new Alert(AlertType.INFORMATION,"You are not on leave");
            a.show();
        }
    }

    
}
