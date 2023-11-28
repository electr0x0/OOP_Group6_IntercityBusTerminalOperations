/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import com.busterminal.controller.AppendableObjectOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author UseR
 */
public class Driver   {

    public Driver() {
    }
    
    
  public void requestMaintenance(String busId, String maintenanceType, String comment, LocalDate requestDate, String details) {
       
        Maintenance maintenanceRequest = new Maintenance(busId, maintenanceType, comment, requestDate, details);

       
        ArrayList<Maintenance> maintenanceList = new ArrayList<>();
        maintenanceList.add(maintenanceRequest);

      
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("MaintenanceList.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            for (Maintenance m : maintenanceList) {
                oos.writeObject(m);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
