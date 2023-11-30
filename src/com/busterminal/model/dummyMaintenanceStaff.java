package com.busterminal.model;

import java.time.LocalDate;

public  class dummyMaintenanceStaff { //
    // ArrayList<>NotificationEvent = hold the coloumn raw 
    private String staffId;
    private String staffName;
    private String driverId;
    private String busId;
    private String maintenanceType;
    private LocalDate lastMaintenanceDate;
    private LocalDate requestDate;

    public dummyMaintenanceStaff(String staffId, String staffName, String driverId, String busId, String maintenanceType, LocalDate lastMaintenanceDate, LocalDate requestDate) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.driverId = driverId;
        this.busId = busId;
        this.maintenanceType = maintenanceType;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.requestDate = requestDate;
    }
    
    

    public dummyMaintenanceStaff(String driverId, String busId, String maintenanceType, LocalDate lastMaintenanceDate, LocalDate requestDate) {
        this.driverId = driverId;
        this.busId = busId;
        this.maintenanceType = maintenanceType;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.requestDate = requestDate;
    }
    


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }
}
