package com.busterminal.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Administrator {
    
    
    public static void employeeCreateNewAccount(DummyEmployee e) {
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;

        try {
            f = new File("Employee.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void clientCreateNewAccount(Client c) {
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;

        try {
            f = new File("../Client.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(c);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static ObservableList <Client> getClientList() {
        ObjectInputStream ois = null;
        ObservableList <Client> list = FXCollections.observableArrayList();
        try {
             Client c;
             ois = new ObjectInputStream(new FileInputStream("Client.bin"));
             
            while(true){
                c = (Client) ois.readObject();
                list.add(c);
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
        catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException ex1) {  }           
        }
        return list;
    } 
    
    public static ObservableList <DummyEmployee> getEmployeeList() {
        ObjectInputStream ois = null;
        ObservableList <DummyEmployee> list = FXCollections.observableArrayList();
        try {
             DummyEmployee e;
             ois = new ObjectInputStream(new FileInputStream("Employee.bin"));
             
            while(true){
                e= (DummyEmployee) ois.readObject();
                list.add(e);
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
        catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException ex1) {  }           
        }
        return list;
    } 
    
    
    /*public static void deleteEmployee(Employee p) {
        ArrayList <Employee> employeelist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
             Package c;
             ois = new ObjectInputStream(new FileInputStream("Employee.bin"));
             
            while(true){
                c = (Package) ois.readObject();
                //if(!c.getTitle().equals(p.getTitle())) pkgList.add(c);
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
        catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException ex1) {  }           
        }
        File file = new File("Employee");
        file.delete();
        
        for (int i = 0; i <employeelist.size(); i ++) {
            File f = null;
            FileOutputStream fos = null;      
            ObjectOutputStream oos = null;

            try {
                f = new File("Eployee.bin");
                if(f.exists()){
                    fos = new FileOutputStream(f,true);
                    oos = new AppendableObjectOutputStream(fos);                
                }
                else{
                    fos = new FileOutputStream(f);
                    oos = new ObjectOutputStream(fos);               
                }
                oos.writeObject(employeelist.get(i));

            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(oos != null) oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/
    
    
   /* public static ObservableList <Salary> getSalaryList() {
        ObservableList <Salary> list = FXCollections.observableArrayList();
        ObjectInputStream ois = null;
        try {
             Salary c;
             ois = new ObjectInputStream(new FileInputStream("Salary.bin"));
             
            while(true){
                c = (Salary) ois.readObject();
                list.add(c);
            }
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
        catch (Exception ex) {
            try {
                if(ois!=null)
                    ois.close();
            } catch (IOException ex1) {  }           
        }
        return list;
    }*/
    
    
}
