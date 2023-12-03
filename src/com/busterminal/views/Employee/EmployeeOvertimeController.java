/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.utilityclass.Validator;
import static com.busterminal.views.Employee.EmployeeDashboardController.getEmployeeById;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class EmployeeOvertimeController implements Initializable {

    private Button loadButton;
    @FXML
    private AnchorPane overtimePane;
    @FXML
    private Label overtimeText2;
    @FXML
    private Label OPHours;
    @FXML
    private Label overtimeRate;
    @FXML
    private AnchorPane Form;
    @FXML
    private TextArea ovtReason;
    @FXML
    private TextField ovtHours;
    @FXML
    private TextField ovtRate;
    @FXML
    private Button overtimeBtn;
    
     String empID;
     Employee user;
     
     ArrayList<Employee> empList;
    
     private Stage stage;
     private Scene scene;
     private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
    
    public void LoadData(){
        user = getEmployeeById("MyEmployee.bin",empID);
        overtimeText2.setText("Overtime Status: "+" "+ user.getOverTime().getIsOvertime());
        if(user.getOverTime().getOvertimeHours() < 1 || user.getOverTime().getOvertimeRate() < 1)
        {
            OPHours.setText("Not doing overtime.");
            overtimeRate.setText("Not doing overtime.");
        }
        if(!user.getOverTime().getIsOvertime()){
            overtimeBtn.setDisable(true);
        } else{
            overtimeBtn.setDisable(false);
        }
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
    private void applyForOvt(ActionEvent event) {
        if(user.getOverTime().getAskedForOvertime() != null && user.getOverTime().getAskedForOvertime()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You already have submited a request!");
            a.show();
            return;
        } else if(user.getOverTime().getIsOvertime()){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Your Overtime permission has been granted already!");
            a.show();
            return;
        }
        if(!Form.isVisible()){
            Form.setVisible(true);
        }
    }

    @FXML
    private void submitForm(ActionEvent event) {
        System.out.println(user.getOverTime());
       user.getOverTime().setAskedForOvertime(true);
       Boolean isValid = validateFormSubmit();
       System.out.println(isValid);
       if(!isValid){
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setContentText("Form not written properly!");
           a.show();
           return;
       }
       Overtime ot = new Overtime(empID,false,Integer.parseInt(ovtHours.getText()),
       Integer.parseInt(ovtRate.getText()),true,ovtReason.getText());
       
       empList = user.readEmployeesFromFile("MyEmployee.bin");
       
       Overtime.writeOvertimeToFile("Overtime.bin",ot);
       updateEmployeeOvertime(empList,empID,ot);
       
       Alert a = new Alert(Alert.AlertType.CONFIRMATION);
       a.setContentText("Request submitted successfully!");
       a.show();
       
       Form.setVisible(false);
    }

    @FXML
    private void cancelOvertime(ActionEvent event) {
         if(user.getOverTime().getIsOvertime()){
            Alert a = new Alert(Alert.AlertType.WARNING,"Are you sure you want to cancel your overtime?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.YES){
                Overtime ot = new Overtime(empID,false,0,0,false,"");
                empList = user.readEmployeesFromFile("MyEmployee.bin");
                updateEmployeeOvertime(empList,empID,ot);
                a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Overtime canceled successfully!");
                a.show();
                loadButton.setDisable(false);
                return;
        } 
     }
    }
    
    public Boolean validateFormSubmit(){
        return (user.getOverTime().getAskedForOvertime() == true 
                && Validator.containsOnlyNumbers(ovtHours.getText()) 
                && Validator.containsOnlyNumbers(ovtRate.getText()));
    }
    
    public static ArrayList<Overtime> readOvertimeFromFile(String filename) {
        ArrayList<Overtime> overtimes = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            // Read all existing overtime objects from the file
            overtimes = (ArrayList<Overtime>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions if the file doesn't exist or other issues
            e.printStackTrace();
        }

        return overtimes;
    }
 
     
    
    public static void updateEmployeeOvertime(ArrayList<Employee> empList, String id, Overtime newOvertime) {
        for (Employee employee : empList) {
            if (employee.getId().equals(id)) {
                employee.setOverTime(newOvertime);
                break;
            }
        }

        Employee.writeEmployeesToFile("MyEmployee.bin", empList);
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
