package com.busterminal.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MaintenanceTask extends dummyMaintenanceStaff {

    private String description;

    public MaintenanceTask(String staffId, String staffName, String driverId, String busId, String maintenanceType, String lastMaintenanceDate, String requestDate, String description) {
        super(staffId, staffName, driverId, busId, maintenanceType, lastMaintenanceDate, requestDate);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ObservableList<MaintenanceStaff> getEmployeeList() {
        ObjectInputStream ois = null;
        ObservableList<MaintenanceStaff> list = FXCollections.observableArrayList();
        try {
            MaintenanceStaff e;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceStaffList.bin"));
            while (true) {
                e = (MaintenanceStaff) ois.readObject();
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

    public static int totalstaff() {
        return getEmployeeList().size();
    }
}
