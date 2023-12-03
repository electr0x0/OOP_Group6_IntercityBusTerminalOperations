/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Salary;
import com.busterminal.utilityclass.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EmployeeSalaryDashboardController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane salaryPane;
    @FXML
    private Label salaryLabel;
    @FXML
    private Label LastPaidLabel;
    @FXML
    private Label AskedForIncrementLabel;
    @FXML
    private AnchorPane incrementForm;
    @FXML
    private TextArea incrementReason;
    @FXML
    private TextField incrementAmount;
    
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
        salaryLabel.setText(Integer.toString(user.getSalary())+"tk");
        if(user.getSalStatus().getIncresedSalary()< 1){
            AskedForIncrementLabel.setText("No Increment Request");
        } else {
            AskedForIncrementLabel.setText("Asked for Increment: yes");
        }
        if (user.getSalStatus().getLastPaid() != null) {
            LastPaidLabel.setText(user.getSalStatus().getLastPaid().toString());
        } else {
            LastPaidLabel.setText("Have not been paid yet.");
        }
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
    private void applyForIncrement(ActionEvent event) {
        if(user.getSalStatus().getIncresedSalary() != 0 || user.getSalStatus().getIncresedSalary() > 0){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You already have submited a request!");
            a.show();
            return;
        } 
        if(!incrementForm.isVisible()){
            incrementForm.setVisible(true);
        }
    }

    @FXML
    private void applyForSalary(ActionEvent event) {
        Integer increasedSalary = user.getSalStatus().getIncresedSalary();

        if (increasedSalary != null && increasedSalary != 0) {
            // There's a pending request
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You already have a pending request!");
            a.show();
        } else if (user.getSalStatus().getIsPaid() != null && user.getSalStatus().getIsPaid()) {
            if(user.getSalStatus().getLastPaid()!= null && ChronoUnit.DAYS.between(user.getSalStatus().getLastPaid(),LocalDate.now()) >= 25){
                user.getSalStatus().setAskedForSalary(true);
                Salary sal = new Salary(empID, user.getSalary(), true,
                        false, user.getSalStatus().getLastPaid(), 0, "");

                empList = user.readEmployeesFromFile("MyEmployee.bin");
                for (Employee emp : empList) {
                    if (emp.getId().equals(empID)) {
                        emp.setSalStatus(sal);
                        Employee.writeEmployeesToFile("MyEmployee.bin", empList);

                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Request submitted successfully!");
                        a.show();

                        return;
                    }
                }
            }
            // Already paid
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You have already been paid next salary request can be made after 25 days");
            a.show();
        } else {
            if (user.getSalStatus().getAskedForSalary() == null || !user.getSalStatus().getAskedForSalary()) {
                user.getSalStatus().setAskedForSalary(true);
                Salary sal = new Salary(empID, user.getSalary(), true,
                        false, user.getSalStatus().getLastPaid(), 0, "");

                empList = user.readEmployeesFromFile("MyEmployee.bin");
                for (Employee emp : empList) {
                    if (emp.getId().equals(empID)) {
                        emp.setSalStatus(sal);
                        Employee.writeEmployeesToFile("MyEmployee.bin", empList);

                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Request submitted successfully!");
                        a.show();

                        return;
                    }
                }
                // Handle the scenario where askedForSalary is already set
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You have already submitted a request.");
                a.show();
            } else {
                // Handle the scenario where askedForSalary is already set
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("You have already submitted a request.");
                a.show();
            }
        }
    }

    @FXML
    private void submitForm(ActionEvent event) {
        String reason = incrementReason.getText();
        String amount = incrementAmount.getText();

        if (!reason.isEmpty() && Validator.containsOnlyNumbers(amount)) {
            int increment = Integer.parseInt(amount);
            System.out.println(increment+" from salaryDashboard");
            Salary sal = new Salary(
                empID,
                user.getSalary(),
                true,
                user.getSalStatus().getIsPaid(),
                user.getSalStatus().getLastPaid(),
                increment,
                reason
            );

            empList = user.readEmployeesFromFile("MyEmployee.bin");

            for (Employee emp : empList) {
                if (emp.getId().equals(empID)) {
                    emp.setSalStatus(sal);
                    Employee.writeEmployeesToFile("MyEmployee.bin", empList);

                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Request submitted successfully!");
                    a.show();

                    incrementForm.setVisible(false);
                    return;
                }
            }

            // Show error if the employee ID is notfound
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Employee ID not found!");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Form not filled properly!");
            a.show();
        }
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
    private void toReimbus(ActionEvent event) {
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
