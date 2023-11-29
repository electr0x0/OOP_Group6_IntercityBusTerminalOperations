package com.busterminal.controller.Administrator;

import com.busterminal.model.Administrator;
import com.busterminal.model.Client;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.MaintenanceStaff;
import com.busterminal.model.PopUp;
import com.busterminal.model.Resignation;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;

public class ResignationController {

    @FXML
    private ListView<Resignation> resignationList;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Button acceptButton;

    @FXML
    private Button viewReasonButton;

    private ObservableList<Resignation> resignationRequestList;
    
    @FXML
    private void initialize() {
     
        resignationList.getItems().addAll(
                new Resignation(1565666, "Samin", "Personal reasons"),
                new Resignation(2454651, "Alif", "New job opportunity"),
                new Resignation(379845, "Mantaka", "Relocation")
        );
        
        resignationRequestList= getResignList();
        resignationList.getItems().addAll(resignationRequestList);
        
        
    }

    @FXML
    private void acceptResignation() {
        Resignation selectedItem = resignationList.getSelectionModel().getSelectedItem();
        ObservableList<Resignation> selectedRows, allPeople;
        allPeople = resignationList.getItems();
        selectedRows = resignationList.getSelectionModel().getSelectedItems();
        for (Resignation r : selectedRows) {
            allPeople.remove(r);
        }
        
       
        
    }

    @FXML
    private void viewReason() {
        Resignation selectedResignation = resignationList.getSelectionModel().getSelectedItem();
        reasonTextArea.setText("Reason for resignation:\nEmployee: " +
                selectedResignation.getEmpName() +
                "\nReason: " + selectedResignation.getLetter());
    }
    
    public static ObservableList<Resignation> getResignList() {
        ObjectInputStream ois = null;
        ObservableList<Resignation> list = FXCollections.observableArrayList();
        try {
            Resignation c;
            ois = new ObjectInputStream(new FileInputStream("Resignation.bin"));

            while (true) {
                c = (Resignation) ois.readObject();
                list.add(c);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return list;
    }
}
