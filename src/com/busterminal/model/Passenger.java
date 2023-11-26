/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import com.busterminal.controller.AppendableObjectOutputStream;
import com.busterminal.controller.passenger.SupportController;
import java.io.EOFException;
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
import javafx.scene.control.TextArea;

/**
 *
 * @author Samin
 */
public class Passenger implements Serializable{
    
    private String name,email;
    //private ArrayList<String> seatNumber;

    public Passenger(String name, String email, int contactNum) {
        this.name = name;
        this.email = email;
        this.contactNum = contactNum;
    }

    
    private  int contactNum;

    

    public void purchaseTicket(String ticketId, DummyClassForTableViewSchedule schedule, ArrayList<String> seatNumbers, String status, String purchaseDate) {
        try {
            Ticket ticket = new Ticket(ticketId, this, schedule, seatNumbers, seatNumbers.size(), status, purchaseDate);

            File ticketFile = new File("TicketList.bin");
            FileOutputStream ticketFos;
            ObjectOutputStream ticketOos;

            if (ticketFile.exists()) {
                ticketFos = new FileOutputStream(ticketFile, true);
                ticketOos = new AppendableObjectOutputStream(ticketFos);
            } else {
                ticketFos = new FileOutputStream(ticketFile);
                ticketOos = new ObjectOutputStream(ticketFos);
            }

            ticketOos.writeObject(ticket);
            ticketOos.close();
            ticketFos.close();
        } catch (IOException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    
        
        
}
    
     public void doReserve(String reserveId, String name, String email, int phone, String busType, int fare, LocalDate date) {
        
        ReserveBus reserveBus = new ReserveBus( busType, fare, date);
        Reservation reservation = new Reservation(name, email, phone,reserveId,reserveBus);

        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            f = new File("ReservationList.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(reservation);

        } catch (IOException ex) {
            Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch (IOException ex) {
                Logger.getLogger(SupportController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }

    
      public static void viewTicketDetails(TextArea ticketDetailsTextArea) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("TicketList.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Ticket t;

            while (true) {
                t = (Ticket) ois.readObject();

                ticketDetailsTextArea.appendText("Passenger Info:" + "\n");
                ticketDetailsTextArea.appendText("Passenger Name: " + t.getPassenger().getName() + "\n");
                ticketDetailsTextArea.appendText("Contact Number: " + t.getPassenger().getContactNum() + "\n" + "\n");

                ticketDetailsTextArea.appendText("Journey Info:" + "\n");
                ticketDetailsTextArea.appendText("BusID: " + t.getDummy().getBusId() + "\n");
                ticketDetailsTextArea.appendText("Ticket ID: " + t.getTicketId() + "\n");
                ticketDetailsTextArea.appendText("Seats: " + t.getSeatNumber1() + "\n");
                ticketDetailsTextArea.appendText("Quantity: " + t.getTicketQty() + "\n" + "\n");

                ticketDetailsTextArea.appendText("Source: " + t.getDummy().getSource() + "\n");
                ticketDetailsTextArea.appendText("Destination: " + t.getDummy().getDestination() + "\n" + "\n");

                ticketDetailsTextArea.appendText("Time: " + t.getDummy().getTimeSlot() + "\n");
                ticketDetailsTextArea.appendText("Date: " + t.getDummy().getScheduleDate() + "\n" + "\n");
                ticketDetailsTextArea.appendText("Booking Date: " + t.getPurchaseDate() + "\n\n");

            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException ex) {
                // Handle the exception if needed
            }
        }
    }

   
    public Passenger() {
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

  
   

    public int getContactNum() {
        return contactNum;
    }

    public void setContactNum(int contactNum) {
        this.contactNum = contactNum;
    }
}


