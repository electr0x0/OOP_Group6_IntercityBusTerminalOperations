/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.views.Employee.EmployeeDashboardController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
    String myId="771126081851";
    @FXML
    private AnchorPane EmployeeListPane;
    
    @FXML
    private ListView<String> OvertimeView;
    ArrayList<Overtime> ovtList = new ArrayList<>();
    private AnchorPane OvertimePane;
    @FXML
    private AnchorPane switchToOvertime;
    @FXML
    private AnchorPane ovtRequestPane;
    @FXML
    private Label expandLabelOvt;
    
    Overtime ovt;
    String selectedItem;
    ObservableList<String> items = FXCollections.observableArrayList(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortChoice.getItems().addAll(getChoices);
        sortChoice.setValue("ID");
        
        empList = readEmployeesFromFile("Employee.bin");
        if(empList != null){
            RelationshipDatabaseClass.getInstance().setAllEmployees(empList);
        }
        
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
    ArrayList<Employee> filteredList = new ArrayList<>();

    switch (choice) {
        case "ID":
            for (Employee employee : observableEmployeeList) {
                if (employee.getId().startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        case "First Name":
            for (Employee employee : observableEmployeeList) {
                if (employee.getFirstname().startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        case "Last Name":
            for (Employee employee : observableEmployeeList) {
                if (employee.getLastname().startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        case "Email":
            for (Employee employee : observableEmployeeList) {
                if (employee.getEmail().startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        case "Salary":
            for (Employee employee : observableEmployeeList) {
                if (String.valueOf(employee.getSalary()).startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        case "Designation":
            for (Employee employee : observableEmployeeList) {
                if (employee.getEmpType().startsWith(inputString)) {
                    filteredList.add(employee);
                }
            }
            break;
        default:
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("No " + inputString + " found in list!");
            alert.show();
            return new ArrayList<>();
    }

    if (filteredList.isEmpty()) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("No " + inputString + " found in list!");
        alert.show();
    } else {
        employeeTable.getItems().clear();
    }

    return filteredList;
}


    @FXML
    private void showSearched(ActionEvent event) {
        String toSearch = searchBox.getText();
        observableEmployeeList.addAll(filterEmployees(sortChoice.getValue(),toSearch));
        employeeTable.setItems(observableEmployeeList);
    }

    @FXML
    private void goToAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            root = loader.load();
            EmployeeDashboardController controller = loader.getController();

            controller.setEmpID(myId);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    

    @FXML
    private void myEmployees(ActionEvent event) {
        employeeTable.getItems().clear();
        sortChoice.getItems().addAll(getChoices);
        sortChoice.setValue("ID");
        
        empList = readEmployeesFromFile("Employee.bin");
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
        switchToOvertime.setVisible(false);
        EmployeeListPane.setVisible(true);
    }

    @FXML
    private void overtimePanel(ActionEvent event) {
        ovtList = Overtime.readOvertimeFromFile("Overtime.bin");
        items = FXCollections.observableArrayList(); // Create an empty observable list
        
       
        
        // Assuming ovtList is your list of Overtime objects
        for (Overtime overtime : ovtList) {
            int overtimeHours = overtime.getOvertimeHours();
            int overtimeRate = overtime.getOvertimeRate();
            String overtimeReason = overtime.getOvertimeReason();
            String id = overtime.getId();

            // Create the formatted string for each overtime
            String overtimeString =  "ID: " + id+
                                     " Hour expected: " + overtimeHours +
                                     " Hour Rate: " + overtimeRate +
                                     " Reason: \"" + overtimeReason + "\"";

            // Add each formatted string to the items list
            items.add(overtimeString);
            
        }

        // Set the items list to the ListView
        OvertimeView.setItems(items);
        
        switchToOvertime.setVisible(true);
        EmployeeListPane.setVisible(false);
    }

    @FXML
    private void expandList(ActionEvent event) {
      empList = readEmployeesFromFile("Employee.bin");
      selectedItem = OvertimeView.getSelectionModel().getSelectedItem();
      int firstSpaceIndex = selectedItem.indexOf(" ");
      selectedItem = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("H"));
      System.out.println(selectedItem);
      
      String name;
      String hrRate;
      String tHours;
      String Reason;
      String position;
      
      
      
      for(Employee emp: empList){
          System.out.println("EMP ID: "+emp.getId()+" "+"Selected ID: "+selectedItem);
          if(emp.getId().trim().equals(selectedItem.trim())){
              name = emp.getFirstname() + " " + emp.getLastname();
              hrRate = Integer.toString(emp.getOverTime().getOvertimeRate());
              tHours = Integer.toString(emp.getOverTime().getOvertimeHours());
              Reason = emp.getOverTime().getOvertimeReason();
              position = emp.getEmpType();
              ovt = new Overtime(emp.getId(),true,Integer.parseInt(tHours),Integer.parseInt(hrRate),
              false,"");
              String formattedText = String.format("Name: %s\nHourly Rate: %s\nTotal Hours: %s\nReason: %s\nPosition: %s",
        name, hrRate, tHours, Reason, position);
              expandLabelOvt.setText(formattedText);
              switchToOvertime.setVisible(true);
              ovtRequestPane.setVisible(true);
              return;
          } 
      }
      
      

    }

    @FXML
    private void closeOvtRequestPane(ActionEvent event) {
        expandLabelOvt.setText("");
        ovtRequestPane.setVisible(false);
        switchToOvertime.setVisible(true);
    }

    @FXML
    private void acceptRequestOvt(ActionEvent event) {
        for(Employee emp: empList){
          if(emp.getId().trim().equals(selectedItem.trim())){
             ArrayList<Overtime> ot = Overtime.deleteOvertimeById("Overtime.bin", emp.getId());
             try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Overtime.bin"))) {
                // Write the updated list back to the file
                outputStream.writeObject(ot);
            } catch (IOException e) {
                e.printStackTrace();
            }
              EmployeeDashboardController.updateEmployeeOvertime(empList, myId, ovt);
                ovtRequestPane.setVisible(false);
                switchToOvertime.setVisible(true);
                expandLabelOvt.setText("");
                items.clear(); 
              return;
          }
        }
        
    }

    @FXML
    private void denaiRequestOvt(ActionEvent event) {
        for(Employee emp: empList){
          if(emp.getId().trim().equals(selectedItem.trim())){
              ovt = new Overtime(emp.getId(),false,0,0,
              false,"");
              ArrayList<Overtime> ot = Overtime.deleteOvertimeById("Overtime.bin", emp.getId());
             try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Overtime.bin"))) {
                // Write the updated list back to the file
                outputStream.writeObject(ot);
            } catch (IOException e) {
                e.printStackTrace();
            }
              EmployeeDashboardController.updateEmployeeOvertime(empList, myId, ovt);
              ovtRequestPane.setVisible(false);
              switchToOvertime.setVisible(true);
              expandLabelOvt.setText("");
              items.clear();
              return;
          } 
        }
        
    }

    @FXML
    private void toSalary(ActionEvent event) throws IOException {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/SalaryRequest.fxml"));
            root = loader.load();
            SalaryRequestController controller = loader.getController();

            controller.setEmpID(myId);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
        
    }

    
}
