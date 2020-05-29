package org.ooad_dws4;

import java.util.*;

/**
 * 
 */
public class Mode {

    /**
     * Default constructor
     */
    public Mode() {
    }

    /**
     * 
     */
    protected Boolean isActivate;

    /**
     * 
     */
    protected int state;


    /**
     * @param systemTime
     */
    public void broadcast(Long systemTime) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Message getModeData() {
        // TODO implement here
        return null;
    }

    /**
     * 
     */
    public void toggleModeActivation() {
        // TODO implement here
    }

    /**
     * 
     */
    public void saveModeActivation() {
        // TODO implement here
    }

    /**
     * @param event
     */
    public void modeModify(int event) {
        // TODO implement here
    }

    /**
     * @param state
     */
    public void changeState(int state) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Message update() {
        // TODO implement here
        return null;
    }

    /**
     * @param msg 
     * @return
     */
    public Boolean receiveMessage(Message msg) {
        // TODO implement here
        return null;
    }

}