/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busterminal.model;

import java.time.LocalDate;


public class TripHistory {
    private LocalDate date;
    private String route, busId, passengerFeedback, comments, status ;

    @Override
    public String toString() {
        return "TripHistory{" + "date=" + date + ", route=" + route + ", busId=" + busId + ", passengerFeedback=" + passengerFeedback + ", comments=" + comments + ", status=" + status + '}';
    }

    public TripHistory() {
    }

    public TripHistory(LocalDate date, String route, String busId, String passengerFeedback, String comments, String status) {
        this.date = date;
        this.route = route;
        this.busId = busId;
        this.passengerFeedback = passengerFeedback;
        this.comments = comments;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPassengerFeedback() {
        return passengerFeedback;
    }

    public void setPassengerFeedback(String passengerFeedback) {
        this.passengerFeedback = passengerFeedback;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
