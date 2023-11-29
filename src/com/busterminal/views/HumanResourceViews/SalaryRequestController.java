/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Salary;
import com.busterminal.views.Employee.EmployeeDashboardController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SalaryRequestController implements Initializable {

    @FXML
    private AnchorPane RequestPane;
    @FXML
    private Label expandLabel;
    @FXML
    private Tab paymentTab;
    @FXML
    private ListView<String> paymentList;
    @FXML
    private Tab incrementTab;
    @FXML
    private ListView<String> incrementList;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    ArrayList<Employee> salList = new ArrayList<>();
    Salary sal;
    String selectedItem;
    String SelectedId;
    String empID;
    
    ObservableList<String> itemsIncrement = FXCollections.observableArrayList();
    ObservableList<String> itemsPayment = FXCollections.observableArrayList();
    ObservableList<Employee> employeeList = FXCollections.observableArrayList(); 
    
    @FXML
    private AnchorPane incrementRequestPane;
    @FXML
    private Label incrementDetails;
    @FXML
    private TableView<Employee> salaryTable;
    @FXML
    private TableColumn<Employee, String> tableID;
    @FXML
    private TableColumn<Employee, String> tableFname;
    @FXML
    private TableColumn<Employee, String> tableLname;
    @FXML
    private TableColumn<Employee, String> tableDesignation;
    @FXML
    private TableColumn<Employee, Integer> tableSal;
    @FXML
    private TableColumn<Employee, LocalDate> tableLastPaid;
    @FXML
    private TableColumn<Employee, Boolean> tableIsPaid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salList = Employee.readEmployeesFromFile("Employee.bin");
        for(Employee emp: salList){
            if(emp.getSalStatus().getIncresedSalary() > 0){
                String salarySTR = "ID: "+emp.getId() +" Name"+ emp.getFirstname() + " " + emp.getLastname() +
                        "Increment wanted: " + emp.getSalStatus().getIncresedSalary() +
                        "Reason: " + emp.getSalStatus().getReason();
                itemsIncrement.add(salarySTR);
                incrementList.setItems(itemsIncrement);
            } 
            if(emp.getSalStatus().getAskedForSalary() != null && emp.getSalStatus().getAskedForSalary()){
                String salarySTR = "ID: "+emp.getId()+" Name: "+ emp.getFirstname() + " " + emp.getLastname() +
                        " Salary: " + emp.getSalary() +
                        " Last Paid: " + emp.getSalStatus().getLastPaid();
                    
                itemsPayment.add(salarySTR);
                paymentList.setItems(itemsPayment);
                        
            }
        }
        
        tableID.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        tableFname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        tableLname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue(). getLastname()));
        tableSal.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee,String>("empType"));
        tableLastPaid.setCellValueFactory(new PropertyValueFactory<Employee,LocalDate>("lastPaid"));
        tableIsPaid.setCellValueFactory(new PropertyValueFactory<Employee,Boolean>("isPaid"));
       

    employeeList.addAll(salList);
    salaryTable.setItems(employeeList);
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
    private void closeRequestPane(ActionEvent event) {
        incrementDetails.setText("");
        incrementRequestPane.setVisible(false);
        
    }

    @FXML
    private void acceptRequest(ActionEvent event) {
    for(Employee emp: salList) {
        if(emp.getId().trim().equals(SelectedId.trim())) {
            // Increment the salary
            int newSalary = emp.getSalary() + emp.getSalStatus().getIncresedSalary();
            emp.setSalary(newSalary);
            
            // Update the Salary object with the new salary
            Salary sal = new Salary(emp.getId(), newSalary,
                    true, 
                    true,
                    LocalDate.now(), 0, "");
            emp.setSalStatus(sal);
            emp.setIsPaid(true);
            emp.setLastPaid(LocalDate.now());
            // Save the updated employee list to the file
            Employee.writeEmployeesToFile("Employee.bin", salList);

            incrementDetails.setText("");
            incrementRequestPane.setVisible(false);
            incrementList.getItems().remove(selectedItem);
            return;
        }
    }
}


    @FXML
    private void denaiRequest(ActionEvent event) {
        for(Employee emp: salList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               Salary sal = new Salary(emp.getId(),emp.getSalary(),
               emp.getSalStatus().getAskedForSalary(),emp.getSalStatus().getIsPaid(),
               emp.getSalStatus().getLastPaid(),0,"");
               emp.setSalStatus(sal);
               Employee.writeEmployeesToFile("Employee.bin", salList);
               incrementDetails.setText("");
               incrementRequestPane.setVisible(false);
               incrementList.getItems().remove(selectedItem);
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

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void showSelection(ActionEvent event) {
        if(!incrementTab.isSelected()){
            return;
        }
        selectedItem = incrementList.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem+" Opem sim sim!");
        int firstSpaceIndex = selectedItem.indexOf(" ");
        SelectedId = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("N"));
        for(Employee emp: salList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               String output = String.format("Name %s\n ID: %s\nIncrement wanted: %s\n"
                       + "Last paid: %s\n %s",emp.getFirstname()+" "+emp.getLastname(),emp.getId(),
                       emp.getSalStatus().getIncresedSalary(),emp.getSalStatus().getLastPaid(),
                       emp.getSalStatus().getReason());
               incrementDetails.setText(output);
               incrementRequestPane.setVisible(true);
               return;
            }
        }
        
        
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    @FXML
    private void paySalary(ActionEvent event) {
        selectedItem = paymentList.getSelectionModel().getSelectedItem();  
        int firstSpaceIndex = selectedItem.indexOf(" ");
        SelectedId = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("N"));
        for(Employee emp: salList){
            if(emp.getId().trim().equals(SelectedId.trim())){
               Salary sal = new Salary(emp.getId(), emp.getSalary(),
                    false, 
                    true,
                    LocalDate.now(), 0, "");
            emp.setSalStatus(sal);
            emp.setIsPaid(true);
            emp.setLastPaid(LocalDate.now());
            Employee.writeEmployeesToFile("Employee.bin", salList);
            paymentList.getItems().remove(selectedItem);
               return;
            }
        }
    }


    @FXML
    private void toLeave(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/HolidayRequest.fxml"));
            root = loader.load();
            HolidayRequestController controller = loader.getController();

            controller.setEmpID(empID);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }
    
}
