/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class GuideLine implements Serializable {
    private LocalDate date;
    private String text;

    @Override
    public String toString() {
        return "GuideLine{" + "date=" + date + ", text=" + text + '}';
    }

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
    
    public static void addItems(GuideLine e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("GuideLine.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(GuideLine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(GuideLine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     public static ObservableList<GuideLine> getItems() {
        ObjectInputStream ois = null;
        ObservableList<GuideLine> list = FXCollections.observableArrayList();
        try {
            GuideLine e;
            ois = new ObjectInputStream(new FileInputStream("GuideLine.bin"));

            while (true) {
                e = (GuideLine) ois.readObject();
                list.add(e);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return list;
    }
    
    
}
