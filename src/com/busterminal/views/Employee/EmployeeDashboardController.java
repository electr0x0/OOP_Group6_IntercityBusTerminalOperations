/*

package com.busterminal.views.Employee;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.utilityclass.Validator;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private TextArea requestLog;
    
    String empID;
    Employee user;
    
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane overtimePane;
    @FXML
    private Label OPHours;
    @FXML
    private Label overtimeRate;
    @FXML
    private AnchorPane Form;
    @FXML
    private TextField ovtHours;
    @FXML
    private TextField ovtRate;
    @FXML
    private TextArea ovtReason;
    
    ArrayList<Employee> empList;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button loadButton;
    @FXML
    private Label overtimeText2;

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

    @FXML
    private void toHome(ActionEvent event) {
        homePane.setVisible(true);
        overtimePane.setVisible(false);
        
        System.out.println(empID);
        
        //System.out.println(user.toString());
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
    private void submitForm(ActionEvent event) {
        System.out.println(user.getOverTime());
       user.getOverTime().setAskedForOvertime(true);
       Boolean isValid = validateFormSubmit();
       System.out.println(isValid);
       if(!isValid){
           Alert a = new Alert(AlertType.ERROR);
           a.setContentText("Form not written properly!");
           a.show();
           return;
       }
       Overtime ot = new Overtime(empID,false,Integer.parseInt(ovtHours.getText()),
       Integer.parseInt(ovtRate.getText()),true,ovtReason.getText());
       
       empList = user.readEmployeesFromFile("Employee.bin");
       
       Overtime.writeOvertimeToFile("Overtime.bin",ot);
       updateEmployeeOvertime(empList,empID,ot);
       
       Alert a = new Alert(AlertType.CONFIRMATION);
       a.setContentText("Request submitted successfully!");
       a.show();
       
       Form.setVisible(false);
         
       
    }

    @FXML
    private void switchOvertime(ActionEvent event) {
        if(user == null){
            return;
        }
        homePane.setVisible(false);
        ShowOvertime();
        overtimePane.setVisible(true);
    }

    @FXML
    private void loadDetails(ActionEvent event) {
        user = getEmployeeById("Employee.bin",empID);
        id.setText(id.getText()+" "+ empID);
        name.setText("Name: "+user.getFirstname()+" "+ user.getLastname());
        designation.setText(designation.getText()+" " +user.getEmpType());
        payedRecieved.setText(payedRecieved.getText()+" "+ user.getSalStatus().getSalary()+"tk");
        overtimeText.setText("Overtime Status: " + user.getOverTime().getIsOvertime());
        overtimeText2.setText("Overtime Status: "+" "+ user.getOverTime().getIsOvertime());
        onLeaveText.setText(onLeaveText.getText()+" "+ user.getHoliday().getOnLeave());
        loadButton.setDisable(true);
    }

    @FXML
    private void applyForOvt(ActionEvent event) {
         if(user.getOverTime().getAskedForOvertime()){
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("You already have submited a request!");
            a.show();
            return;
        } else if(user.getOverTime().getIsOvertime()){
            Alert a = new Alert(AlertType.INFORMATION);
            a.setContentText("Your Overtime permission has been granted already!");
            a.show();
            return;
        }
        if(!Form.isVisible()){
            Form.setVisible(true);
        }
    }
    
    public Boolean validateFormSubmit(){
        return (user.getOverTime().getAskedForOvertime() == true 
                && !Validator.containsOnlyNumbers(ovtHours.getText()) 
                && !Validator.containsOnlyNumbers(ovtRate.getText()));
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

        Employee.writeEmployeesToFile("Employee.bin", empList);
    }


    public void ShowOvertime(){
        if(user.getOverTime().getAskedForOvertime() != null){
            OPHours.setText("Overtime Hour: "+ user.getOverTime().getOvertimeHours());
            overtimeRate.setText("Overtime rate/hr:"+user.getOverTime().getOvertimeRate()+"tk");
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
    private void cancelOvertime(ActionEvent event) {
       
       Alert a = new Alert(AlertType.WARNING,"Are you sure you want to cancel your overtime?",ButtonType.YES, ButtonType.NO);
       Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.YES){
            Overtime ot = new Overtime(empID,false,0,0,false,null);
            empList = user.readEmployeesFromFile("Employee.bin");
            updateEmployeeOvertime(empList,empID,ot);
            a = new Alert(AlertType.CONFIRMATION);
            a.setContentText("Overtime canceled successfully!");
            a.show();
            loadButton.setDisable(false);
        } 
       
       
    }
    
}
*/