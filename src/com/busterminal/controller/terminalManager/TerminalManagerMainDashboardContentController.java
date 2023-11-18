/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class TerminalManagerMainDashboardContentController implements Initializable {

    @FXML
    private TextField txtFieldSearchBar;
    @FXML
    private LineChart<?, ?> salesLineChart;
    @FXML
    private PieChart pieChartDashboard;
    @FXML
    private Label labelTotalClientCounter;
    @FXML
    private Label lablelTotalStaffCounter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
