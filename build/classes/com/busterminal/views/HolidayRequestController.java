/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Leave;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.model.employeeModels.Salary;
import static com.busterminal.views.HumanResourceViews.MyEmployeeController.readEmployeesFromFile;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HolidayRequestController implements Initializable {

    @FXML
    private AnchorPane LeavePanel;
    @FXML
    private ListView<String> LeaveView;
    @FXML
    private AnchorPane ovtRequestPane;
    @FXML
    private Label leaveReasonLabel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ArrayList<Employee> empList = new ArrayList<>();
    Salary sal;
    String selectedItem;
    String SelectedId;
    String empID;
    ObservableList<String> items = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empList = Employee.readEmployeesFromFile("Employee.bin");
        for(Employee emp: empList){
            if(emp.getHoliday().getAskedForLeave()){
                String str = "ID: "+emp.getId() +" Name: "+ emp.getFirstname() + " " + emp.getLastname() +
                        " From: "+ emp.getHoliday().getLeaveStartDate().toString()+" To: "+emp.getHoliday().getLeaveEndDate().toString();
                items.addAll(str);
                LeaveView.setItems(items);
            }
        }
    }    

    @FXML
    private void myEmployees(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void overtimePanel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toSalary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/SalaryRequest.fxml"));
            root = loader.load();
            SalaryRequestController controller = loader.getController();

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
    private void expandList(ActionEvent event) {
      empList = readEmployeesFromFile("Employee.bin");
      selectedItem = LeaveView.getSelectionModel().getSelectedItem();
      int firstSpaceIndex = selectedItem.indexOf(" ");
      SelectedId = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("N"));
       for(Employee emp: empList){
          if(emp.getId().trim().equals(SelectedId.trim())){
             String name = emp.getFirstname() + " " + emp.getLastname();
            String id = emp.getId();
            String start = emp.getHoliday().getLeaveStartDate().toString();
            String end = emp.getHoliday().getLeaveEndDate().toString();
            String reason = emp.getHoliday().getLeaveReason();

            String formattedText = String.format("Name: %s\nID: %s\nStart Date: %s\nEnd Date: %s\nReason: %s",
                    name, id, start, end, reason);
              leaveReasonLabel.setText(formattedText);
              ovtRequestPane.setVisible(true);
              return;
          } 
      }
    }

    @FXML
    private void closeRequestPane(ActionEvent event) {
        ovtRequestPane.setVisible(false);
    }

    @FXML
    private void acceptRequest(ActionEvent event) {
         for(Employee emp: empList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               Leave chuti = new Leave(emp.getId(),true,false,"",emp.getHoliday().getLeaveStartDate()
               ,emp.getHoliday().getLeaveEndDate());
               emp.setHoliday(chuti);
               leaveReasonLabel.setText("");
               ovtRequestPane.setVisible(false);
               Employee.writeEmployeesToFile("Employee.bin", empList);
               LeaveView.getItems().remove(selectedItem);
               return;
            }
        }
        
    }

    @FXML
    private void denaiRequest(ActionEvent event) {
        for(Employee emp: empList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               Leave chuti = new Leave(emp.getId(),false,false,"",null
               ,null);
               emp.setHoliday(chuti);
               leaveReasonLabel.setText("");
               ovtRequestPane.setVisible(false);
               Employee.writeEmployeesToFile("Employee.bin", empList);
               LeaveView.getItems().remove(selectedItem);
               return;
            }
        }
    }

    @FXML
    private void goToAccount(ActionEvent event) {
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    
    
}
