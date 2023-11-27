
package com.busterminal.model;

import java.io.Serializable;


public class Feedback implements Serializable{
    private String name;
    private int feedbackID;
    private String subject;
    private String body;
    
    public Feedback() {
    }

    public Feedback(String name, int feedbackID, String subject, String body) {
        this.name = name;
        this.feedbackID = feedbackID;
        this.subject = subject;
        this.body = body;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Feedback{" + "name=" + name + ", feedbackID=" + feedbackID + ", subject=" + subject + ", body=" + body + '}';
    }
    
    
    
    
 
   
}
