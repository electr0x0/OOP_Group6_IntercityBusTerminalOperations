
package com.busterminal.controller.driver;


import com.busterminal.model.Driver;
import com.busterminal.model.Maintenance;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UseR
 */
public class RequestMaintenanceController implements Initializable {

    @FXML
    private TextField driverIDTextField;
    @FXML
    private TextField busIdTextField;
    @FXML
    private ComboBox<String> maintenanceTypeCombo;
    @FXML
    private TextField commentTextField;
    @FXML
    private TextArea detailsTextArea;
    
    ArrayList<Maintenance> maintenanceList= new ArrayList();
    @FXML
    private Button confirmBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maintenanceTypeCombo.getItems().setAll("Engine oil","Tyres","Windscreen and windows","Electrical equipment","Brake system","Vehicle battery","Fuel system");
        //confirmBtn.setDisable(true);
    }    

    @FXML
    private void confirmRequestOnClick(ActionEvent event) {
        
        
        if ("".equals(busIdTextField.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setTitle("Error");
        alert.setHeaderText("Bus ID can not be empty");
        alert.showAndWait();}
        
        else if ("".equals(detailsTextArea.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setTitle("Error");
        alert.setHeaderText("Details can not be empty");
        alert.showAndWait();}
        
        
        
        else if ("".equals(driverIDTextField.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setTitle("Error");
        alert.setHeaderText("Driver ID can not be empty");
        alert.showAndWait();}
        
        else if ("".equals(maintenanceTypeCombo.getValue())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setTitle("Error");
        alert.setHeaderText(" Please select a maintenance type");
        alert.showAndWait();}
        
        else if ("".equals(commentTextField.getText())){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("");
        alert.setTitle("Error");
        alert.setHeaderText("Comment can not be empty");
        alert.showAndWait();}
        
        
        
        if (busIdTextField.getText().isEmpty() == false && driverIDTextField.getText().isEmpty() == false && detailsTextArea.getText().isEmpty() == false
                && detailsTextArea.getText().isEmpty() == false && commentTextField.getText().isEmpty() == false) {

            
                Driver driver =new Driver();
                driver.requestMaintenance(driverIDTextField.getText(),busIdTextField.getText(), maintenanceTypeCombo.getValue(), commentTextField.getText(),
                        LocalDate.now(), detailsTextArea.getText());
          

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("");
            alert.setTitle("Confirmation");
            alert.setHeaderText("Request Confirmed");
            alert.showAndWait();
        }
    }


        /*
        if ( busIdTextField.getText().isEmpty() == false  && driverIDTextField.getText().isEmpty() == false &&  detailsTextArea.getText().isEmpty() == false && 
                detailsTextArea.getText().isEmpty() == false &&  commentTextField.getText().isEmpty() == false ){
        
            
            
        Maintenance m1 = new Maintenance(busIdTextField.getText(),maintenanceTypeCombo.getValue(),commentTextField.getText(),LocalDate.now(),detailsTextArea.getText());
        
        maintenanceList.add(m1);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Request Confirmed");
        alert.showAndWait();
                   
        
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("MaintenanceList.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            
            for(Maintenance x: maintenanceList){
                oos.writeObject(x);
            }
            //oos.writeObject(m1);

        } catch (IOException ex) {
            Logger.getLogger(RequestMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(RequestMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }                
        
    }
      */  
        
            
    
    
    

    @FXML
    private void switchToDashboard(ActionEvent event) throws IOException {
        
         Parent root = null;
        FXMLLoader someLoader = new FXMLLoader(getClass().getResource("/com/busterminal/views/driver/Dashboard_Driver.fxml"));
        root = (Parent) someLoader.load();
        
        Scene someScene = new Scene (root);
        
        
        
        Stage someStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        someStage.setScene(someScene);
        someStage.show();
        
    }
    
}
