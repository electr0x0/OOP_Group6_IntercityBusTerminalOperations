/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.model.Employee;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    @FXML
    private TextArea requestLog;
    
    String empID;
    Employee user;

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
    private void toHome(ActionEvent event) {
        System.out.println(empID);
        user = getEmployeeById("MyEmployee.bin",empID);
        id.setText(id.getText()+" "+ empID);
        name.setText("Name: "+user.getFirstname()+" "+ user.getLastname());
        designation.setText(designation.getText()+" " +user.getEmpType());
        payedRecieved.setText(payedRecieved.getText()+" "+ user.getSalStatus().getSalary()+"tk");
        overtimeText.setText(overtimeText.getText()+" "+ user.getOverTime().getIsOvertime());
        onLeaveText.setText(onLeaveText.getText()+" "+ user.getHoliday().getOnLeave());
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

    
}
