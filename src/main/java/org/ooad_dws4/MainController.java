package org.ooad_dws4;

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
//    int repeat2=0, repeat4 = 0; // for UI test

    /**
     * @brief Default constructor
     */
    public MainController() {
    }

    /**
     * @param ioBridge       IOBridge Object. Object Number : 10
     * @param timeRunner     TimeRunner Object. Object Number : 21
     * @param eventScheduler EventScheduler Object. Object Number : 22
     * @param modeManager    ModeManager Object. Object Number : 30
     * @brief Make Reference Link with other Objects
     */
    public void linkObjects(IOBridge ioBridge, TimeRunner timeRunner, EventScheduler eventScheduler,
                            ModeManager modeManager) {
        this.ioBridge = ioBridge;
        this.timeRunner = timeRunner;
        this.eventScheduler = eventScheduler;
        this.modeManager = modeManager;
    }

    /**
     * @param systemTime Pass the current system time throughout the system.
     * @brief The pulse started by TimeRunner is delivered to each part. 1. Deadline
     * check in eventScheduler. If there is a result (not null), output
     * occurs. 2. Each mode function is performed in modeManager. Creating
     * the current screen is mode.
     */
    public void broadcast(long systemTime) {
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
    }

    /**
     * @param event The number of the button clicked.
     * @brief Button input event handling. The event itself is passed to the
     * modeManager. Distribute the Message according to the destination.
     */
    public void inputEvent(int event) {
        if (!(0 < event && event < 9))
            return;
        defaultScreenTimerReset();
        Message message = this.modeManager.modeModify(event);
        if (message == null)
            return;
        if (message.getDestination() < 20) {
            if (message.getArg() != null)
                if (message.getArg().containsKey("Action")) {
                    if (message.getArg().containsValue("removeAlarmAll"))
                        this.eventScheduler.removeAlarmAll();
                    message.getArg().remove("Action");
                }
            ioBridge.outputEvent(message);
        } else if (message.getDestination() < 30) {
            switch (message.getDestination()) {
                case 20:
                    break;
                case 21:
                    message = this.timeRunner.systemTimeUpdate(message);
                    if (message != null) {
                        this.eventScheduler.changeSystemTime(Long.parseLong(message.getArg().get("TimeDifference")));
                        message.getArg().remove("TimeDifference");
                        this.ioBridge.outputEvent(message);
                    }
                    break;
                case 22:
                    message = this.eventScheduler.pushEvent(message);
                    if (message != null)
                        this.ioBridge.outputEvent(message);
                    break;
            }
        }
    }

    /**
     * @brief Resets the timer that returns to the default screen each time a button
     * is pressed.
     */
    private void defaultScreenTimerReset() {
        this.eventScheduler.defaultScreenTimerReset();
    }

    /**
     * @brief Remove BuzzOff event from eventScheduler If a button is input while
     * the buzzer is ringing, the buzzOffEvent is removed and the buzzer is
     * immediately turned off.
     */
    public void stopBuzzer() {
        defaultScreenTimerReset();
        ioBridge.outputEvent(eventScheduler.removeBuzzerOffEvent());
    }
}