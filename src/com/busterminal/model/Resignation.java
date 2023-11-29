package com.busterminal.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samin
 * 
 */
public class Resignation implements Serializable{

    @Override
    public String toString() {
        return "Resignation{" + "empName=" + empName + ", empId=" + empId + ", resignationId=" + resignationId + ", subject=" + subject + ", Letter=" + Letter + '}';
    }
    
   private String empName;
   private int empId;
   private int resignationId;
   private String subject;
   private String Letter;

    public Resignation() {
    }

    public Resignation(String empName, int empId, int resignationId, String subject, String Letter) {
        this.empName = empName;
        this.empId = empId;
        this.resignationId = resignationId;
        this.subject = subject;
        this.Letter = Letter;
    }
    public Resignation(int empId,String empName,String subject){
        this.empId = empId;
        this.empName = empName;
        this.subject= subject;
        
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getResignationId() {
        return resignationId;
    }

    public void setResignationId(int resignationId) {
        this.resignationId = resignationId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLetter() {
        return Letter;
    }

    public void setLetter(String Letter) {
        this.Letter = Letter;
    }
   
    public String readLetter(String filename) throws FileNotFoundException{
        File file = new File(filename);
        Scanner sc; String str=null; String str1="";
        try {
            sc = new Scanner(file);         
            while(sc.hasNextLine()){
                str=sc.nextLine();             
                 str1+=str;                 
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Resignation.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return str1;
    }
}   