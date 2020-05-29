package org.ooad_dws4;

import java.util.*;

/**
 * 
 */
public class Message {

    public int getDestination() {
        return destination;
    }

    public String getAction() {
        return action;
    }

    public Dictionary<String, String> getArg() {
        return arg;
    }

    private int destination;
    private String action;
    private Dictionary<String, String> arg;

    public Message(int destination, String action, Dictionary<String, String> arg) {
        this.destination = destination;
        this.action = action;
        this.arg = arg;
    }



}