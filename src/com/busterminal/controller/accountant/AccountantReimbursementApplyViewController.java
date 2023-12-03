
package com.busterminal.controller.accountant;

import com.busterminal.model.Employee;
import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import com.busterminal.utilityclass.TransitionUtility;
import com.busterminal.utilityclass.Validator;
import com.busterminal.views.Employee.EmployeeDashboardController;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class AccountantReimbursementApplyViewController implements Initializable {

    @FXML
    private MFXTextField txtFieldEmployeeID;
    @FXML
    private TextField txtFieldEmpFirstName;
    @FXML
    private TextField txtFieldEmpLastName;
    @FXML
    private TextField txtFieldEmpAddress;
    @FXML
    private TextField txtFieldEmpSalary;
    @FXML
    private TextField txtFieldEmpDesignation;
    @FXML
    private MFXComboBox<String> combBoxSelectExpense;
    @FXML
    private MFXTextField txtFieldExpenseAmount;
    @FXML
    private MFXComboBox<String> combBoxPrefPaymentMethod;
    
    private ReimbursementInfo currentReimbursementInfo;
    
    private boolean loadButtonClickStaus = false;
    @FXML
    private AnchorPane rootPane;
    
    private LocalDate currentDate = LocalDate.now();
    
    private ArrayList<Employee> allAvailableEmployee;
    
    private Employee currentEmployee;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TransitionUtility.materialScale(rootPane);
        combBoxSelectExpense.getItems().setAll("Internet Bill",
                                                "Telephone Bill",
                                                "Bus Equipment", 
                                                "Terminal Equipment");
        
        combBoxPrefPaymentMethod.getItems().setAll("Bkash",
                                                   "Bank ACC", 
                                                   "Nagad",
                                                   "Cash", 
                                                   "Company Credit");
        allAvailableEmployee = Employee.readEmployeesFromFile("MyEmployee.bin");
    }    

    @FXML
    private void onClickLoadEmployeeDetails(ActionEvent event) {
        String employeeID = txtFieldEmployeeID.getText();
        if(employeeID == null || employeeID.isEmpty()){
            showErrorDialog("No Employee Info!","Please Input a valid Employee ID before Trying to Load ");
            return;
        }
        for (Employee empObj: allAvailableEmployee){
           if(empObj.getId().equals(txtFieldEmployeeID.getText())){
               currentEmployee = empObj;
               break;
           } 
        }
        txtFieldEmpFirstName.setText(currentEmployee.getFirstName());
        txtFieldEmpLastName.setText(currentEmployee.getLastName());
        txtFieldEmpDesignation.setText(currentEmployee.getEmpType());
        txtFieldEmpSalary.setText(String.valueOf(currentEmployee.getSalary()));
        txtFieldEmpAddress.setText(currentEmployee.getAddress());
        loadButtonClickStaus = true;
    }
    
    public void setEmployeeIDFromSceneSwitch(String ID){
        this.txtFieldEmployeeID.setText(ID);
    }

    @FXML
    private void onClickSubmitEmployeeReimb(ActionEvent event) {
        if (!loadButtonClickStaus){
            showErrorDialog("No Employee Info!","Please Input Employee ID and Load Info before Trying to Submit");
            return;
        }
        
        String expenseItem = combBoxSelectExpense.getSelectedItem();
        if(expenseItem == null){
            showErrorDialog("No Selection!","Please select an expense cause before proceeding"
                                + "to Submit the Reimbursement Request!");
            return;
        }
        
        String prefPaymentMethod = combBoxPrefPaymentMethod.getSelectedItem();
        if(expenseItem == prefPaymentMethod){
            showErrorDialog("No Selection!","Please select your preferred payment receving method before proceeding"
                                + "to Submit the Reimbursement Request!");
            return;
        }
        
        String reimAmount = txtFieldExpenseAmount.getText();
        if( !Validator.isInteger(reimAmount) || Integer.parseInt(reimAmount) == 0  ){
            showErrorDialog("Invalid Amount!","Please input non-zero postive integer value as Reimbursement Amount");
        }
        
        String employeeID = txtFieldEmployeeID.getText();
        
        currentReimbursementInfo = new ReimbursementInfo(employeeID,Integer.parseInt(reimAmount),txtFieldEmpFirstName.getText(),txtFieldEmpLastName.getText(),expenseItem,prefPaymentMethod,currentDate, 
                                                        txtFieldEmpDesignation.getText());
        
        RelationshipDatabaseClass.getInstance().addItemToReimbursementList(currentReimbursementInfo);
        showSuccessDialog("Success!","Your Reimbursement Request has been successfully submitted!");
    }
    
    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert",rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success",rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void onClickViewCurrentReqs(ActionEvent event) {
        try {
            // Load the new content FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantEmployeeViewCurrentRequests.fxml"));
            AnchorPane newContent = loader.load();

            // Get the controller
            AccountantEmployeeViewCurrentRequestsController controller = loader.getController();

            
            controller.setEmployeeIDFromSceneSwitch(txtFieldEmployeeID.getText());
            rootPane.getChildren().setAll(newContent);
            
        } catch (Exception e) {
            e.printStackTrace();
            MFXDialog.showErrorDialog("Error Occured", e.getMessage(), rootPane);
        }
    }

    @FXML
    private void goBackToEmployeeMyAccount(MouseEvent event) {
        try {

            Parent root = null;
            FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            
            root = (Parent) someLoader.load();
            EmployeeDashboardController controller = someLoader.getController();
            
            controller.setEmpID(RelationshipDatabaseClass.getInstance().getCurrentLoggedIn());
            controller.LoadData();
            
            Scene someScene = new Scene(root);

            Stage someStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            someStage.setScene(someScene);
            someStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("FXML not Found", "Unable to swtich scene, make sure FXML is present in the specified path");
        }
    }
    
}
