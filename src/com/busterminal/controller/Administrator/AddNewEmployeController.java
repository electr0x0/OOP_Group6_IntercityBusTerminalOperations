package com.busterminal.controller.Administrator;
import com.busterminal.model.Administrator;
import com.busterminal.model.DummyEmployee;
import com.busterminal.model.PopUp;
import com.busterminal.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class AddNewEmployeController implements Initializable {

    @FXML
    private TableView<DummyEmployee> userDataTable;
    @FXML
    private TableColumn<DummyEmployee, String> firstNameCol;
    @FXML
    private TableColumn<DummyEmployee, String> genderCol;
    @FXML
    private TableColumn<DummyEmployee, String> emailCol;
    @FXML
    private TableColumn<DummyEmployee, String> phoneCol;
    @FXML
    private TableColumn<DummyEmployee, String> addressCol;
    @FXML
    private TableColumn<DummyEmployee, String> designationCol;
    @FXML
    private TableColumn<DummyEmployee, String> passwordCol;

    private ObservableList<DummyEmployee> employeeObjectlist;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        designationCB.getItems().addAll("Administrator", "Maintenance Staff", "Ticket Vendor", "Driver",
                "Terminal Manager", "Human Resource");

        employeeObjectlist = FXCollections.observableArrayList();
        IdCol.setCellValueFactory(new PropertyValueFactory<>("iD"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        userDataTable.getItems().addAll(Administrator.getEmployeeList());
        userDataTable.setVisible(true);
        
        
        // set multiple selection
        //userDataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
        String designation = designationCB.getValue();

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
            designationLabel.setText("Select Employee desingnation");
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
            DummyEmployee e = new DummyEmployee(designation, doj, id, firstname, lastname, address, email, dob, password, contactNumber, gender, salary);
            Administrator.employeeCreateNewAccount(e);
            dummyObservableList.add(e);
            userDataTable.getItems().add(e);

            PopUp.showMessage("Information", "Account has been Successfully Created..!"
                    + "Employee ID: " + id + "\n"
                    + "Employee Password: " + password);
        }

    }

    @FXML
    private void removeButtonOnMouseClick(ActionEvent event) {
        DummyEmployee selectedItem = userDataTable.getSelectionModel().getSelectedItem();
        ObservableList<DummyEmployee> selectedRows, allPeople;
        allPeople = userDataTable.getItems();
        selectedRows = userDataTable.getSelectionModel().getSelectedItems();
        for (DummyEmployee p : selectedRows) {
            allPeople.remove(p);
        }
       Administrator.deleteEmployee(selectedItem);
        
        
        
    }
}
