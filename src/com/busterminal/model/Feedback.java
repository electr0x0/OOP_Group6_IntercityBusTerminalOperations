
package com.busterminal.model;

import java.io.Serializable;


public class Feedback implements Serializable{
    public String name;
    public String feedback;
    
    public Feedback(String name, String feedback){
        this.name = name;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Feedback{" + "name=" + name + ", feedback=" + feedback + '}';
    }
}
