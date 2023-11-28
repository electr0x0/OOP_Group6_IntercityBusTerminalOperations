/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.terminalManager;

import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
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
    private MFXLegacyTableView<?> empMangeTableView;
    @FXML
    private TableColumn<?, ?> colEmpName;
    @FXML
    private TableColumn<?, ?> colEmpID;
    @FXML
    private TableColumn<?, ?> colEmpDesignation;
    @FXML
    private TableColumn<?, ?> colEmpSalary;
    @FXML
    private TableColumn<?, ?> colEmpStatus;
    @FXML
    private TableColumn<?, ?> colEmployeePerformanceRep;
    @FXML
    private MFXTextField txtFieldSalaryModificationAmt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickPromotionRecommendation(ActionEvent event) {
    }

    @FXML
    private void onClickIncrementSalary(ActionEvent event) {
    }

    @FXML
    private void onClickDecrementSalary(ActionEvent event) {
    }
    
}
