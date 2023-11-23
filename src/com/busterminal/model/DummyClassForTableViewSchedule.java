
package com.busterminal.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author UseR
 */
public class DummyClassForTableViewSchedule implements Serializable {
    private Bus bus;
    private String busId;
    //private   int     scheduleId;
    private   String  source;
    private   String  destination;
    private   int     adultFare;
    private   String  timeSlot;
    private   LocalDate scheduleDate;

    public DummyClassForTableViewSchedule(Bus bus, String source, String destination, int adultFare, String timeSlot, LocalDate scheduleDate) {
        this.bus = bus;
        //this.scheduleId = scheduleId;
        this.source = source;
        this.destination = destination;
        this.adultFare = adultFare;
        this.timeSlot = timeSlot;
        this.scheduleDate = scheduleDate;
    }

    public DummyClassForTableViewSchedule(String busId, String source, String destination, int adultFare, String timeSlot, LocalDate scheduleDate) {
        this.busId = busId;
        this.source = source;
        this.destination = destination;
        this.adultFare = adultFare;
        this.timeSlot = timeSlot;
        this.scheduleDate = scheduleDate;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

   

    

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAdultFare() {
        return adultFare;
    }

    public void setAdultFare(int adultFare) {
        this.adultFare = adultFare;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public DummyClassForTableViewSchedule() {
    }

    

    //@Override
    //public String toString() {
        //return "DummyClassForTableViewSchedule{" + "scheduleId=" + scheduleId + ", source=" + source + ", destination=" + destination + ", adultFare=" + adultFare + ", timeSlot=" + timeSlot + ", scheduleDate=" + scheduleDate + '}';
   // }
    
    
    
    
    
}