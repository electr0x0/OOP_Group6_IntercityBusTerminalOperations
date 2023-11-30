package com.busterminal.model;

import static com.busterminal.model.MaintenanceTask.getTaskList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class MaintenanceStaff extends DummyEmployee implements Serializable {

    private String staffType;

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffType() {
        return staffType;
    }

    public int getiD() {
        return iD;
    }

    public MaintenanceStaff(String designation, LocalDate dateofJoining, int iD, String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String password, String phoneNumber, String gender, int Salary, String staffType) {
        super(designation, dateofJoining, iD, firstName, lastName, address, email, dateOfBirth, password, phoneNumber, gender, Salary);
        this.staffType = staffType;
    }

    public static ObservableList<MaintenanceStaff> getEmployeeList() {
        ObjectInputStream ois = null;
        ObservableList<MaintenanceStaff> list = FXCollections.observableArrayList();
        try {
            MaintenanceStaff e;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceStaffList.bin"));
            while (true) {
                e = (MaintenanceStaff) ois.readObject();
                list.add(e);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }
        return list;
    }
    
     public static void employeeCreateNewAccount(MaintenanceStaff e) {
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("MaintenanceStaffList.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(e);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
     
     public static void deleteEmployee(MaintenanceStaff p) {
        // create a arraylist for storing all intance from bin file
        ArrayList<MaintenanceStaff> employeelist = new ArrayList<>();
        ObjectInputStream ois = null;
        try {
            MaintenanceStaff c;
            ois = new ObjectInputStream(new FileInputStream("MaintenanceStaffList.bin"));

            while (true) {
                c = (MaintenanceStaff) ois.readObject();
                System.out.println(c.getID());
                if (!(c.getID()== p.getID())){
                    employeelist.add(c);
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
            }
        }

        // get file path
        File file = new File("MaintenanceStaffList.bin");

        // Now delete the file
        file.delete();

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        // again create a new file
        f = new File("MaintenanceStaffList.bin");
        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            System.out.println(employeelist);
            for (MaintenanceStaff e : employeelist) {
                oos.writeObject(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Package.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }

    }
     
      public static int totalstaff() {
        return getTaskList().size();
    }
     

}
