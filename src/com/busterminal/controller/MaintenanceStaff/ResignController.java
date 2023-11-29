package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.AppendableObjectOutputStream;
import com.busterminal.model.Resignation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    @FXML
    private Label filenameLabel;

    private String filePath = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileTypeList = new ArrayList<>();
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
    private void chooseFileOnClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", fileTypeList));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            filePath = f.getAbsolutePath();
            System.out.println("Selected File: " + filePath);
        }

        if (f != null) {
            try {
                Scanner sc = new Scanner(f);
                String str = "";
                while (sc.hasNextLine()) {
                    str += sc.nextLine() + "\n";
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        sendResignationBtn.setDisable(false);
        filenameLabel.setText(filePath);

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
        Resignation r2 = new Resignation(nameTextField.getText(), Integer.parseInt(empIDTextField.getText()),
                Integer.parseInt(resignationIDTextField.getText()),
                commentTextField.getText(), (String) letterContent);

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("Resignation.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(r2);

        } catch (IOException ex) {
           ex.printStackTrace();
           
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        clear();

    }

    private void clear() {
        nameTextField.clear();
        empIDTextField.clear();
        commentTextField.clear();
    }

}
