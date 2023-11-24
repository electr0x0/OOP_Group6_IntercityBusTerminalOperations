package com.busterminal.controller.Administrator;
import com.busterminal.model.Database;
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
        resignationList.setItems(Database.getInstanceBinFile("Resignation.bin"));
    }

    @FXML
    private void acceptResignation() {
        Resignation selectedResignation = resignationList.getSelectionModel().getSelectedItem();
        reasonTextArea.setText("Accepted resignation for: " + selectedResignation.getEmpName());
    }

    @FXML
    private void viewReason() {
        Resignation selectedResignation = resignationList.getSelectionModel().getSelectedItem();
        reasonTextArea.setText("Employee: " +
                selectedResignation.getEmpName() +"\nSubject: "+selectedResignation.getSubject()+
                "\nReason: " +"\n" +selectedResignation.getLetter());
    }
}
