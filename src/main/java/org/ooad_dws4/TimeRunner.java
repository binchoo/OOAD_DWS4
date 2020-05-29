package org.ooad_dws4;

import java.util.*;

/**
 * 
 */
public class TimeRunner extends DWSObject implements PulseMaker {

    /**
     * Default constructor
     */
    public TimeRunner() {
        systemTime = 0;
        timeZone = 0;
    }

    /**
     * 
     */
    private long systemTime;

    /**
     * 
     */
    private int timeZone;

    private MainController mainController;

    /**
     * 
     */
    public void pulse() {
        // TODO implement here
    }

    /**
     * @param msg 
     * @return
     */
    public Message systemTimeUpdate(Message msg) {
        // TODO implement here
        return null;
    }

    /**
     * @param msg 
     * @return
     */
    public Message changeTimeZone(Message msg) {
        // TODO implement here
        return null;
    }

    @Override
    public void makePulse() {
        System.out.println("Time Running");
    }
}