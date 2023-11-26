package com.busterminal.model;

public class Resignation {

    private final String employeeId;
    private final String employeeName;
    private final String resignationReason;

    public Resignation(String employeeId, String employeeName, String resignationReason) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.resignationReason = resignationReason;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getResignationReason() {
        return resignationReason;
    }

    // You can add setters if needed

    @Override
    public String toString() {
        return employeeName + " (" + employeeId + ")";
    }
}
