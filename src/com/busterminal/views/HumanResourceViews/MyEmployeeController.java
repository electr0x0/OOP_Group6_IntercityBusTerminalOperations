/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.HumanResourceViews;

import com.busterminal.controller.MaintenanceStaff.CheckMaintenanceTaskController;
import com.busterminal.model.BulletinMassage;
import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Overtime;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.views.Employee.EmployeeDashboardController;
import com.busterminal.views.Employee.EmployeeOvertimeController;
import com.busterminal.views.NoticiationController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MyEmployeeController implements Initializable {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> employeTableId;
    @FXML
    private TableColumn<Employee, String> tableFirstName;
    @FXML
    private TableColumn<Employee, String> tableLastName;
    @FXML
    private TableColumn<Employee, String> tableEmail;
    @FXML
    private TableColumn<Employee, String> tablePhone;
    @FXML
    private TableColumn<Employee, Integer> tableSalary;
    @FXML
    private TableColumn<Employee, String> tableDesignation;
    @FXML
    private TableColumn<Employee, Boolean> tableAbsent;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ArrayList<Employee> empList = new ArrayList<>();
    ObservableList<Employee> observableEmployeeList = FXCollections.observableArrayList(empList);
    @FXML
    private TextField searchBox;
    @FXML
    private ChoiceBox<String> sortChoice;
    String getChoices[] = {"ID", "First Name", "Last Name", "Email", "Salary", "Designation"};

    String myId;
    @FXML
    private AnchorPane EmployeeListPane;

    private ListView<String> OvertimeView;
    private AnchorPane OvertimePane;
    private AnchorPane switchToOvertime;
    private AnchorPane ovtRequestPane;
    private Label expandLabelOvt;

    Overtime ovt;
    String selectedItem;
    ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    private PieChart SalaryChart;
    @FXML
    private AnchorPane toCharts;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yaxis;
    @FXML
    private CategoryAxis xAxis;

    private int managerSalTotal, accSalTotal, hrmSalTotal, mtSalTotal, driverSalTotal, adminSalTotal, terminalManagerSalTotal;

    private int totalEmp, employeeWhoLeft;
    @FXML
    private Label countLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myId = RelationshipDatabaseClass.getInstance().getCurrentLoggedIn();
        
        //Maruf
        countLabel.setText(Integer.toString(BulletinMassage.countUpcomingMassage(RelationshipDatabaseClass.getInstance().getCurrentUserEmail())));
        
        sortChoice.getItems().addAll(getChoices);
        sortChoice.setValue("ID");

        empList = readEmployeesFromFile("MyEmployee.bin");
        if (empList != null) {
            RelationshipDatabaseClass.getInstance().setAllEmployees(empList);
        }

        employeTableId.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        tableFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        tableLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        tablePhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tableSalary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee, String>("empType"));
        tableAbsent.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("onLeave"));

        observableEmployeeList.addAll(empList);

        employeeTable.setItems(observableEmployeeList);

        int paid = 0;
        int unpaid = 0;
        for (Employee emp : empList) {
            if (emp.getIsPaid() == false) {
                unpaid += 1;
            } else {
                paid += 1;
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Paid", paid),
                new PieChart.Data("Unpaid", unpaid)
        );
        SalaryChart.setData(pieChartData);
        SalaryChart.setTitle("Paid Employees Chart");

        //Bar Chart
        for (Employee empObj : empList) {
            totalEmp++;
            switch (empObj.getEmpType()) {
                case "Manager":
                    managerSalTotal += empObj.getSalary();
                    break;
                case "Accountant":
                    accSalTotal += empObj.getSalary();
                    break;
                case "Human Resource":
                    hrmSalTotal += empObj.getSalary();
                    break;
                case "Maintainence Staff":
                    mtSalTotal += empObj.getSalary();
                    break;
                case "Driver":
                    driverSalTotal += empObj.getSalary();
                    break;
                case "Administrator":
                    adminSalTotal += empObj.getSalary();
                    break;
                case "Terminal Manager":
                    terminalManagerSalTotal += empObj.getSalary();
                    break;
            }
        }

        XYChart.Series<String, Number> chart = new XYChart.Series<>();
        chart.setName("Department Expendeture");
        chart.getData().add(new BarChart.Data("Administrator", adminSalTotal));
        chart.getData().add(new BarChart.Data("Accountant", accSalTotal));
        chart.getData().add(new BarChart.Data("Terminal Manager", terminalManagerSalTotal));
        chart.getData().add(new BarChart.Data("Human Resource", hrmSalTotal));
        chart.getData().add(new BarChart.Data("Manager", managerSalTotal));
        chart.getData().add(new BarChart.Data("Maintenance Staff", mtSalTotal));
        chart.getData().add(new BarChart.Data("Driver", driverSalTotal));

        barChart.getData().add(chart);
    }

    @FXML
    private void createEmployeeScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/HumanResourceViews/CreateEmployee.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static ArrayList<Employee> readEmployeesFromFile(String filename) {
        ArrayList<Employee> employees = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            // Read all existing employees from the file
            employees = (ArrayList<Employee>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions if the file doesn't exist or other issues
            e.printStackTrace();
        }

        return employees;
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        employeeTable.getItems().clear();
        empList = readEmployeesFromFile("MyEmployee.bin");
        employeTableId.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        tableFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        tableLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        tablePhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tableSalary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee, String>("empType"));
        tableAbsent.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("onLeave"));

        observableEmployeeList.addAll(empList);

        employeeTable.setItems(observableEmployeeList);
    }

    public ArrayList<Employee> filterEmployees(String choice, String inputString) {
        ArrayList<Employee> filteredList = new ArrayList<>();

        switch (choice) {
            case "ID":
                for (Employee employee : observableEmployeeList) {
                    if (employee.getId().startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            case "First Name":
                for (Employee employee : observableEmployeeList) {
                    if (employee.getFirstName().startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            case "Last Name":
                for (Employee employee : observableEmployeeList) {
                    if (employee.getLastName().startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            case "Email":
                for (Employee employee : observableEmployeeList) {
                    if (employee.getEmail().startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            case "Salary":
                for (Employee employee : observableEmployeeList) {
                    if (String.valueOf(employee.getSalary()).startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            case "Designation":
                for (Employee employee : observableEmployeeList) {
                    if (employee.getEmpType().startsWith(inputString)) {
                        filteredList.add(employee);
                    }
                }
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("No " + inputString + " found in list!");
                alert.show();
                return new ArrayList<>();
        }

        if (filteredList.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No " + inputString + " found in list!");
            alert.show();
        } else {
            employeeTable.getItems().clear();
        }

        return filteredList;
    }

    @FXML
    private void showSearched(ActionEvent event) {
        String toSearch = searchBox.getText();
        observableEmployeeList.addAll(filterEmployees(sortChoice.getValue(), toSearch));
        employeeTable.setItems(observableEmployeeList);
    }

    @FXML
    private void goToAccount(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            root = loader.load();
            EmployeeDashboardController controller = loader.getController();

            controller.setEmpID(myId);
            controller.LoadData();

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void myEmployees(ActionEvent event) {
        employeeTable.getItems().clear();
        sortChoice.getItems().addAll(getChoices);
        sortChoice.setValue("ID");

        empList = readEmployeesFromFile("MyEmployee.bin");
        employeTableId.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        tableFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        tableLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tableEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        tablePhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        tableSalary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Salary"));
        tableDesignation.setCellValueFactory(new PropertyValueFactory<Employee, String>("empType"));
        tableAbsent.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("onLeave"));

        observableEmployeeList.addAll(empList);

        employeeTable.setItems(observableEmployeeList);
        switchToOvertime.setVisible(false);
        EmployeeListPane.setVisible(true);
    }

    @FXML
    private void overtimePanel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/OvertimeRequest.fxml"));
            root = loader.load();
            OvertimeRequestController controller = loader.getController();

            controller.setEmpID(myId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    private void expandList(ActionEvent event) {
        empList = readEmployeesFromFile("MyEmployee.bin");
        selectedItem = OvertimeView.getSelectionModel().getSelectedItem();
        int firstSpaceIndex = selectedItem.indexOf(" ");
        selectedItem = selectedItem.substring(firstSpaceIndex + 1, selectedItem.indexOf("H"));
        System.out.println(selectedItem);

        String name;
        String hrRate;
        String tHours;
        String Reason;
        String position;

        for (Employee emp : empList) {
            System.out.println("EMP ID: " + emp.getId() + " " + "Selected ID: " + selectedItem);
            if (emp.getId().trim().equals(selectedItem.trim())) {
                name = emp.getFirstName() + " " + emp.getLastName();
                hrRate = Integer.toString(emp.getOverTime().getOvertimeRate());
                tHours = Integer.toString(emp.getOverTime().getOvertimeHours());
                Reason = emp.getOverTime().getOvertimeReason();
                position = emp.getEmpType();
                ovt = new Overtime(emp.getId(), true, Integer.parseInt(tHours), Integer.parseInt(hrRate),
                        false, "");
                String formattedText = String.format("Name: %s\nHourly Rate: %s\nTotal Hours: %s\nReason: %s\nPosition: %s",
                        name, hrRate, tHours, Reason, position);
                expandLabelOvt.setText(formattedText);
                switchToOvertime.setVisible(true);
                ovtRequestPane.setVisible(true);
                return;
            }
        }

    }

    private void closeOvtRequestPane(ActionEvent event) {
        expandLabelOvt.setText("");
        ovtRequestPane.setVisible(false);
        switchToOvertime.setVisible(true);
    }

    private void acceptRequestOvt(ActionEvent event) {
        for (Employee emp : empList) {
            if (emp.getId().trim().equals(selectedItem.trim())) {
                ArrayList<Overtime> ot = Overtime.deleteOvertimeById("Overtime.bin", emp.getId());
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Overtime.bin"))) {
                    // Write the updated list back to the file
                    outputStream.writeObject(ot);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EmployeeOvertimeController.updateEmployeeOvertime(empList, myId, ovt);
                ovtRequestPane.setVisible(false);
                switchToOvertime.setVisible(true);
                expandLabelOvt.setText("");
                items.clear();
                return;
            }
        }

    }

    private void denaiRequestOvt(ActionEvent event) {
        Alert a = new Alert(AlertType.WARNING, "Are you sure you want to cancel your overtime?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.YES) {
            for (Employee emp : empList) {
                if (emp.getId().trim().equals(selectedItem.trim())) {
                    ovt = new Overtime(emp.getId(), false, 0, 0,
                            false, "");
                    ArrayList<Overtime> ot = Overtime.deleteOvertimeById("Overtime.bin", emp.getId());
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Overtime.bin"))) {
                        // Write the updated list back to the file
                        outputStream.writeObject(ot);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    EmployeeOvertimeController.updateEmployeeOvertime(empList, myId, ovt);
                    ovtRequestPane.setVisible(false);
                    switchToOvertime.setVisible(true);
                    expandLabelOvt.setText("");
                    items.clear();
                    return;
                }

            }
        }

    }

    @FXML
    private void toSalary(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/SalaryRequest.fxml"));
            root = loader.load();
            SalaryRequestController controller = loader.getController();

            controller.setEmpID(myId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }

    }

    @FXML
    private void toLeave(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/HolidayRequest.fxml"));
            root = loader.load();
            HolidayRequestController controller = loader.getController();

            controller.setEmpID(myId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void toResign(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/HumanResourceViews/ResignationRequest.fxml"));
            root = loader.load();
            ResignationRequestController controller = loader.getController();

            controller.setEmpID(RelationshipDatabaseClass.getInstance().getCurrentLoggedIn());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void ToPieChart(ActionEvent event) {
        toCharts.setVisible(true);
        EmployeeListPane.setVisible(false);
    }

    @FXML
    private void toTable(ActionEvent event) {
        toCharts.setVisible(false);
        EmployeeListPane.setVisible(true);
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/busterminal/views/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showMassageOnMouseClick(MouseEvent event) {

       try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/busterminal/views/Noticiation.fxml"));
            Scene newScene = new Scene(parent);
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(CheckMaintenanceTaskController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
