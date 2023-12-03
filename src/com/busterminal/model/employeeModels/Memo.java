/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.employeeModels;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Memo implements Serializable {
    String senderName;
    String email;
    String senderDesignation;
    String id;
    
    String subject;
    String body;
    
    ArrayList<String> tags;
    
    LocalDate date;
    LocalTime time;

    public Memo(String senderName, String email, String senderDesignation, String id, String subject, String body, ArrayList<String> tags, LocalDate date,LocalTime time) {
        this.senderName = senderName;
        this.email = email;
        this.senderDesignation = senderDesignation;
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.tags = tags;
        this.date = date;
        this.time = time;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenderDesignation() {
        return senderDesignation;
    }

    public void setSenderDesignation(String senderDesignation) {
        this.senderDesignation = senderDesignation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
    
    
    
}
