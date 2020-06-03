package org.ooad_dws4;

import java.util.HashMap;

public class Message {

    public int getDestination() {
        return destination;
    }

    public String getAction() {
        return action;
    }

    public HashMap<String, String> getArg() {
        return arg;
    }

    private int destination;
    private String action;
    private HashMap<String, String> arg;

    public Message(int destination, String action, HashMap<String, String> arg) {
        this.destination = destination;
        this.action = action;
        this.arg = arg;
    }



}