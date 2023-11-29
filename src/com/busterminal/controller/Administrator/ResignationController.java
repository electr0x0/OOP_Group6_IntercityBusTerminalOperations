package com.busterminal.controller.Administrator;

import com.busterminal.model.Resignation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ResignationController {

    @FXML
    private ListView<Resignation> resignationList;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Button acceptButton;

    @FXML
    private Button viewReasonButton;

    // Add your logic to initialize the resignationList with employee names, IDs, and reasons
    @FXML
    private void initialize() {
        // Sample data (replace with your actual data)
        resignationList.getItems().addAll(
                new Resignation(1565666, "Samin", "Personal reasons"),
                new Resignation(2454651, "Alif", "New job opportunity"),
                new Resignation(379845, "Mantaka", "Relocation")
        );
    }

    @FXML
    private void acceptResignation() {
        Resignation selectedResignation = resignationList.getSelectionModel().getSelectedItem();
        reasonTextArea.setText("Accepted resignation for: " + selectedResignation.getEmpName());
    }

    @FXML
    private void viewReason() {
        Resignation selectedResignation = resignationList.getSelectionModel().getSelectedItem();
        reasonTextArea.setText("Reason for resignation:\nEmployee: " +
                selectedResignation.getEmpName() +
                "\nReason: " + selectedResignation.getLetter());
    }
}
