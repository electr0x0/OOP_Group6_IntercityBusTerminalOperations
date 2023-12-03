/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import com.busterminal.model.Employee;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.Helper;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerViewEmployeeStatsController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    
    
    
    @FXML
    private Pane tileEmployeeCount;
    @FXML
    private Pane tileEmployeeAverageByDesig;
    @FXML
    private Pane tileEmployeeTurnOverRate;
    
    private ArrayList<Employee> allEmpList;
    
    private int managerQty, accQty, hrmQty, mtQty, driverQty, adminQty, terminalManagerQty;
    
    private int managerSalTotal, accSalTotal, hrmSalTotal, mtSalTotal, driverSalTotal, adminSalTotal, terminalManagerSalTotal;
    
    private double managerSalAvg, accSalAvg, hrmSalAvg, mtSalAvg, driverSalAvg, adminSalAvg, terminalManagerSalAvg;
    
    private int otManager, otAcc, otHrm, otMt, otDriver, otAdmin, otTerminalManager;
    
    
    // turn over calculation
    private int totalEmp, employeeWhoLeft, employeeAtCurrent;
    @FXML
    private Pane overTimeTile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TransitionUtility.materialScale(tileEmployeeCount);
        TransitionUtility.materialScale(tileEmployeeAverageByDesig);
        TransitionUtility.materialScale(tileEmployeeTurnOverRate);
        TransitionUtility.materialScale(overTimeTile);
        
        allEmpList = Employee.readEmployeesFromFile("MyEmployee.bin");
        calcEmpStats();
        if(allEmpList == null){
            MFXDialog.showErrorDialog("Failed to load employee Data", "Employee Data loading failed", rootPane);
            return;
        }
    }
    
    public void calcEmpStats(){
        for(Employee empObj: allEmpList){
            totalEmp++;
            if(empObj.getResignation().getAskedForResignation()){
                employeeWhoLeft++;
            }
            switch(empObj.getEmpType()){
                case "Manager":
                    managerQty++;
                    managerSalTotal += empObj.getSalary();
                    otManager += empObj.getOverTime().getOvertimeHours();
                    break;
                case "Accountant":
                    accQty++;
                    accSalTotal += empObj.getSalary();
                    otAcc += empObj.getOverTime().getOvertimeHours();
                    break;
                case "Human Resource":
                    hrmQty++;
                    hrmSalTotal += empObj.getSalary();
                    break;
                case "Maintainence Staff":
                    mtQty++;
                    mtSalTotal += empObj.getSalary();
                    otMt += empObj.getOverTime().getOvertimeHours();
                    break;
                case "Driver":
                    driverQty++;
                    driverSalTotal += empObj.getSalary();
                    otDriver += empObj.getOverTime().getOvertimeHours();
                    break;
                case "Administrator":
                    adminQty++;
                    adminSalTotal += empObj.getSalary();
                    otAdmin += empObj.getOverTime().getOvertimeHours();
                    break;
                case "Terminal Manager":
                    terminalManagerQty++;
                    terminalManagerSalTotal += empObj.getSalary();
                    otTerminalManager += empObj.getOverTime().getOvertimeHours();
                    break;
            }
        }
        if(managerQty != 0){
            managerSalAvg = managerSalTotal / managerQty;
        }
        if(accQty != 0){
            accSalAvg = accSalTotal / accQty;
        }
        if(hrmQty != 0){
            hrmSalAvg = hrmSalTotal / hrmQty;
        }
        if(mtQty != 0){
            mtSalAvg = mtSalTotal / mtQty;
        }
        if(driverQty != 0){
            driverSalAvg = driverSalTotal / driverQty;
        }
        if(adminQty != 0){
            adminSalAvg = adminSalTotal / adminQty;
        }
        if(terminalManagerQty != 0){
            terminalManagerSalAvg = terminalManagerSalTotal / terminalManagerQty;
        }
        
        setupEmployeeCountChart();
        setupEmployeeSalaryDistribution();
        setupTurnOverRateTile();
        setupOvertimePieChart();
    }
    
    private void setupEmployeeCountChart(){
        BarChartItem managerCount = new BarChartItem("Manager", managerQty, Tile.BLUE);
        BarChartItem accCount = new BarChartItem("Accountant", accQty, Tile.GREEN);
        BarChartItem hrCount = new BarChartItem("Human Resource", hrmQty, Tile.MAGENTA);
        BarChartItem mtStaffCount = new BarChartItem("Maintainence Staff", mtQty, Tile.RED);
        BarChartItem adminCount = new BarChartItem("Administrator", adminQty, Tile.PINK);
        BarChartItem terminalManagerCount = new BarChartItem("Terminal Manager", terminalManagerQty, Tile.DARK_BLUE);
        BarChartItem driverCount = new BarChartItem("Driver", driverQty, Tile.DARK_BLUE);
        
        Tile employeeCountBarChart = TileBuilder.create()
                                  .skinType(Tile.SkinType.BAR_CHART)
                                  .prefSize(tileEmployeeCount.getPrefWidth(), tileEmployeeCount.getPrefHeight())
                                  .animated(true)
                                  .title("Current Employee Count by Designation")
                                  .barChartItems(managerCount,accCount,hrCount,mtStaffCount,adminCount,terminalManagerCount,driverCount)
                                  .decimals(0)
                                  .autoScale(true)
                                  .backgroundColor(Color.TRANSPARENT)
                                  .textColor(Color.WHITE)
                                  .titleColor(Color.WHITE)
                                  .sortedData(true)
                                  .titleAlignment(TextAlignment.CENTER)
                                  .build();
        tileEmployeeCount.getChildren().clear();
        tileEmployeeCount.getChildren().setAll(employeeCountBarChart);
    }
    
    
    private void setupEmployeeSalaryDistribution(){
        
        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.setName("Average Salary");
        series1.getData().add(new XYChart.Data("Manager",managerSalAvg));
        series1.getData().add(new XYChart.Data("Accountant", accSalAvg));
        series1.getData().add(new XYChart.Data("Human Resource", hrmSalAvg));
        series1.getData().add(new XYChart.Data("Maintainence Staff", mtSalAvg));
        series1.getData().add(new XYChart.Data("Administrator", adminSalAvg));
        series1.getData().add(new XYChart.Data("Terminal Manager", terminalManagerSalAvg));
        series1.getData().add(new XYChart.Data("Driver", driverSalAvg));
        
        
        
        Tile salaryByDesig = TileBuilder.create()
                                   .skinType(Tile.SkinType.SMOOTHED_CHART)
                                   .prefSize(tileEmployeeAverageByDesig.getPrefWidth(), tileEmployeeAverageByDesig.getPrefHeight())
                                   .title("Employee Salary Distribution by Designation")
                                   .titleAlignment(TextAlignment.CENTER)
                                   .animationDuration(50000)
                                   .animated(true)
                                   .dataPointsVisible(true)
                                   .smoothing(true)
                                   .backgroundColor(Color.TRANSPARENT)
                                   .series(series1)
                                   .build();
        
        tileEmployeeAverageByDesig.getChildren().clear();
        tileEmployeeAverageByDesig.getChildren().setAll(salaryByDesig);
    }
    
    private void setupTurnOverRateTile(){
        employeeAtCurrent = totalEmp - employeeWhoLeft;
        
        
        //double turnOverRate = ((employeeWhoLeft) / ((totalEmp + employeeAtCurrent) /2)) * 100;
        
        System.out.println(employeeAtCurrent+" "+ totalEmp+" "+ employeeWhoLeft);
        
        Tile turnOrate = TileBuilder.create()
                                    .skinType(SkinType.GAUGE_SPARK_LINE)
                                    .prefSize(tileEmployeeTurnOverRate.getPrefWidth(), tileEmployeeTurnOverRate.getPrefHeight())
                                    .title("Employee Turnover Rate")
                                    .titleAlignment(TextAlignment.CENTER)
                                    .unit("%")
                                    .animated(true)
                                    .animationDuration(10000)
                                    .maxValue(100)
                                    .backgroundColor(Color.TRANSPARENT)
                                    .build();
        
        turnOrate.setValue(50);
        
        tileEmployeeTurnOverRate.getChildren().clear();
        tileEmployeeTurnOverRate.getChildren().addAll(turnOrate);
        
    }
    
    private void setupOvertimePieChart(){
        ChartData managerOt = new ChartData("Manager", otManager, Tile.BLUE);
        ChartData accOt = new ChartData("Accountant", otAcc, Tile.GREEN);
        ChartData hrOt = new ChartData("Human Resource", otHrm, Tile.MAGENTA);
        ChartData mtOt = new ChartData("Maintainence Staff", otMt, Tile.RED);
        ChartData adminOt = new ChartData("Administrator", otAdmin, Tile.PINK);
        ChartData terminalManagerOt = new ChartData("Terminal Manager", otTerminalManager, Tile.DARK_BLUE);
        ChartData driverOt = new ChartData("Driver", otDriver, Tile.DARK_BLUE);
        // Demo Data
//        ChartData managerOt = new ChartData("Manager", 40, Tile.BLUE);
//        ChartData accOt = new ChartData("Accountant", 50, Tile.GREEN);
//        ChartData hrOt = new ChartData("Human Resource", 20, Tile.MAGENTA);
//        ChartData mtOt = new ChartData("Maintainence Staff", 10, Tile.RED);
//        ChartData adminOt = new ChartData("Administrator", 30, Tile.PINK);
//        ChartData terminalManagerOt = new ChartData("Terminal Manager", 70, Tile.DARK_BLUE);
//        ChartData driverOt = new ChartData("Driver", 80, Tile.YELLOW);
        
        Tile overTimepieChart = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .animated(true)
                .prefSize(overTimeTile.getPrefWidth(), overTimeTile.getPrefHeight())
                .title("Overtime Data by Designation")
                .titleAlignment(TextAlignment.CENTER)
                .chartData(managerOt, accOt,hrOt,mtOt,adminOt,terminalManagerOt,driverOt)
                .backgroundColor(Color.TRANSPARENT)
                .titleColor(Color.WHITE)
                .textColor(Color.WHITE)
                .smoothing(true)
                .autoScale(true)
                .build();
        
        overTimeTile.getChildren().clear();
        
        overTimeTile.getChildren().setAll(overTimepieChart);
    }
    
}
