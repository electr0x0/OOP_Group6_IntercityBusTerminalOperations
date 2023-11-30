/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.storage.db;

import com.busterminal.model.Bus;
import com.busterminal.model.BusTrip;
import com.busterminal.model.BusTripSchedule;
import com.busterminal.model.Employee;
import com.busterminal.model.accountant.PurchaseEntry;
import com.busterminal.model.accountant.RefundRequest;
import com.busterminal.model.accountant.ReimbursementInfo;
import com.busterminal.model.accountant.Transaction;
import com.busterminal.model.terminalManager.ChatModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author electr0
 */
public class RelationshipDatabaseClass implements Serializable {

    private static RelationshipDatabaseClass instance;

    private ArrayList<String> allLocations;
    private ArrayList<String> allTimes;
    private ArrayList<String> allBusStands;
    private ArrayList<Bus> allAvailableBuses;
    private int busIdCounter;
    private ArrayList<BusTrip> allTripList;
    private ArrayList<BusTripSchedule> allAvailableTripSchedules;
    private int currentScheduleID;
    private int currentInvoiceCounter;
    private int reimbursementIDCounter;
    private ArrayList<ReimbursementInfo> reimbursementList;
    private ArrayList<ChatModel> allAvailableChats;
    private ArrayList<Transaction> allAvailableTransactions;
    private ArrayList<PurchaseEntry> currentInventory;
    private ArrayList<RefundRequest> allRefundRequest;

    private String refundPolicy;

    private ArrayList<Employee> allEmployees;
    
    private String currentLoggedIn;

    public void setCurrentInvoiceCounter(int currentInvoiceCounter) {
        this.currentInvoiceCounter = currentInvoiceCounter;
        saveToFile();
    }

    // Private constructor to prevent instantiation
    private RelationshipDatabaseClass() {
    }

    // Static method to get the singleton instance
    public static RelationshipDatabaseClass getInstance() {
        loadFromFile();
        if (instance == null) {
            instance = new RelationshipDatabaseClass();
        }
        return instance;
    }

    public ArrayList<String> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(ArrayList<String> allLocations) {
        this.allLocations = allLocations;
        saveToFile();
    }

    public ArrayList<String> getAllTimes() {
        return allTimes;
    }

    public void setAllTimes(ArrayList<String> allTimes) {
        this.allTimes = allTimes;
        saveToFile();
    }

    public ArrayList<String> getAllBusStands() {

        return allBusStands;
    }

    public void setAllBusStands(ArrayList<String> allBusStands) {
        this.allBusStands = allBusStands;
        saveToFile();
    }

    public ArrayList<Bus> getAllAvailableBuses() {
        System.out.println("Loaded All Buses" + allAvailableBuses);
        return allAvailableBuses;
    }

    public void setAllAvailableBuses(ArrayList<Bus> allAvailableBuses) {
        this.allAvailableBuses = allAvailableBuses;
        System.out.println(allAvailableBuses);
        saveToFile();
    }

    public int getBusIdCounter() {
        System.out.println("Bus Id Counter loaded from ObjectDatabase");
        return busIdCounter;
    }

    public void setBusIdCounter(int busIdCounter) {
        System.out.println("Bus Id counter saved to ObjectDatabase");
        this.busIdCounter = busIdCounter;
        saveToFile();
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GlobalClassDatabase.bin"))) {
            out.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("GlobalClassDatabase.bin"))) {
            instance = (RelationshipDatabaseClass) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BusTrip> getAllTripList() {
        return allTripList;
    }

    public void setAllTripList(ArrayList<BusTrip> allTripList) {
        this.allTripList = allTripList;
        saveToFile();
    }

    public ArrayList<BusTripSchedule> getAllAvailableTripSchedules() {
        return allAvailableTripSchedules;
    }

    public void setAllAvailableTripSchedules(ArrayList<BusTripSchedule> allAvailableTripSchedules) {
        this.allAvailableTripSchedules = allAvailableTripSchedules;
        saveToFile();
    }

    public int getCurrentScheduleID() {
        return currentScheduleID;
    }

    public void setCurrentScheduleID(int currentScheduleID) {
        this.currentScheduleID = currentScheduleID;
        saveToFile();
    }

    public int getReimbursementIDCounter() {
        return reimbursementIDCounter;
    }

    public void setReimbursementIDCounter(int reimbursementIDCounter) {
        this.reimbursementIDCounter = reimbursementIDCounter;
        saveToFile();
    }

    public void addItemToReimbursementList(ReimbursementInfo reimObj) {
        if (this.reimbursementList == null) {
            this.reimbursementList = new ArrayList<>();
            this.reimbursementList.add(reimObj);
        } else {
            this.reimbursementList.add(reimObj);
        }

        saveToFile();

    }

    public ArrayList<ReimbursementInfo> getReimbursementList() {
        return reimbursementList;
    }

    public void setReimbursementList(ArrayList<ReimbursementInfo> reimbursementList) {
        this.reimbursementList = reimbursementList;

        saveToFile();
    }

    public ArrayList<ChatModel> getAllAvailableChats() {
        return allAvailableChats;
    }

    public void setAllAvailableChats(ArrayList<ChatModel> allAvailableChats) {
        this.allAvailableChats = allAvailableChats;
        saveToFile();
    }

    public ArrayList<Transaction> getAllAvailableTransactions() {
        return allAvailableTransactions;
    }

    public void setAllAvailableTransactions(ArrayList<Transaction> allAvailableTransactions) {
        this.allAvailableTransactions = allAvailableTransactions;
        saveToFile();
    }

    public void addItemToAllAvailableTransactions(Transaction txnObj) {
        if (this.allAvailableTransactions == null) {
            this.allAvailableTransactions = new ArrayList<>();
            this.allAvailableTransactions.add(txnObj);
        } else {
            this.allAvailableTransactions.add(txnObj);
        }

        saveToFile();

    }

    public ArrayList<PurchaseEntry> getCurrentInventory() {
        return currentInventory;
    }

    public void setCurrentInventory(ArrayList<PurchaseEntry> currentInventory) {
        this.currentInventory = currentInventory;
        saveToFile();
    }

    public String getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(String refundPolicy) {
        this.refundPolicy = refundPolicy;
        saveToFile();
    }

    public void addItemToAllRefundRequest(RefundRequest refundObj) {
        if (this.allRefundRequest == null) {
            this.allRefundRequest = new ArrayList<>();
            this.allRefundRequest.add(refundObj);
        } else {
            this.allRefundRequest.add(refundObj);
        }

        saveToFile();

    }

    public ArrayList<RefundRequest> getAllRefundRequest() {
        return allRefundRequest;
    }

    public void setAllRefundRequest(ArrayList<RefundRequest> allRefundRequest) {
        this.allRefundRequest = allRefundRequest;
        saveToFile();
    }

    public int getCurrentInvoiceCounter() {
        return currentInvoiceCounter;
    }

    public ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public void setAllEmployees(ArrayList<Employee> allEmployees) {
        this.allEmployees = allEmployees;
        saveToFile();
    }
    
}
