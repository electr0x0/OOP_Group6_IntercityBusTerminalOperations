/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.busterminal.controller;

import com.busterminal.model.terminalManager.ChatModel;
import com.busterminal.storage.db.RelationshipDatabaseClass;
import com.busterminal.utilityclass.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author electr0
 */
public class CommunicationHubController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private MFXComboBox<String> comboBoxReceiverList;

    private String currentEmp; // will be instantiated while switching scene
    private String currentEmpType; //will be instantiated while switching scene
    @FXML
    private TextArea textAreaChatBody;
    @FXML
    private MFXTextField txtFieldmsgContent;

    private String currentChatHistory = "";

    private ArrayList<ChatModel> allAvailableChats = new ArrayList<>();

    private ChatModel currentChat;
    
    LocalDateTime now;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss a");

    String formattedDateTime;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxReceiverList.getItems().addAll("Accountant", "Manager", "HR",
                "Driver A", "Driver B", "Driver C");

        if (RelationshipDatabaseClass.getInstance().getAllAvailableChats() != null) {
            allAvailableChats = RelationshipDatabaseClass.getInstance().getAllAvailableChats();
        }
    }

    @FXML
    private void onClickSendMessageButton(ActionEvent event) {
        String receiver = comboBoxReceiverList.getSelectedItem();
        if (receiver == null || receiver.isEmpty()) {
            showErrorDialog("No Recipient", "Please select a recipient!");
            return;
        }

        String messageContent = txtFieldmsgContent.getText();
        if (messageContent.isEmpty()) {
            showErrorDialog("Empty Message Content", "Please write a message before trying to send one");
            return;
        }
        
        // Set Date Time
        now = LocalDateTime.now();
        formattedDateTime = now.format(formatter);

        String textFormat = currentEmp + " (" + currentEmpType + ") : ";

        
        String newMessage = "\n\n" + textFormat + messageContent + "\nSent Date : "+formattedDateTime;
        currentChatHistory += newMessage;
        textAreaChatBody.setText(currentChatHistory);
        
        boolean changeStatus  = false;
        
        if(allAvailableChats != null){
            for (ChatModel chatObj:allAvailableChats){
                if (chatObj.chatBelongingCheck(currentEmpType, receiver)){
                    chatObj.setChatHistory(currentChatHistory);
                    changeStatus = true;
                }
            }
        }
        
        if(!changeStatus){
           currentChat = new ChatModel(currentEmpType, receiver, currentChatHistory);
           allAvailableChats.add(currentChat);
        }
        
        RelationshipDatabaseClass.getInstance().setAllAvailableChats(allAvailableChats);
        
        
        txtFieldmsgContent.clear();
    }

    public void setCurrentUserWhileSceneSwitch(String empName, String empType) {
        currentEmp = empName;
        currentEmpType = empType;
        comboBoxReceiverList.getItems().remove(currentEmpType);
    }

    private void showErrorDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "alert", rootPane);
        alertDialog.openMFXDialog();
    }

    private void showSuccessDialog(String title, String content) {
        MFXDialog alertDialog = new MFXDialog(title, content, "Close", "success", rootPane);
        alertDialog.openMFXDialog();
    }

    @FXML
    private void loadChatOnRecipientSelection(ActionEvent event) {
        String receiver = comboBoxReceiverList.getSelectedItem();
        currentChatHistory = ""; // Reset the history
        currentChat = null; // Reset the current chat model

        if(allAvailableChats != null){
            for (ChatModel chatObj:allAvailableChats){
                if (chatObj.chatBelongingCheck(currentEmpType, receiver)){
                    currentChat = chatObj;
                    currentChatHistory = currentChat.getChatHistory();
                }
            }
        }
        
        System.out.println(currentChat);

        textAreaChatBody.setText(currentChatHistory);
    }

}
