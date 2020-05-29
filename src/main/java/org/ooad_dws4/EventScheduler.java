package org.ooad_dws4;

import java.util.*;

/**
 * 
 */
public class EventScheduler {

    /**
     * Default constructor
     */
    public EventScheduler() {
    }

    /**
     * 
     */
    private List<Event> eventQueue;

    /**
     * 
     */
    private Event defaultScreenTimer;

    /**
     * 
     */
    private Event buzzerOffTimer;



    /**
     * @param systemTime
     */
    public void broadcast(Long systemTime) {
        // TODO implement here
    }

    /**
     * 
     */
    public void defaultScreenTimerReset() {
        // TODO implement here
    }

    /**
     * 
     */
    public void sortEventQueue() {
        // TODO implement here
    }

    /**
     * 
     */
    public void decreaseDeadline() {
        // TODO implement here
    }

    /**
     * @param target
     */
    public void removeEvent(Message target) {
        // TODO implement here
    }

    /**
     * @param msg
     */
    public void pushEvent(Message msg) {
        // TODO implement here
    }

    /**
     * 
     */
    public void makeEvent() {
        // TODO implement here
    }

    /**
     * @param deadline 
     * @return
     */
    public Message getMessageAndReset(Long deadline) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Message getMessage() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Boolean removeBuzzerOffEvent() {
        // TODO implement here
        return null;
    }

}