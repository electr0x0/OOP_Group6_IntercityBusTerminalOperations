/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.terminalManager;

import java.io.Serializable;

/**
 *
 * @author electr0
 */
public class ChatModel implements Serializable{
    private String sender, receiver;
    private String chatHistory;

    public ChatModel(String sender, String receiver, String chatHistory) {
        this.sender = sender;
        this.receiver = receiver;
        this.chatHistory = chatHistory;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }
    
    public boolean chatBelongingCheck(String empTypeA, String empTypeB){
        boolean checkFirstCase = empTypeA.equals(sender) || empTypeA.equals(receiver);
        boolean checkSecondCase = empTypeB.equals(sender) || empTypeB.equals(receiver);
        
        return checkFirstCase && checkSecondCase;
    }
    
    
}
