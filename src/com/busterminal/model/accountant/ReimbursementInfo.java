/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model.accountant;

import com.busterminal.storage.db.RelationshipDatabaseClass;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author electr0
 */
public class ReimbursementInfo implements Serializable {
    private String reimbursementID,empID;
    private int expenseAmount;
    
    private String firstName, lastName;
    
    private String expenseType, prefPaymentMethod, designation;
    
    private String status = "Unpaid";
    
    private int reimbursementIDCounter;
    
    private LocalDate submissionDate;
    
    
    

    public ReimbursementInfo( String empID, int expenseAmount, String firstName, String lastName, String expenseType, String prefPaymentMethod, LocalDate submissionDate, String designation) {
        this.empID = empID;
        this.expenseAmount = expenseAmount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expenseType = expenseType;
        this.prefPaymentMethod = prefPaymentMethod;
        this.submissionDate = submissionDate;
        this.designation = designation;
        
        if(RelationshipDatabaseClass.getInstance().getReimbursementIDCounter() != 0){
            reimbursementIDCounter = RelationshipDatabaseClass.getInstance().getReimbursementIDCounter();
            reimbursementIDCounter++;
        }
        genReimID();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    
    public void genReimID(){
        String format = "REIM-";
        reimbursementID = format + reimbursementIDCounter;
        RelationshipDatabaseClass.getInstance().setReimbursementIDCounter(reimbursementIDCounter);
    }

    public String getReimbursementID() {
        return reimbursementID;
    }

    public void setReimbursementID(String reimbursementID) {
        this.reimbursementID = reimbursementID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getPrefPaymentMethod() {
        return prefPaymentMethod;
    }

    public void setPrefPaymentMethod(String prefPaymentMethod) {
        this.prefPaymentMethod = prefPaymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReimbursementIDCounter() {
        return reimbursementIDCounter;
    }

    public void setReimbursementIDCounter(int reimbursementIDCounter) {
        this.reimbursementIDCounter = reimbursementIDCounter;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    

}
