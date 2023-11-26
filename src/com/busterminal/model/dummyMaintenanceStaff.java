package com.busterminal.model;

public  class dummyMaintenanceStaff { //
    // ArrayList<>NotificationEvent = hold the coloumn raw 
    private String staffId;
    private String staffName;
    private String driverId;
    private String busId;
    private String maintenanceType;
    private String lastMaintenanceDate;
    private String requestDate;

    public dummyMaintenanceStaff(String staffId, String staffName, String driverId, String busId, String maintenanceType, String lastMaintenanceDate, String requestDate) {
        this.staffId = staffId;
        this.staffName = staffName;
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

    public String getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(String lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
