
package com.busterminal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    
    public void receiveResponse(){
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("Contact.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Contact f2;
            try{
                //outputTextArea.setText("");
                while(true){
                    //System.out.println("Printing objects.");
                    f2= (Contact)ois.readObject();
                    //Object obj = ois.readObject();
                    //obj.submitReport();
                    //f2.submitReport();
                    System.out.println(f2);
                    //outputTextArea.appendText(emp.toString());
                }
            }//end of nested try
            catch(Exception e){
                // handling code
            }//nested catch     
            //outputTextArea.appendText("All objects are loaded successfully...\n");            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } 
        finally {
            try {
                
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }           
    
    
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
