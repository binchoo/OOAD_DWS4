package org.ooad_dws4;

import java.util.*;

/**
 * 
 */
public class DDay {

    /**
     * Default constructor
     */
    public DDay() {
    }

    /**
     * true = on  false =off
     */
    private boolean state;

    /**
     * 
     */
    private long date;


    /**
     * toggle day state (on<->off)
     */
    public void changeState() {
        // TODO implement here
        if (state){state=false;}
        else{state= true;}
    }

    /**
     * 
     */
    public long getDdayData() {
        // TODO implement here
        return date;
    }

}