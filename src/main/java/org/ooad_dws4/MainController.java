package org.ooad_dws4;

import java.util.HashMap;

/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief Central Bride from DWS
 */
public class MainController {

    /**
     * @brief The objects that MainController can use
     */
    private TimeRunner timeRunner;
    private EventScheduler eventScheduler;
    private IOBridge ioBridge;
    private ModeManager modeManager;

    /**
     * @brief Default constructor
     */
    public MainController() {
    }

    /**
     * @param ioBridge       IOBridge Object.        Object Number : 10
     * @param timeRunner     TimeRunner Object.      Object Number : 21
     * @param eventScheduler EventScheduler Object.  Object Number : 22
     * @param modeManager    ModeManager Object.     Object Number : 30
     * @brief Make Reference Link with other Objects
     */
    public void linkObjects(IOBridge ioBridge, TimeRunner timeRunner, EventScheduler eventScheduler, ModeManager modeManager) {
        this.ioBridge = ioBridge;
        this.timeRunner = timeRunner;
        this.eventScheduler = eventScheduler;
        this.modeManager = modeManager;
    }

    /**
     * @param systemTime Pass the current system time throughout the system.
     * @brief The pulse started by TimeRunner is delivered to each part.
     * 1. Deadline check in eventScheduler. If there is a result (not null), output occurs.
     * 2. Each mode function is performed in modeManager. Creating the current screen is mode.
     */
    public void broadcast(long systemTime) {
        System.out.println("Time : " + systemTime);
        Message message;
        if ((message = eventScheduler.broadcast(systemTime)) != null) {
            if (message.getDestination() < 20)
                ioBridge.outputEvent(message);
            else {
                message = modeManager.showDefaultScreen(message);
                if (message != null)
                    ioBridge.outputEvent(message);
            }
        }
        if ((message = modeManager.broadcast(systemTime)) != null)
            ioBridge.outputEvent(message);

        testCode(systemTime);
    }

    /**
     * @param event The number of the button clicked.
     * @brief Button input event handling.
     * The event itself is passed to the modeManager.
     * Distribute the Message according to the destination.
     */
    public void inputEvent(int event) {
        Message message = this.modeManager.modeModify(event);
        if (message == null) return;
        if (message.getDestination() < 20)
            ioBridge.outputEvent(message);
        else if (message.getDestination() < 30) {
            switch (message.getDestination()) {
                case 20:
                    System.out.println(message.getAction());
                    break;
                case 21:
                    message = this.timeRunner.systemTimeUpdate(message);
                    this.eventScheduler.changeSystemTime(Long.parseLong(message.getArg().get("TimeDifference")));
                    this.ioBridge.outputEvent(message);
                    break;
                case 22:
                    this.eventScheduler.pushEvent(message);
                    break;
            }
        }
    }

    /**
     * @brief Resets the timer that returns to the default screen each time a button is pressed.
     */
    public void defaultScreenTimerReset() {
        this.eventScheduler.defaultScreenTimerReset();
    }

    /**
     * @brief Remove BuzzOff event from eventScheduler
     * If a button is input while the buzzer is ringing,
     * the buzzOffEvent is removed and the buzzer is immediately turned off.
     */
    public void stopBuzzer() {
        defaultScreenTimerReset();
        ioBridge.outputEvent(eventScheduler.removeBuzzerOffEvent());
    }

    private void testCode(long systemTime) {

        if (systemTime == 5000) {
            System.out.println("Test Start");
        }

        if (systemTime == 10000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "ringing");
            map.put("1", "8000");
            map.put("2", "321");
            this.eventScheduler.pushEvent(new Message(22, "updateAlarmEvent", map));
        }

        if (systemTime == 15000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "off");
            map.put("2", "321");
            this.eventScheduler.pushEvent(new Message(22, "updateAlarmEvent", map));
        }

        if (systemTime == 20000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "ringing");
            map.put("1", "4000");
            map.put("2", "322");
            this.eventScheduler.pushEvent(new Message(22, "updateAlarmEvent", map));
        }

        if (systemTime == 25000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "ringing");
            map.put("1", "7000");
            map.put("2", "323");
            this.eventScheduler.pushEvent(new Message(22, "updateAlarmEvent", map));
        }

        if (systemTime == 27000) {
            this.stopBuzzer();
        }

        if (systemTime == 30000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "31000");
            Message msg = this.timeRunner.systemTimeUpdate(new Message(21, "updateSystemTime", map));
            this.eventScheduler.changeSystemTime(Long.parseLong(msg.getArg().get("TimeDifference")));
            this.ioBridge.outputEvent(msg);
        }

        if (systemTime == 32000) {
            this.defaultScreenTimerReset();
        }

        if (systemTime == 35000) {
            HashMap<String, String> map = new HashMap<>();
            map.put("0", "9000");
            Message msg = this.timeRunner.systemTimeUpdate(new Message(21, "updateWorldTime", map));
            this.eventScheduler.changeSystemTime(Long.parseLong(msg.getArg().get("TimeDifference")));
            this.ioBridge.outputEvent(msg);
        }
    }
}