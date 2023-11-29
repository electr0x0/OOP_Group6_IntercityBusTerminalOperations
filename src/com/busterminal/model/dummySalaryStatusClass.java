package com.busterminal.model;

import java.time.LocalDate;

public class dummySalaryStatusClass extends DummyEmployee {

    private LocalDate paymentDate;
    private String paidStatus;

    public dummySalaryStatusClass(LocalDate paymentDate, String paidStatus, String designation, LocalDate dateofJoining, int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender) {
        super(designation, dateofJoining, iD, firstName, lastName, address, email, dateOfBirth, password, phoneNumber, gender);
        this.paymentDate = paymentDate;
        this.paidStatus = paidStatus;
    }

public dummySalaryStatusClass(int id, String name, String designation, int salary, LocalDate paymentDate, String status) {
        super(designation, salary, id, name);
        this.iD = id;
        this.paymentDate = paymentDate;
        this.paidStatus = status;

    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public int getiD() {
        return iD;
    }

    public String getPassword() {
        return password;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
