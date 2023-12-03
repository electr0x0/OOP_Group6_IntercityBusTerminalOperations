/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.controller.accountant.AccountantEmployeeReimbursementManagementController;
import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.Employee;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class EmployeeDashboardController implements Initializable  {

    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label designation;
    @FXML
    private Label payedRecieved;
    @FXML
    private Label overtimeText;
    @FXML
    private Label onLeaveText;
    
    String empID;
    Employee user;
    
    ArrayList<Employee> empList;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Button loadButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private TextArea requestLog;


    
    
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
        LoadData();
    }

   
    public static Employee getEmployeeById(String filename, String id) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            ArrayList<Employee> employees = (ArrayList<Employee>) inputStream.readObject();

            // Search for the employee with the given ID
            for (Employee emp : employees) {
                if (emp.getId().equals(id)) {
                    return emp;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null; 
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

    
    
    public void LoadData(){
        requestLog.clear();
        user = getEmployeeById("MyEmployee.bin",empID);
        id.setText(id.getText()+" "+ empID);
        System.out.println(empID);
        System.out.println(user.toString());
        name.setText("Name: "+user.getFirstName()+" "+ user.getLastName());
        designation.setText("Designation: "+" " +user.getEmpType());
        payedRecieved.setText("Payment Recieved: "+" "+ user.getSalStatus().getSalary()+"tk");
        overtimeText.setText("Overtime Status: " + user.getOverTime().getIsOvertime());
        onLeaveText.setText("On Holiday: "+user.getOnLeave());
        empList = Employee.readEmployeesFromFile("MyEmployee.bin");
        for(Employee emp: empList){
            if(emp.getId().equals(user.getId())){
                if(emp.getOverTime().getIsOvertime()){
                    requestLog.appendText("A Request for overtime was sent.\n");
                }
                if(emp.getSalStatus().getIncresedSalary()>0){
                    requestLog.appendText("A Request for increment was sent.\n");
                }
                if(emp.getSalStatus().getAskedForSalary()!= null && emp.getSalStatus().getAskedForSalary()){
                    requestLog.appendText("A Request for Salary Pay was sent.\n");
                }
                if(emp.getHoliday().getAskedForLeave()){
                    requestLog.appendText("Request for Holiday was sent.\n");
                }
                if(emp.getResignation().getAskedForResignation()){
                    requestLog.appendText("A Request for Resignation was sent.\n");
                }
                
            }
        }
      }

       
    @FXML
    private void goHome(ActionEvent event) throws IOException {
            if(user.getEmpType().equals("Administrator")){
               SceneSwitch(event,"/com/busterminal/views/Addministrator/AdminDashbord.fxml");
            } else if(user.getEmpType().equals("Maintenance Staff")){
                SceneSwitch(event,"/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml");
            } else if(user.getEmpType().equals("Driver")){
                SceneSwitch(event,"/com/busterminal/views/driver/Dashboard_Driver.fxml");
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
    
    

    

   
}
