package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.Administrator;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.MaintenanceStaff;
import com.busterminal.model.PopUp;
import com.busterminal.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddMaintenanceMemberController implements Initializable {

    @FXML
    private TableView<MaintenanceStaff> userDataTable;
    @FXML
    private TableColumn<MaintenanceStaff, String> firstNameCol;
    @FXML
    private TableColumn<MaintenanceStaff, String> genderCol;
    @FXML
    private TableColumn<MaintenanceStaff, String> emailCol;
    @FXML
    private TableColumn<MaintenanceStaff, String> phoneCol;
    @FXML
    private TableColumn<MaintenanceStaff, String> addressCol;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private DatePicker birthdayDP;
    @FXML
    private TextField addressTF;
    @FXML
    private RadioButton maleRB;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton FemaleRB;
    @FXML
    private ComboBox<String> designationCB;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private AnchorPane AddEmployeAnchorPane;
    @FXML
    private TableColumn<DummyEmployee, Integer> IdCol;
    @FXML
    private TextField salaryTF;
    @FXML
    private Label fristNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label designationLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthdateLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label salaryLabel;

    private ObservableList<DummyEmployee> employeeObjectlist;
    @FXML
    private TableColumn<MaintenanceStaff, String> expertCol;
    @FXML
    private ComboBox<String> searchCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchCB.getItems().addAll("Tire Technicians", "Oil Change Technicians", "Brake Technicians","Engine Technicians");
        designationCB.getItems().addAll("Tire Technicians", "Oil Change Technicians", "Brake Technicians","Engine Technicians");
        employeeObjectlist = FXCollections.observableArrayList();
        IdCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        expertCol.setCellValueFactory(new PropertyValueFactory<>("staffType"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        userDataTable.getItems().addAll(MaintenanceStaff.getEmployeeList());
        System.out.println(MaintenanceStaff.getEmployeeList());
        userDataTable.setVisible(true);

    }

    @FXML
    private void AddButtonOnMouseClick(ActionEvent event) {
        ObservableList<DummyEmployee> dummyObservableList = FXCollections.observableArrayList();
        String firstname = firstNameTF.getText();
        String lastname = lastNameTF.getText();
        String address = addressTF.getText();
        String contactNumber = phoneNumberTF.getText();
        String email = emailTF.getText().trim();
        LocalDate dob = birthdayDP.getValue();
        String gender = (maleRB.isSelected() ? "Male" : "Famale");
        Integer salary = Integer.valueOf(salaryTF.getText());
        String type = designationCB.getValue();

        // Check validation and email and pass verification
        if (firstNameTF.getText().trim().equals("")) {
            fristNameLabel.setText("Enter Employee First Name !");
            return;
        }
        if (lastNameTF.getText().trim().equals("")) {
            lastNameLabel.setText("Enter Employee last Name !");
            return;
        }
        if (emailTF.getText().trim().equals("")) {
            emailLabel.setText("Enter Empoloyee Eamil !");
            return;
        } else {
            if (User.verifyEmailSuffix(email) == false) {
                emailLabel.setText("@gmail.com");
                return;
            }
        }
        if (phoneNumberTF.getText().trim().equals("")) {
            phonenumberLabel.setText("Enter Employee Phone Number !");
            return;
        } else {
            if (phoneNumberTF.getText().length() != 11) {
                phoneNumberTF.setText("Enter valid Phone Number !");
            }
        }

        if (addressTF.getText().trim().equals("")) {
            addressLabel.setText("Enter Employee Address !");
            return;
        }

        if (maleRB.isSelected() == false && FemaleRB.isSelected() == false) {
            genderLabel.setText("Choose Employee Gender !");
            return;
        }

        if (salaryTF.getText().equals("")) {
            salaryLabel.setText("Enter Employee Salary !");
            return;
        }

        if (designationCB.getValue() == "") {
            designationLabel.setText("Select Employee Type");
            return;
        }

        if (birthdayDP.getValue() == null) {
            birthdateLabel.setText("Enter Employee Birth Date !");
            return;
        } else {
            if (User.AgeVerification(birthdayDP.getValue()) == false) {
                birthdateLabel.setText("Employee Age Must be gatter then 19 !");
                return;
            }
        }

        if (User.checkEmployeeEmailExistence(email) == true) {
            PopUp.showMessage("Information", "Account Already Exists !");
        } else {
            int id = User.generateEmployeeID();
            String password = User.generateEmployeePassword();
            LocalDate doj = LocalDate.now();

            String designation = "Maintenance Staff";
            DummyEmployee e = new DummyEmployee(designation, doj, id, firstname, lastname, address, email, dob, password, contactNumber, gender, salary);
            Administrator.employeeCreateNewAccount(e);

            MaintenanceStaff m = new MaintenanceStaff(designation, doj, id, firstname, lastname, address, email, dob, password, contactNumber, gender, salary, type);
            System.out.println(m);
            employeeObjectlist.add(m);
            System.out.println(employeeObjectlist);
            MaintenanceStaff.employeeCreateNewAccount(m);
            dummyObservableList.add(e);
            userDataTable.getItems().add(m);

            PopUp.showMessage("Information", "Account has been Successfully Created..!"
                    + "Employee ID: " + id + "\n"
                    + "Employee Password: " + password);
        }
        clear();

    }

    @FXML
    private void removeButtonOnMouseClick(ActionEvent event) {
        MaintenanceStaff selectedItem = userDataTable.getSelectionModel().getSelectedItem();
        ObservableList<MaintenanceStaff> selectedRows, allPeople;
        allPeople = userDataTable.getItems();
        selectedRows = userDataTable.getSelectionModel().getSelectedItems();
        for (MaintenanceStaff p : selectedRows) {
            allPeople.remove(p);
        }
        MaintenanceStaff.deleteEmployee(selectedItem);
        Administrator.deleteEmployee(selectedItem);

    }
    private void clear(){
        firstNameTF.clear();
        lastNameTF.clear();
        phoneNumberTF.clear();
        emailTF.clear();
        designationCB.setValue(null);
        addressTF.clear();
        birthdayDP.setValue(null);
        salaryTF.clear();
        fristNameLabel.setText("");
        lastNameLabel.setText("");
        phonenumberLabel.setText("");
        emailLabel.setText("");
        designationLabel.setText("");
        addressLabel.setText("");
        birthdateLabel.setText("");
        salaryLabel.setText("");
 
    }

}
