/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Salary;
import com.busterminal.utilityclass.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EmployeeSalaryDashboardController implements Initializable {

    @FXML
    private Button loadButton;
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

    @FXML
    private void loadDetails(ActionEvent event) {
        System.out.println(empID);
        user = getEmployeeById("Employee.bin",empID);
        salaryLabel.setText(Integer.toString(user.getSalary()));
        if(user.getSalStatus().getIncresedSalary()< 1){
            LastPaidLabel.setText("No previous entry");
            AskedForIncrementLabel.setText("No previous entry");
            return;
        }
    }

    @FXML
    private void toHome(ActionEvent event) {
    }

    @FXML
    private void switchOvertime(ActionEvent event) {
    }

    @FXML
    private void goHome(ActionEvent event) {
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
            // Already paid
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("You have already been paid");
            a.show();
        } else {
            if (user.getSalStatus().getAskedForSalary() == null || !user.getSalStatus().getAskedForSalary()) {
                user.getSalStatus().setAskedForSalary(true);
                Salary sal = new Salary(empID, user.getSalary(), true,
                        false, user.getSalStatus().getLastPaid(), 0, "");

                empList = user.readEmployeesFromFile("Employee.bin");
                for (Employee emp : empList) {
                    if (emp.getId().equals(empID)) {
                        emp.setSalStatus(sal);
                        Employee.writeEmployeesToFile("Employee.bin", empList);

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
            System.out.println(empID+" from salaryDashboard");
            Salary sal = new Salary(
                empID,
                user.getSalary(),
                true,
                user.getSalStatus().getIsPaid(),
                user.getSalStatus().getLastPaid(),
                increment,
                reason
            );

            empList = user.readEmployeesFromFile("Employee.bin");

            for (Employee emp : empList) {
                if (emp.getId().equals(empID)) {
                    emp.setSalStatus(sal);
                    Employee.writeEmployeesToFile("Employee.bin", empList);

                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setContentText("Request submitted successfully!");
                    a.show();

                    incrementForm.setVisible(false);
                    return;
                }
            }

            // Show error if the employee ID is not found
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
    
}
