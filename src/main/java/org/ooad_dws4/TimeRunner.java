package org.ooad_dws4;

import jdk.tools.jaotc.Main;

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

    public void linkObject(MainController mainController){
        this.mainController = mainController;
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
    public void makePulse(long timeUnit) {
        systemTime += timeUnit;
        mainController.broadcast(systemTime);
    }
}