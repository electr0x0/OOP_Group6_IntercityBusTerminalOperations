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
    
    private String expenseType, prefPaymentMethod;
    
    private String status = "Unpaid";
    
    private int reimbursementIDCounter;
    
    private LocalDate submissionDate;
    

    public ReimbursementInfo(String empID, int expenseAmount, String expenseType, String prefPaymentMethod, LocalDate cDate) {
        this.empID = empID;
        this.expenseAmount = expenseAmount;
        this.expenseType = expenseType;
        this.prefPaymentMethod = prefPaymentMethod;
        this.submissionDate = cDate;
        if(RelationshipDatabaseClass.getInstance().getReimbursementIDCounter() != 0){
            reimbursementIDCounter = RelationshipDatabaseClass.getInstance().getReimbursementIDCounter();
            reimbursementIDCounter++;
        }
        genReimID();
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

}
