package org.ooad_dws4;

/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief System clock in DWS
 */

public class Clock implements Runnable {

    /**
     * @brief System Clock Data
     */
    static long timeUnit;
    static long defaultScreenTime;
    static long buzzerOffTime;

    /**
     * @brief The objects that Clock can use.
     */
    private PulseMaker pulseMaker;

    /**
     * @brief Default constructor
     */
    public Clock() {
    }

    /**
     * @param pulseMaker    TimeRunner Object   Object Number : 21
     */
    public void linkObjects(PulseMaker pulseMaker) {
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
