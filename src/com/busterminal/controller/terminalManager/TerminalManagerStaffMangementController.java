/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Employee;
import com.busterminal.model.SceneSwicth;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerStaffMangementController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXLegacyTableView<Employee> empMangeTableView;
    @FXML
    private TableColumn<Employee, String> colEmpName;
    @FXML
    private TableColumn<Employee, String> colEmpID;
    @FXML
    private TableColumn<Employee, String> colEmpDesignation;
    @FXML
    private TableColumn<Employee, Integer> colEmpSalary;
    @FXML
    private TableColumn<Employee, Boolean> colEmpStatus;
    @FXML
    private TableColumn<Employee, String> colEmployeePerformanceRep;
    @FXML
    private ImageView imageViewGear;
    @FXML
    private TableColumn<Employee, String> colEmployeeFirstName;
    @FXML
    private TableColumn<Employee, String> colEmployeeLastName;
    
    private ArrayList<Employee> allAvailableEmployee;
    
    private ObservableList<Employee> employeeListTableView = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        setupTable();
        allAvailableEmployee = Employee.readEmployeesFromFile("MyEmployee.bin");
        if (allAvailableEmployee != null){
            employeeListTableView.setAll(allAvailableEmployee);
            empMangeTableView.setItems(employeeListTableView);
        }
    }
    
    private void setupTable(){
        colEmployeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colEmployeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpDesignation.setCellValueFactory(new PropertyValueFactory<>("empType"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colEmpStatus.setCellValueFactory(new PropertyValueFactory<>("onLeave"));
        colEmployeePerformanceRep.setCellValueFactory(new PropertyValueFactory<>("performance"));
    }


    @FXML
    private void onClickShowContextMenu(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem option1 = new MenuItem("View Employee Stats");
        option1.setOnAction(x -> 
                SceneSwitch("/com/busterminal/views/terminalManagerUser/TerminalManagerViewEmployeeStats.fxml"));
        
        contextMenu.getItems().add(option1);
        contextMenu.show(imageViewGear,event.getScreenX(),event.getScreenY());
    }
    
    private void SceneSwitch(String fxmllocation) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource(fxmllocation));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path",rootPane);
        }
    }

    @FXML
    private void onClickSendAudit(ActionEvent event) {
        
        Employee selectedEmp = empMangeTableView.getSelectionModel().getSelectedItem();
        if (selectedEmp != null){
            sceneSwitchToAudit();
        } else{
            MFXDialog.showErrorDialog("No selection", "Please select an specific employee for Audit!", rootPane);
        }
    }
    
    private void sceneSwitchToAudit(){
        try {
            // Load the new content FXML file
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerEmployeeAuditViewEdit.fxml"));
            AnchorPane newContent = loader.load();
            
            TerminalManagerEmployeeAuditViewEditController controller = loader.getController();
            
            controller.getDatafromSceneSwitch(allAvailableEmployee,empMangeTableView.getSelectionModel().getSelectedItem());
            
            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("FXML not Found","Unable to swtich scene, make sure FXML is present in the specified path", rootPane);
        }
    }
    
}
