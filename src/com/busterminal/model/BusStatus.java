package com.busterminal.model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class BusStatus {
    private  StringProperty busId;
    private  StringProperty busName;
    private  StringProperty driverName;
    private  StringProperty fuelConsumption;
    private  LocalDate lastMaintenanceDate;

    public BusStatus(String busId, String busName, String driverName, String fuelConsumption, LocalDate lastMaintenanceDate) {
        this.busId = new SimpleStringProperty(busId);
        this.busName = new SimpleStringProperty(busName);
        this.driverName = new SimpleStringProperty(driverName);
        this.fuelConsumption = new SimpleStringProperty(fuelConsumption);
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public String getBusId() {
        return busId.get();
    }

    public StringProperty busIdProperty() {
        return busId;
    }

    public String getBusName() {
        return busName.get();
    }

    public StringProperty busNameProperty() {
        return busName;
    }

    public String getDriverName() {
        return driverName.get();
    }

    public StringProperty driverNameProperty() {
        return driverName;
    }

    public String getFuelConsumption() {
        return fuelConsumption.get();
    }

    public StringProperty fuelConsumptionProperty() {
        return fuelConsumption;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }
}
