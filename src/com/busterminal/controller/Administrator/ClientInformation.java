package com.busterminal.controller.Administrator;

import com.busterminal.model.Administrator;
import com.busterminal.model.Client;
import com.busterminal.model.PopUp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientInformation implements Initializable {

    @FXML
    private TableView<Client> passengerTable;
    @FXML
    private TextField clientNameSearchbar;
    @FXML
    private TableColumn<Client, Integer> clientIdCol;
    @FXML
    private TableColumn<Client, String> clientNameCol;
    @FXML
    private TableColumn<Client, String> clientAddressCol;
    @FXML
    private TableColumn<Client, String> clientEmailCol;
    @FXML
    private TableColumn<Client, String> clientGenderCol;
    @FXML
    private TableColumn<Client, String> clientPasswordCol;
    private ObservableList<Client> loadData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        clientNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clientAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        clientEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        clientGenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clientPasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        loadData = Administrator.getClientList();
        System.out.print(loadData);

        for (Client c : loadData) {
            passengerTable.getItems().add(c);
        }

    }

    @FXML
    private void searchByClientNameOnMouseClick(ActionEvent event) {
        ObservableList<Client> cTable = passengerTable.getItems();
        String selectedCategory = clientNameSearchbar.getText();
        if (selectedCategory != null) {
            ObservableList<Client> filteredData = FXCollections.observableArrayList();

            for (Client c : cTable) {
                if (c.getFirstName().equals(selectedCategory)) {
                    filteredData.add(c);
                } else {
                    PopUp.showMessage("Information", "Client Not Found..!");
                    clientNameSearchbar.clear();

                }

            }

            passengerTable.setItems(filteredData);
        } else {
            passengerTable.setItems(loadData);
        }
    }
}
