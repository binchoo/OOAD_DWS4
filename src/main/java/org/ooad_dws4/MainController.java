package org.ooad_dws4;

import java.util.*;

/**
 *
 */
public class MainController {

    /**
     * Default constructor
     */
    public MainController() {
    }

    /**
     * @param ioBridge IOBridge Object
     * @param timeRunner TimeRunner Object
     * @param eventScheduler EventScheduler Object
     * @param modeManager ModeManager Object
     * @return
     */

    public void linkObjects(IOBridge ioBridge, TimeRunner timeRunner, EventScheduler eventScheduler, ModeManager modeManager) {
        this.ioBridge = ioBridge;
        this.timeRunner = timeRunner;
        this.eventScheduler = eventScheduler;
        this.modeManager = modeManager;
    }

    /**
     *
     */
    private TimeRunner timeRunner;

    /**
     *
     */
    private EventScheduler eventScheduler;

    /**
     *
     */
    private IOBridge ioBridge;

    /**
     *
     */
    private ModeManager modeManager;


    /**
     * @param systemTime
     */
    public void broadcast(long systemTime) {
        System.out.println("Time : " + systemTime);
    }

    /**
     * @param event
     */
    public void inputEvent(int event) {
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
    public void stopBuzzer() {
        // TODO implement here
    }

}