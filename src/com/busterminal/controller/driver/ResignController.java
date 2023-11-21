/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller.driver;

//import com.busterminal.views.driver.*;
import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.controller.passenger.SupportController;
import com.busterminal.model.Feedback;
import com.busterminal.model.Resignation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author UseR
 */
public class ResignController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField empIDTextField;
    @FXML
    private TextField commentTextField;
    @FXML
    private TextField resignationIDTextField;
    
    private ArrayList<String> fileTypeList;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private Button sendResignationBtn;
    
    private String filePath="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileTypeList = new ArrayList<String>();
        fileTypeList.add("*.txt");
        fileTypeList.add("*.doc");
        fileTypeList.add("*.docx");
        fileTypeList.add("*.TXT");
        fileTypeList.add("*.DOC");
        fileTypeList.add("*.DOCX");
        
        
        sendResignationBtn.setDisable(true);
        
        Random rand = new Random();
        
        resignationIDTextField.setText(Integer.toString(rand.nextInt(100)));
        
    }    

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

    @FXML
    private void chooseFileOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", fileTypeList));
        
        //File f2 = fc.showSaveDialog(null);
        File f = fc.showOpenDialog(null);
        if (f != null) {
        filePath = f.getAbsolutePath();
        System.out.println("Selected File: " + filePath);
        }
        
        
        if(f != null){
            try {
                Scanner sc = new Scanner(f);
                String str="";
                while(sc.hasNextLine()){
                    str+=sc.nextLine()+"\n";
                }
                //System.out.println(str);
                //fileContentLabel.setText(str);
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(FileChooserViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        chooseFileBtn.setDisable(true);
        
       
            sendResignationBtn.setDisable(false);
        
    }

    @FXML
    private void sendResignLetterOnClick(ActionEvent event) throws FileNotFoundException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("  ");
        alert.setTitle("Confirmation");
        alert.setHeaderText("Resignation Application Sent");
        alert.showAndWait();
        
        Resignation r1 = new Resignation();
        //System.out.println(filePath);
        //r1.readLetter(filePath);
        Serializable letterContent = r1.readLetter(filePath);
        Resignation r2 = new Resignation(nameTextField.getText(),Integer.parseInt(empIDTextField.getText()),
                                                                
                                                                              Integer.parseInt(resignationIDTextField.getText()),
                                                                              commentTextField.getText(), (String) letterContent);
        
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("Resignation.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            
            
            oos.writeObject(r2);

        } catch (IOException ex) {
            Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        
    }

    @FXML
    private void readOnClick(ActionEvent event) {
        
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("Resignation.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Resignation r;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    r= (Resignation)ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(r.toString());
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch(Exception e){
                // handling code
            }//nested catch     
            //outputTextArea.appendText("All objects are loaded successfully...\n");            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }  
    
    
    }
}
