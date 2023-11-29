
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

public class MaintenanceHistory implements Serializable {
    private int id;
    private LocalDate lastUpdate;
    private String issueResolved;
    private boolean status;

    

    public MaintenanceHistory(int id, LocalDate lsastUpdate, String issueResolved,boolean status) {
        this.id = id;
        this.lastUpdate = lsastUpdate;
        this.issueResolved = issueResolved;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLsastUpdate() {
        return lastUpdate;
    }

    public void setLsastUpdate(LocalDate lsastUpdate) {
        this.lastUpdate = lsastUpdate;
    }

    public String getIssueResolved() {
        return issueResolved;
    }

    public void setIssueResolved(String issueResolved) {
        this.issueResolved = issueResolved;
    }
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    public static void addItems(MaintenanceHistory e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("MaintenanceHistory.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(MaintenanceHistory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(MaintenanceHistory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     public static ObservableList<MaintenanceHistory> getItems() {
        ObjectInputStream ois = null;
        ObservableList<MaintenanceHistory> list = FXCollections.observableArrayList();
        try {
            MaintenanceHistory e;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceHistory.bin"));

            while (true) {
                e = (MaintenanceHistory) ois.readObject();
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
