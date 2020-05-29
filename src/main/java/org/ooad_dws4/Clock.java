package org.ooad_dws4;

import java.util.*;

/**
 *
 */
public class Clock implements Runnable {
    private long timeUnit;
    private PulseMaker pulseMaker;

    /**
     * Default constructor
     */
    public Clock(long timeUnit) {
        this.timeUnit = timeUnit;
    }
    /**
     * @param pulseMaker PulseMaker Object
     * @return
     */
    public void linkObjects(PulseMaker pulseMaker){
        this.pulseMaker = pulseMaker;
    }

    public void run() {
        while (true) {
            try {
                pulseMaker.makePulse(timeUnit);
                Thread.sleep(timeUnit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
