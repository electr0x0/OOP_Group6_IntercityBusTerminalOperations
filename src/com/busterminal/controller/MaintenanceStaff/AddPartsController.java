package com.busterminal.controller.MaintenanceStaff;
import com.busterminal.model.Database;
import com.busterminal.model.Parts;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddPartsController implements Initializable {

    @FXML
    private TableView<Parts> stockTable;
    @FXML
    private TableColumn<Parts, String> partsNameCol;
    @FXML
    private TableColumn<Parts, String> partsModelCol;
    @FXML
    private TableColumn<Parts, Integer> priceCol;
    @FXML
    private TableColumn<Parts, String> catagoryCol;
    @FXML
    private Label catagoryLabel;
    @FXML
    private TextField partsNameTF;
    @FXML
    private Label partsNameLabel;
    @FXML
    private TextField partsModelTF;
    @FXML
    private Label partsModelLabel;
    @FXML
    private TextField partsPriceTF;
    @FXML
    private Label priceLabel;
    @FXML
    private ComboBox<String> filterCB;
    @FXML
    private ComboBox<String> catagoryCB;

    private ObservableList<Parts> partsDataList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterCB.getItems().addAll("Category1", "Category2", "Category3");
        catagoryCB.getItems().addAll("Category1", "Category2", "Category3", "Category4");

        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsModelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        catagoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        //loadDummyData();
        stockTable.getItems().addAll(Database.getInstanceBinFile("PartsList.bin"));
        
        partsDataList = FXCollections.observableArrayList();
        partsDataList.addAll(stockTable.getItems());
  
    }

    private void clearInputFields() {
        partsNameTF.clear();
        partsModelTF.clear();
        partsPriceTF.clear();
        catagoryCB.setValue(null);
    }

    @FXML
    private void AddButtonOnMouseClick(ActionEvent event) {
        String partsName = partsNameTF.getText();
        String partsModel = partsModelTF.getText();
        String partsPrice = partsPriceTF.getText();
        String catagory = catagoryCB.getValue();

        if (Parts.validateInput(partsName, partsModel, partsPrice, catagory)) {
            Parts newParts = new Parts(partsName, partsModel, Integer.parseInt(partsPrice), catagory); 
            partsDataList.add(newParts);
             Database.writeToBinFile("PartsList.bin", partsDataList);
            clearInputFields();
            stockTable.setItems(partsDataList);
        }

    }

    @FXML
    private void clearFilterOnMouseClick(ActionEvent event) {
        filterCB.setValue(null);
    }

    @FXML
    private void filterCBOnMouseClick(ActionEvent event) {
        String selectedCategory = filterCB.getValue();
        if (selectedCategory != null) {
            ObservableList<Parts> filteredData = FXCollections.observableArrayList();

            for (Parts parts : partsDataList) {
                if (parts.getCategory().equals(selectedCategory)) {
                    filteredData.add(parts);
                }
            }

            stockTable.setItems(filteredData);
        } else {
            stockTable.setItems(partsDataList);
        }
    }

}
