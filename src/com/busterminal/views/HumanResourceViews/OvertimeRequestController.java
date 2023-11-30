/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.views.Employee.EmployeeDashboardController;
import com.busterminal.views.Employee.EmployeeOvertimeController;
import static com.busterminal.views.HumanResourceViews.MyEmployeeController.readEmployeesFromFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class OvertimeRequestController implements Initializable {

    @FXML
    private AnchorPane switchToOvertime;
    @FXML
    private ListView<String> OvertimeView;
    @FXML
    private AnchorPane ovtRequestPane;
    @FXML
    private Label expandLabelOvt;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    Overtime ovt;
    String selectedItem;
    ObservableList<String> items = FXCollections.observableArrayList();
    
     ArrayList<Overtime> ovtList = new ArrayList<>();
     ArrayList<Employee> empList = new ArrayList<>();
     
     String empID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    }    

    @FXML
    private void myEmployees(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/MyEmployee.fxml"));
            root = loader.load();
            MyEmployeeController controller = loader.getController();

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

    @FXML
    private void toResign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/ResignationRequest.fxml"));
            root = loader.load();
            ResignationRequestController controller = loader.getController();

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
              name = emp.getFirstName() + " " + emp.getLastName();
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
              EmployeeOvertimeController.updateEmployeeOvertime(empList, empID, ovt);
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
        Alert a = new Alert(Alert.AlertType.WARNING,"Are you sure you want to cancel your overtime?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.YES){
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
                        EmployeeOvertimeController.updateEmployeeOvertime(empList, empID, ovt);
                        ovtRequestPane.setVisible(false);
                        switchToOvertime.setVisible(true);
                        expandLabelOvt.setText("");
                        items.clear();
                        return;
                     } 
        
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
    
    
}
