/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Employee;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.addons.Indicator;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerEmployeeAuditViewEditController implements Initializable {

    private ArrayList<Employee> allAvailableEmployee;
    
    private Employee currentEmployee;
    @FXML
    private AnchorPane anchorAudit;
    @FXML
    private TextArea textAreaAuditReport;
    @FXML
    private AnchorPane anchorStats;
    @FXML
    private Pane tilePaneFirst;
    @FXML
    private Pane tilePaneSecond;
    private Pane tilePaneThird;
    @FXML
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void getDatafromSceneSwitch(ArrayList<Employee> allAvailableEmployee,Employee currentEmployee ){
        this.allAvailableEmployee = allAvailableEmployee;
        this.currentEmployee = currentEmployee;
        textAreaAuditReport.setText(currentEmployee.getPerformance());
        
    }
    
    private void setSalaryData(){

        Indicator leaveStatus = new Indicator(Tile.RED, Tile.RED);
        leaveStatus.setOn(currentEmployee.getOnLeave());
        
        Indicator isPaid = new Indicator(Tile.BLUE,Tile.RED);
        isPaid.setOn(currentEmployee.getSalStatus().getAskedForSalary());
        
        
        
        Tile salaryIncrement = TileBuilder.create()
                                    .skinType(Tile.SkinType.STATUS)
                                    .prefSize(tilePaneFirst.getPrefWidth(), tilePaneFirst.getPrefHeight())
                                    .title("Employee Information")
                                    .titleAlignment(TextAlignment.CENTER)
                                    .animated(true)
                                    .leftText("On leave Status")
                                    .leftGraphics(leaveStatus)
                                    .rightText("Asked for increment?")
                                    .middleValue(currentEmployee.getSalary())
                                    .middleText("Employee Salary")
                                    .rightGraphics(isPaid)
                                    .backgroundColor(Color.TRANSPARENT)
                                    .build();

        tilePaneSecond.getChildren().clear();
        tilePaneSecond.getChildren().setAll(salaryIncrement);
    }
    
    private void setOverTimeData(){
        int overTimeHours = currentEmployee.getOverTime().getOvertimeHours();
        Tile overTimeHour = TileBuilder.create()
                                    .skinType(Tile.SkinType.GAUGE_SPARK_LINE)
                                    .prefSize(tilePaneFirst.getPrefWidth(), tilePaneFirst.getPrefHeight())
                                    .title("Employee Overtime Information")
                                    .titleAlignment(TextAlignment.CENTER)
                                    .unit("Hour(s)")
                                    .animated(true)
                                    .animationDuration(10000)
                                    .maxValue(100)
                                    .backgroundColor(Color.TRANSPARENT)
                                    .build();
        
        overTimeHour.setValue(overTimeHours);
        tilePaneFirst.getChildren().clear();
        tilePaneFirst.getChildren().setAll(overTimeHour);
    }

    @FXML
    private void onClickSetAuditReport(ActionEvent event) {
        if(!textAreaAuditReport.getText().isEmpty()){
            for(Employee empobj: allAvailableEmployee){
                if(empobj.getId().equals(currentEmployee.getId())){
                    empobj.setPerformance(textAreaAuditReport.getText());
                    MFXDialog.showSuccessDialog("Success!", "Employee Audit Data Updated Successfully!", rootPane);
                    Employee.writeEmployeesToFile("MyEmployee.bin", allAvailableEmployee);
                    break;
                }
            }
        }
        else{
            MFXDialog.showErrorDialog("Empty Report!", "Please fill the up the audit report before trying to set it!", rootPane);
        }
    }

    @FXML
    private void selectionChangeAnimForTiles(Event event) {
        TransitionUtility.materialScale(tilePaneFirst);
        TransitionUtility.materialScale(tilePaneSecond);
        TransitionUtility.materialScale(tilePaneThird);
        setOverTimeData();
        setSalaryData();
    }

    @FXML
    private void onClickGoBack(MouseEvent event) {
        try {
            // Load the new content FXML file
            AnchorPane newContent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/terminalManagerUser/TerminalManagerStaffMangement.fxml"));

            // Clear existing content and set the new content
            rootPane.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path",rootPane);
        }
    }
    
}
