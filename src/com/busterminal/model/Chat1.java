
package com.busterminal.model;

/**
 *
 * @author UseR
 */
public class Chat1 {
    
    String receiver;
    String body;

    public Chat1(String receiver, String body) {
        this.receiver = receiver;
        this.body = body;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Chat1{" + "receiver=" + receiver + ", body=" + body + '}';
    }
    
    
}
