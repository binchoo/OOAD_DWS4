package org.ooad_dws4;

/**
 * 
 */
public class Timer {

    /**
     * Default constructor
     */
    public Timer() {
    }

    /**
     * 
     */
    private long deadline;




    /**
     * 
     */
    public void changeState() {
        // TODO implement here
        // ???
    }

    /**
     *   return deadline
     */
    public long getDeadlineData() {
        // TODO implement here

        return deadline;
    }

    /**
     *  setDeadline
     */
    public void setDeadlineData(long deadline) {
        // TODO implement here

        this.deadline =deadline;
    }

    /**
     *   reset deadline
     */
    public void reset() {
        // TODO implement here

        deadline=0;

    }



}