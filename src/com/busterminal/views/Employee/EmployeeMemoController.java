/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.views.Employee;

import com.busterminal.controller.accountant.AccountantReimbursementApplyViewController;
import com.busterminal.model.Employee;
import com.busterminal.model.employeeModels.Memo;
import com.busterminal.utilityclass.MFXDialog;
import static com.busterminal.views.Employee.EmployeeSalaryDashboardController.getEmployeeById;
import static com.busterminal.views.HumanResourceViews.MyEmployeeController.readEmployeesFromFile;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import static io.github.palexdev.materialfx.builders.layout.AnchorPaneBuilder.anchorPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EmployeeMemoController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private ListView<String> memoView;
    @FXML
    private TextField subject;
    @FXML
    private TextField body;
    @FXML
    private TextField tagsToAdd;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    String empID;
    Employee user;
    String formattedString;
    
    ArrayList<String> tags = new ArrayList<>();
    ArrayList<Memo> allMemos = new ArrayList<>();
    
    ObservableList<String> items = FXCollections.observableArrayList();
    
    @FXML
    private TextArea currentTags;
    @FXML
    private AnchorPane openPane;
    @FXML
    private Label infoShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allMemos = readMemoFromFile("Memo.bin");
        Collections.reverse(allMemos);
        String taggy="";
        for(Memo m: allMemos){
            for(String t: m.getTags()){
                taggy+= t+" ";
            }
            String momo = "ID: "+m.getId()+" Subject: "+ m.getSubject()+" by: "+m.getSenderDesignation()+" TAGS: "+taggy+" "+m.getDate()+" "+m.getTime();
            items.addAll(momo);
        }
        memoView.setItems(items);
    }    
    
     public void LoadData(){
         user = getEmployeeById("MyEmployee.bin",empID);
    }

    @FXML
    private void toHome(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeDashboard.fxml"));
            root = loader.load();
            EmployeeDashboardController controller = loader.getController();

            controller.setEmpID(empID);
            controller.LoadData();
             
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void toSalary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeSalaryDashboard.fxml"));
            root = loader.load();
            EmployeeSalaryDashboardController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    @FXML
    private void switchOvertime(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeOvertime.fxml"));
            root = loader.load();
            EmployeeOvertimeController controller = loader.getController();

            controller.setEmpID(empID);
            controller.LoadData();
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeResignation.fxml"));
            root = loader.load();
            EmployeeResignationController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();
             
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

   @FXML
    private void goHome(ActionEvent event) throws IOException {
            if(user.getEmpType().equals("Administrator")){
               SceneSwitch(event,"/com/busterminal/views/Addministrator/AdminDashbord.fxml");
            } else if(user.getEmpType().equals("Maintenance Staff")){
                SceneSwitch(event,"/com/busterminal/views/MaintenanceStaff/MaintenanceStaffDashbord.fxml");
            } else if(user.getEmpType().equals("Driver")){
                SceneSwitch(event,"/com/busterminal/views/accountUser/AccountDashbord.fxml");
            } else if(user.getEmpType().equals("Terminal Manager")){
                SceneSwitch(event,"/com/busterminal/views/terminalManagerUser/TerminalManagerDashboard.fxml");
            } else if(user.getEmpType().equals("Human Resource")){
                
                SceneSwitch(event,"/com/busterminal/views/HumanResourceViews/MyEmployee.fxml");
                
            } else if(user.getEmpType().equals("Accountant")){
                SceneSwitch(event,"/com/busterminal/views/accountantUser/AccountantDashboard.fxml");
            }                      
        
    }
    
      public void SceneSwitch(ActionEvent e, String fxmlLocal) {
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlLocal));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception (e.g., logging or displaying an error message)
        }   
    }

    @FXML
    private void openSelectedMemo(ActionEvent event) {
      allMemos = readMemoFromFile("Memo.bin");
      String selectedItem = memoView.getSelectionModel().getSelectedItem();
      int firstSpaceIndex = selectedItem.indexOf(" ");
      String SelectedId = selectedItem.substring(firstSpaceIndex+1,selectedItem.indexOf("S"));
      for(Memo m: allMemos){
           if(m.getId().trim().equals(SelectedId.trim())){
               String id = m.getId();
               String mail = m.getEmail();
               String name = m.getSenderName();
               String sub = m.getSubject();
               String writeup = m.getBody();
               String des = m.getSenderDesignation();
               String date = m.getDate().toString();
               formattedString = String.format("%s\n%s\n \n From: %s\n %s\n Issued Date: %s\n \n %s\n \n \n %s\n",
                       id,mail,name,des,date,sub,writeup);
               
                infoShow.setText(formattedString);
                openPane.setVisible(true);
                return;
           }
      }
      
    }


    @FXML
    private void deleteLastTag(ActionEvent event) {
        if(tags.size() == 0){
            return;
        }
        tags.remove(tags.size()-1);
        currentTags.clear();
        for(String tag: tags){
            currentTags.appendText(tag);
        }
    }

    @FXML
    private void addTag(ActionEvent event) {
        if (tagsToAdd.getText().isEmpty()) {
            Alert a = new Alert(AlertType.WARNING, "You haven't given a tag");
            a.show();
            return;
        }

        if (tags != null && !tags.isEmpty() && tags.contains(tagsToAdd.getText())) {
            Alert a = new Alert(AlertType.INFORMATION, "Tag already added!");
            a.show();
        } else {
            tags.add(tagsToAdd.getText());
            currentTags.appendText(tagsToAdd.getText()+" ");
            tagsToAdd.clear();
        }

    }

    @FXML
   private void sendMemo(ActionEvent event) {
      if(subject.getText().isEmpty() || body.getText().isEmpty()){
            Alert a = new Alert(AlertType.WARNING,"All fields not given");
            a.show();
            return;
        }
        if(currentTags.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.WARNING,"You have no tags,are you sure you want to continue?",ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = a.showAndWait();
            if(result.get()== ButtonType.YES){
                Memo m = new Memo(user.getFirstName()+" "+user.getLastName(),
                user.getEmail(),user.getEmpType(),user.getId(),subject.getText(),body.getText(),tags,
                LocalDate.now(),LocalTime.now());
                writeMemoToFile("Memo.bin",m);
                subject.clear();
                body.clear();
                currentTags.clear();
                tags = new ArrayList<String>();
                Alert b = new Alert(AlertType.CONFIRMATION,"You successfully have sent your memo!");
                b.show();
                return;
            }     
                   
        } else{
             Memo m = new Memo(user.getFirstName()+" "+user.getLastName(),
                user.getEmail(),user.getEmpType(),user.getId(),subject.getText(),body.getText(),tags,
                LocalDate.now(),LocalTime.now());
                writeMemoToFile("Memo.bin",m);
                subject.clear();
                body.clear();
                currentTags.clear();
                tags = new ArrayList<String>();
                Alert b = new Alert(AlertType.CONFIRMATION,"You successfully have sent your memo!");
                b.show();
        }
    }



    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    public static void writeMemoToFile(String filename, Memo memo) {
        String filePath = filename;

        // Read existing memos from the file
        ArrayList<Memo> memos = readMemoFromFile(filePath);

        // Add the new memo
        memos.add(memo);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // Write the entire list back to the file
            outputStream.writeObject(memos);
            System.out.println("Memo written to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Memo> readMemoFromFile(String filename) {
        ArrayList<Memo> memos = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> list = (ArrayList<?>) obj;
                for (Object o : list) {
                    if (o instanceof Memo) {
                        memos.add((Memo) o);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return memos;
    }

    @FXML
    private void closeInfoBox(ActionEvent event) {
        openPane.setVisible(false);
    }

    @FXML
    private void toHoliday(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/Employee/EmployeeHoliday.fxml"));
            root = loader.load();
            EmployeeHolidayController controller = loader.getController();

            controller.setEmpID(empID);
             controller.LoadData();
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }


    @FXML
    private void refresh(ActionEvent event) {
        items.clear();
        allMemos = readMemoFromFile("Memo.bin");
        Collections.reverse(allMemos);
        String taggy="";
        for(Memo m: allMemos){
            for(String t: m.getTags()){
                taggy+= t+" ";
            }
            String momo = "ID: "+m.getId()+" Subject: "+ m.getSubject()+" by: "+m.getSenderDesignation()+" TAGS: "+taggy+" "+m.getDate()+" "+m.getTime();
            items.addAll(momo);
        }
        memoView.setItems(items);
    }

    @FXML
    private void convertToPdf(ActionEvent event) throws FileNotFoundException {
        try {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
    File f = fc.showSaveDialog(null);
    if (f != null) {
        PdfWriter pw = new PdfWriter(new FileOutputStream(f));
        PdfDocument pdf = new PdfDocument(pw);
        pdf.addNewPage();

        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);
        document.add(new Paragraph(formattedString));
        document.close();
    }
    } catch (Exception e) {
        System.out.println(e);

    }

    }

    @FXML
    private void toReimburs(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/busterminal/views/accountantUser/AccountantReimbursementApplyView.fxml"));
            root = loader.load();
            AccountantReimbursementApplyViewController controller = loader.getController();

            controller.setEmployeeIDFromSceneSwitch(empID);
             
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

}