package org.ooad_dws4;

/**
 *
 */
public class EventScheduler extends DWSObject {


    /**
     *
     */

    private MainController mainController;

    private LinkedList<Event> eventQueue;

    private Event defaultScreenTimer;

    private Event buzzerOffTimer;

    /**
     * Default constructor
     */
    public EventScheduler() {
        eventQueue = new LinkedList<Event>();
    }
    /**
     * @param mainController MainController Object
     * @return
     */
    public void linkObjects(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @param systemTime
     */
    public void broadcast(long systemTime) {
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
    public Message getMessageAndReset(long deadline) {
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
    public boolean removeBuzzerOffEvent() {
        // TODO implement here
        return false;
    }


}