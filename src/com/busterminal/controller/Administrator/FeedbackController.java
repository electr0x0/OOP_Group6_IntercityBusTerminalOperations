package com.busterminal.controller.Administrator;

import com.busterminal.model.Database;
import com.busterminal.model.Feedback;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class FeedbackController {

    @FXML
    private TableView<Feedback> feedbackTableView;

    @FXML
    private TableColumn<Feedback, Integer> feedbackIdColumn;

    @FXML
    private TableColumn<Feedback, String> passengerNameColumn;

    @FXML
    private TableColumn<Feedback, String> commentColumn;

    @FXML
    private TextArea selectedFeedbackTextArea;

    public void initialize() {
        // Initialize TableView columns
        feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackID"));
        passengerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));

        // read from file
        feedbackTableView.getItems().addAll(Database.getInstanceBinFile("Feedback.bin"));

        // Add a listener to display selected feedback in the TextArea
        // someting....
        feedbackTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Feedback>() {
            /*/selectedItemProperty() is a method that returns a property representing the currently selected
            item in the TableView*/

 /*
            ChangeListener<Feedback>() is a inner anonymous class  that implement the 
            ChangeListener Interface
             */
 /*
            .addListener(new ChangeListener<Feedback>()  adding a listener to the selected 
            item property. A listener is a piece of code that will be executed
            when a certain event occurs*/
            //ChangeListener interface has a single method called changed
            @Override
            public void changed(ObservableValue<? extends Feedback> observable, Feedback oldValue, Feedback newValue) {
                //<? extends T> means "any type that is a subtype of T" or "any type that extends T
                //observable: The object that triggered the change.
                //Feedback oldValue: This parameter represents the value of the property before the change.
                //Feedback newValue: This parameter represents the new value of the property after the change.
                displaySelectedFeedback(newValue);

            }
        });

    }

    private void displaySelectedFeedback(Feedback selectedFeedback) {
        selectedFeedbackTextArea.setText("Feedback ID: " + selectedFeedback.getFeedbackID()
                + "\nPassenger Name: " + selectedFeedback.getName()
                + "\nSubject: " + selectedFeedback.getSubject() + "\n" + "coment:" + "\n"
                + selectedFeedback.getBody());
    }

}
