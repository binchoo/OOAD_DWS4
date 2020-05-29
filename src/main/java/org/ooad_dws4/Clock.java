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
    public Clock(long timeUnit, PulseMaker pulseMaker) {
        this.timeUnit = timeUnit;
        this.pulseMaker = pulseMaker;
    }

    public Clock(PulseMaker p, long timeUnit) {
        this.timeUnit = timeUnit;
        this.pulseMaker = p;
    }

    public void run() {
        while (true) {
            try {
                pulseMaker.makePulse();
                Thread.sleep(timeUnit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
