/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class GuideLine implements Serializable {
    private LocalDate date;
    private String text;

    public GuideLine(LocalDate date, String text) {
        this.date = date;
        this.text = text;
    }
    public GuideLine(String text){
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    
    
}
