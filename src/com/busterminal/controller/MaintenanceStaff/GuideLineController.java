package com.busterminal.controller.MaintenanceStaff;

import com.busterminal.model.Database;
import com.busterminal.model.GuideLine;
import com.busterminal.model.PopUp;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class GuideLineController implements Initializable {

    @FXML
    private TextArea textArea;
    private ObservableList<GuideLine> data;
    private File currentFile;
    @FXML
    private AnchorPane anchorPaneShow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();

    }

    @FXML
    private void newMenuItemsClick(ActionEvent event) {
        textArea.clear();
        currentFile = null;

    }

    @FXML
    private void openMenuItemsClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // set Initial directory to user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // show open file dialog
        File selectedFile = fileChooser.showOpenDialog(textArea.getScene().getWindow());
        // selectedFile represent the file

        // now read the selectedfile
        if (selectedFile != null) {
            try (FileReader fileReader = new FileReader(selectedFile)) {

                char[] character = new char[(int) selectedFile.length()];

                // Read the entire character into the char array
                fileReader.read(character);

                // Set the text area with the character of the file
                textArea.setText(new String(character));
                currentFile = selectedFile;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void saveMenuItemsClick(ActionEvent event) {
        if (currentFile == null) {
            saveAs();
        } else {
            saveToFile(currentFile);
        }

    }

    private void saveAs() {
        // choose File form desktop user
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // setIni method used to provide a default file name (myFile.txt) but user can change the name
        File selectedFile = fileChooser.showSaveDialog(textArea.getScene().getWindow());
        // showSaveDialog is a part of ui

        if (selectedFile != null) {
            saveToFile(selectedFile);
            currentFile = selectedFile;
        }

    }

    private void saveToFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void closeMenuItemsClick(ActionEvent event) {
        Stage stage = (Stage) textArea.getScene().getWindow();

        // Close the stage
        stage.close();
    }

    @FXML
    private void previousGuidelineMenuItemsClick(ActionEvent event) {
        File f = new File("GuideLine.bin");
        int length = (int) f.length();
        ObservableList<GuideLine> guideLine = GuideLine.getItems();
        // Get the last element 
        int lastIndex = guideLine.size() - 1;
        System.out.println(lastIndex);
        if (lastIndex >= 0) {
            GuideLine lastIntance = guideLine.get(lastIndex);
            textArea.setText(lastIntance.getText());
        } else {
            PopUp.showMessage("Information", "Write a GuideLine..!");

        }
    }

    @FXML
    private void sendOnMouseClick(ActionEvent event) {
        GuideLine g = new GuideLine(LocalDate.now(), textArea.getText());
        GuideLine.addItems(g);
        textArea.clear();
        PopUp.showMessage("Information", "Massage send Successfully");

    }

}
