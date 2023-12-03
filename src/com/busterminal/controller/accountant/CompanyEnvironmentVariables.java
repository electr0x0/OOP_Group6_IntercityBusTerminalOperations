/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.controller.accountant;

/**
 *
 * @author electr0
 */
public enum  CompanyEnvironmentVariables {
    
    COMPANY("XYZ BUS Company", "1234 Main Street", "01773666439", "xyzbuscomany@buscompany.com");

    public final String NAME;
    public final String ADDRESS;
    public final String PHONENUMBER;
    public final String EMAIL;

    CompanyEnvironmentVariables(String NAME, String ADDRESS, String PHONENUMBER, String EMAIL) {
        this.NAME = NAME;
        this.ADDRESS = ADDRESS;
        this.PHONENUMBER = PHONENUMBER;
        this.EMAIL = EMAIL;
    }
    
}
