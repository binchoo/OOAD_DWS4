package org.ooad_dws4;

import java.util.HashMap;

public class Message {

    private int destination;
    private String action;
    private HashMap<String, String> arg;

    public Message(int destination, String action, HashMap<String, String> arg) {
        this.destination = destination;
        this.action = action;
        this.arg = arg;
    }

    public int getDestination() {
        return destination;
    }

    public String getAction() {
        return action;
    }

    public HashMap<String, String> getArg() {
        return arg;
    }


    public void doMessageAction() {
//        #REMIND# forTestCode

//        System.out.print("dest:" + this.destination + ", action:" + this.action + ", arg->");
//        if (this.arg == null)
//            System.out.print("NULL");
//        else
//            for (HashMap.Entry m : this.arg.entrySet())
//                System.out.printf(" {%s:%s} ", m.getKey(), m.getValue());
//        System.out.println();
    }

}