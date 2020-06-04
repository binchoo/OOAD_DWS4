package org.ooad_dws4;

/**
 *
 */
public class Timer {


    /**
     *
     */
    private long deadline;

    /**
     * Default constructor
     */
    public Timer() {
        this.deadline = 12846000L;
    }

    /**
     * return deadline
     */
    public long getDeadlineData() {
        return this.deadline;
    }


    /**
     * setDeadline
     */
    public void setDeadlineData(long deadline) {
        this.deadline = deadline;
    }

    /**
     * reset deadline
     */
    public void reset() {
        this.deadline = 0;
    }


}