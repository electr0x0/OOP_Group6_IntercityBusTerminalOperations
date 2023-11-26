package com.busterminal.model;

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
}