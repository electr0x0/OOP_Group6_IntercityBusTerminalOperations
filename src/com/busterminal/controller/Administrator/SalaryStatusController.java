package com.busterminal.controller.Administrator;

import com.busterminal.model.Employee;
import com.busterminal.model.dummySalaryStatusClass;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalaryStatusController implements Initializable {

    @FXML
    private TableView<dummySalaryStatusClass> employeeTable;
    @FXML
    private TableColumn<dummySalaryStatusClass, Integer> employeIdCol;
    @FXML
    private TableColumn<dummySalaryStatusClass, String> nameCol;
    @FXML
    private TableColumn<dummySalaryStatusClass, String> designationCol;
    @FXML
    private TableColumn<dummySalaryStatusClass, Integer> salaryCol;
    @FXML
    private TableColumn<dummySalaryStatusClass, String> statusCol;
    @FXML
    private CheckBox selectUnpaid;
    @FXML
    private CheckBox selectPaid;
    @FXML
    private ComboBox<String> filterCB;
    @FXML
    private TextField filterTextField;

    private ObservableList<dummySalaryStatusClass> allEmployeeData;
    @FXML
    private TableColumn<dummySalaryStatusClass, LocalDate> paymentDate;
    @FXML
    private TextArea totalTextArea;

    private ArrayList<Employee> employeeData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeData = new ArrayList<>();
        filterCB.getItems().addAll("Employee Name", "Employee Id", "Date");

        employeIdCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("paidStatus"));

        // employee
        employeeData = Employee.readEmployeesFromFile("MyEmployee.bin");
        for (Employee e : employeeData) {
            dummySalaryStatusClass empIntanse = new dummySalaryStatusClass(e.getID(), e.getFirstName(), e.getEmpType(), e.getSalary(), e.getSalStatus().getLastPaid(), e.getSalStatus().getIsPaid() ? "Paid" : "Unpaid");
            // Mantaka have to add paidSalaryDate
            employeeTable.getItems().add(empIntanse);
        }
    }

    @FXML
    private void totalAmountShowInTableColumnSalaryOnMouseClick(ActionEvent event) {
        totalTextArea.clear();
        // Calculate and display the total amount for the filtered data
        int totalAmount = calculateTotalAmount();
        totalTextArea.appendText(Integer.toString(totalAmount));
    }

    @FXML
    private void filterOnMouseClick(ActionEvent event) {
        // Filter and display data based on the selected criteria
        filterData();
    }

    private void filterData() {
        // create a dummy obserable list to filter the data 
        ObservableList<dummySalaryStatusClass> filteredData = FXCollections.observableArrayList();

        String selectedFilter = filterCB.getValue();
        String filterValue = filterTextField.getText().toLowerCase();

        for (dummySalaryStatusClass employee : allEmployeeData) {
            switch (selectedFilter) {
                case "Employee Name":
                    if (employee.getFirstName().toLowerCase().contains(filterValue)) {
                        filteredData.add(employee);
                    }
                    break;
                case "Employee Id":
                    if (String.valueOf(employee.getID()).contains(filterValue)) {
                        filteredData.add(employee);
                    }
                    break;
                case "Date":
                    if (String.valueOf(employee.getPaymentDate()).contains(filterValue)) {
                        filteredData.add(employee);
                    }
                    break;
            }
        }

        // Apply additional filters based on checkboxes
        if (selectPaid.isSelected()) {

            // remove from paid status data after filter the selected combobox type and text value wise
            filteredData.removeIf(employee -> !employee.getPaidStatus().equalsIgnoreCase("Paid"));
        }
        if (selectUnpaid.isSelected()) {
            filteredData.removeIf(employee -> !employee.getPaidStatus().equalsIgnoreCase("Unpaid"));

            /*
            The lambda expression employee -> !employee.getPaidStatus().equalsIgnoreCase("Paid")
            is a concise way to define a predicate (condition) for the removeIf method.
            It checks if the "PaidStatus" attribute of an object (employee) is not equal (ignoring case) to "Paid." 
            If true, the element is removed from the list.
             */
        }

        if (selectPaid.isSelected() && selectUnpaid.isSelected()) {

        }

        employeeTable.setItems(filteredData);
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        // Add All data after filtered and get the total 
        ObservableList<dummySalaryStatusClass> filteredData = employeeTable.getItems();

        // Now traverse throgh the filterData list take salary and increase the total Amount local field
        for (dummySalaryStatusClass employee : filteredData) {
            totalAmount += employee.getSalary();
        }
        return totalAmount;
    }

}
