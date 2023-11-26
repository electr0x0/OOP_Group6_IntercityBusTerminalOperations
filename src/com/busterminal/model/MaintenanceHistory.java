
package com.busterminal.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaintenanceHistory implements Serializable {
    private int id;
    private LocalDate lastUpdate;
    private String issueResolved;

    

    public MaintenanceHistory(int id, LocalDate lsastUpdate, String issueResolved) {
        this.id = id;
        this.lastUpdate = lsastUpdate;
        this.issueResolved = issueResolved;
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
    
    
    
    
}
