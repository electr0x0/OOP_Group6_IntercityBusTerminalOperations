
package com.busterminal.model;

import java.io.Serializable;

/**
 *
 * @author UseR
 */
public class Contact implements Serializable {
    
    private String inquiryType;
    private String name;
    private String email;
    private String subject;
    private String details;

    public Contact(String inquiryType, String name, String email, String subject, String details) {
        this.inquiryType = inquiryType;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.details = details;
    }

    public Contact() {
    }

    public String getInquiryType() {
        return inquiryType;
    }

    public void setInquiryType(String inquiryType) {
        this.inquiryType = inquiryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
    
}
