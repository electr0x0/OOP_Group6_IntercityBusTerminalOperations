/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.utilityclass;

import com.busterminal.model.Passenger;
import com.busterminal.model.Ticket;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author electr0
 */
public class DataLoader {
    
    public static ArrayList<Ticket> loadTicketsFromFile() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        File f = new File("TicketList.bin");

        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    try {
                        Ticket ticket = (Ticket) ois.readObject();
                        tickets.add(ticket);
                    } catch (EOFException e) {
                        break; // End of file reached
                    } catch (ClassNotFoundException e) {
                        Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tickets;
    }
    
    public static ArrayList<Passenger> loadPassengersFromFile() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        File f = new File("PassengerList.bin");

        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                while (true) {
                    try {
                        Passenger passenger = (Passenger) ois.readObject();
                        passengers.add(passenger);
                    } catch (EOFException e) {
                        break; // End of file reached
                    } catch (ClassNotFoundException e) {
                        Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return passengers;
    }
    
}
