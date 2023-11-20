/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyEmployeeController implements Initializable {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> employeTableId;
    @FXML
    private TableColumn<Employee, String> tableFirstName;
    @FXML
    private TableColumn<Employee, String> tableLastName;
    @FXML
    private TableColumn<Employee, String> tableEmail;
    @FXML
    private TableColumn<Employee, String> tablePhone;
    @FXML
    private TableColumn<Employee, Integer> tableSalary;
    @FXML
    private TableColumn<Employee, String> tableDesignation;
    @FXML
    private TableColumn<Employee, Boolean> tableAbsent;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Employee> empList = new ArrayList<>();
    ObservableList<Employee> observableEmployeeList = FXCollections.observableArrayList(empList);
    @FXML
    private TextField searchBox;
    @FXML
    private ChoiceBox<String> sortChoice;
    String getChoices[] = {"ID","First Name","Last Name","Email","Salary","Designation"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortChoice.getItems().addAll(getChoices);
        sortChoice.setValue("ID");
        
        empList = readEmployeesFromFile("MyEmployee.bin");
        employeTableId.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        tableFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        tableLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue(). getLastname()));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        tablePhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhonenumber()));
        tableSalary.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee,String>("empType"));
        tableAbsent.setCellValueFactory(new PropertyValueFactory<Employee,Boolean>("onLeave"));
        
        observableEmployeeList.addAll(empList);
        
        employeeTable.setItems(observableEmployeeList);
    }    


    @FXML
    private void createEmployeeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/CreateEmployee.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static ArrayList<Employee> readEmployeesFromFile(String filename) {
    ArrayList<Employee> employees = new ArrayList<>();

    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
        // Read all existing employees from the file
        employees = (ArrayList<Employee>) inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
        // Handle exceptions if the file doesn't exist or other issues
        e.printStackTrace();
    }
    
    return employees;
}

    @FXML
    private void DeleteEmplyee(ActionEvent event) {
    }

    @FXML
    private void SaveChanges(ActionEvent event) {
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        employeeTable.getItems().clear();
        empList = readEmployeesFromFile("MyEmployee.bin");
        employeTableId.setCellValueFactory(new PropertyValueFactory<Employee,String>("id"));
        tableFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        tableLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue(). getLastname()));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        tablePhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhonenumber()));
        tableSalary.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee,String>("empType"));
        tableAbsent.setCellValueFactory(new PropertyValueFactory<Employee,Boolean>("onLeave"));
        
        observableEmployeeList.addAll(empList);
        
        employeeTable.setItems(observableEmployeeList);
    }
    
    public ArrayList<Employee> filterEmployees(String choice, String inputString) {
        Predicate<Employee> filterPredicate = null;

        switch (choice) {
            case "ID":
                filterPredicate = employee -> employee.getId().startsWith(inputString);
                break;
            case "First Name":
                filterPredicate = employee -> employee.getFirstname().startsWith(inputString);
                break;
            case "Last Name":
                filterPredicate = employee -> employee.getLastname().startsWith(inputString);
                break;
            case "Email":
                filterPredicate = employee -> employee.getEmail().startsWith(inputString);
                break;
            case "Salary":
                // Assuming Salary is stored as a string
                filterPredicate = employee -> String.valueOf(employee.getSalary()).startsWith(inputString);
                break;
            case "Designation":
                filterPredicate = employee -> employee.getEmpType().startsWith(inputString);
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("No " + inputString + " found in list!");
                alert.show();
                return new ArrayList<>();
        }

        ArrayList<Employee> filteredList = observableEmployeeList.stream()
                .filter(filterPredicate)
                .collect(Collectors.toCollection(ArrayList::new));

        if (filteredList.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No " + inputString + " found in list!");
            alert.show();
        } else {employeeTable.getItems().clear();}

        return filteredList;
    }

    @FXML
    private void showSearched(ActionEvent event) {
        String toSearch = searchBox.getText();
        observableEmployeeList.addAll(filterEmployees(sortChoice.getValue(),toSearch));
        employeeTable.setItems(observableEmployeeList);
    }

    
}
