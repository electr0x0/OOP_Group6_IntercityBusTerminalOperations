/**
package com.busterminal.controller;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
///import mainPkg.Interface.Account;
///import mainPkg.Interface.Administrator;
//import mainPkg.Interface.PopUp;
///import mainPkg.model.Employee;
/**
 * FXML Controller class
 *
 * @author DELL
 
public class AddNewEmployeController implements Initializable {

  @FXML
    private TableView<Employee> userDataTable;
    @FXML
    private TableColumn<Employee, String> firstNameCol;
    @FXML
    private TableColumn<Employee, String> genderCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> phoneCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    @FXML
    private TableColumn<Employee, String> designationCol;
    @FXML
    private TableColumn<Employee, String> passwordCol;

    private ObservableList<Employee> employeeObjectlist;
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
    private TableColumn<Employee,Integer> IdCol;

    /**
     * Initializes the controller class.
   
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
    
    }    

    @FXML
    private void AddButtonOnMouseClick(ActionEvent event) {
        ObservableList<Employee> dummyObservableList=FXCollections.observableArrayList();
        String firstname = firstNameTF.getText();
        String lastname = lastNameTF.getText();
        String address = addressTF.getText();
        String contactNumber = phoneNumberTF.getText();
        String email = emailTF.getText().trim();
        LocalDate dob = birthdayDP.getValue();
        String gender = (maleRB.isSelected() ? "Male" : "Famale");
        String designation = designationCB.getValue();

        if (Account.checkEmployeeEmailExistence(email) == true) {
            PopUp.showMessage("Information", "Account Already Exists !");
        } else {
            int id = Account.generateEmployeeID();
            String password = Account.generateEmployeePassword();
            LocalDate doj = LocalDate.now();
            Employee e = new Employee(designation, doj, id, firstname, lastname, address, email, dob, password, contactNumber, gender);
            Administrator.employeeCreateNewAccount(e);
            dummyObservableList.add(e);
            userDataTable.getItems().add(e);
            

            PopUp.showMessage("Information", "Account has been Successfully Created..!"
                    + "Employee ID: " + id + "\n"
                    + "Employee Password: " + password);
        }
        
    }
    
}
*/