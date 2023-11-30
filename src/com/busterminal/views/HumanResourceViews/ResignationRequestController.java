/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Leave;
import com.busterminal.model.employeeModels.Resignation;
import com.busterminal.model.employeeModels.Salary;
import com.busterminal.views.Employee.EmployeeDashboardController;
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
 * @author DELL
 */
public class ResignationRequestController implements Initializable {

    @FXML
    private AnchorPane resignPanel;
    @FXML
    private ListView<String> resignView;
    @FXML
    private AnchorPane requestPane;
    @FXML
    private Label leaveReasonLabel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ArrayList<Employee> empList = new ArrayList<>();
    ObservableList<String> items = FXCollections.observableArrayList();
    
    Resignation resign;
    String selectedItem;
    String SelectedId;
    String empID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empList = Employee.readEmployeesFromFile("MyEmployee.bin");
        for(Employee emp: empList){
            if(emp.getResignation().getAskedForResignation()){
                String str = "ID: "+emp.getId() +" Name: "+ emp.getFirstName() + " " + emp.getLastName() +
                        " Designation: "+emp.getEmpType();
                items.addAll(str);
                resignView.setItems(items);
                
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
        empList = readEmployeesFromFile("MyEmployee.bin");
        selectedItem = resignView.getSelectionModel().getSelectedItem();
        int firstSpaceIndex = selectedItem.indexOf(" ");
        SelectedId = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("N"));
         for(Employee emp: empList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               String name = emp.getFirstName() + " " + emp.getLastName();
              String id = emp.getId();
              String resign = emp.getResignation().getResignationReason();
              System.out.println(emp.getResignation().getResignationReason());
              

              String formattedText = String.format("Name: %s\nID: %s\nDesignation: %s\n %s\n",
                      name,id,emp.getEmpType(),emp.getResignation().getResignationReason());
                leaveReasonLabel.setText(formattedText);
                requestPane.setVisible(true);
                return;
            } 
        }
    }

    @FXML
    private void closeRequestPane(ActionEvent event) {
        requestPane.setVisible(false);
    }

    @FXML
    private void acceptRequest(ActionEvent event) {
        for(Employee emp: empList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               empList.remove(emp);
               Employee.writeEmployeesToFile("MyEmployee.bin", empList);
               resignView.getItems().remove(selectedItem);
               requestPane.setVisible(false);
               return;
            }
        }
    }


    @FXML
    private void goToAccount(ActionEvent event) {
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

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    
    
}
