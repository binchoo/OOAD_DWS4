package org.ooad_dws4;

/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief Owner of information about the time on the DWS
 */
public class TimeRunner extends DWSObject implements PulseMaker {

    /**
     * @breif Default time of this system. msec
     */
    private long systemTime;

    /**
     * @breif Time zone. msec
     */
    private long timeZone;

    /**
     * @brief The objects that TimeRunner can use.
     */
    private MainController mainController;

    /**
     * Default constructor
     */
    public TimeRunner() {
        systemTime = System.currentTimeMillis();
        timeZone = +32400000;
    }

    /**
     * @param mainController MainController Object.  Object Number 20
     * @brief Make Reference Link with other Objects
     */
    public void linkObject(MainController mainController) {
        this.mainController = mainController;
    }

    public long getSystemTime() {
        return this.systemTime;
    }

    /**
     * @param message Time change type and time value to change
     * @return Returns by adding the time difference to the received message
     * @brief Receive systemTimeChange Event.
     */
    public Message systemTimeUpdate(Message message) {
        long timeArg = Long.parseLong(message.getArg().get("newTime"));
        message.getArg().remove("newTime");
        long timeDifference = 0;
        if ("updateSystemTime".equals(message.getAction()))
            timeDifference = changeSystemTime(timeArg);
        else if ("updateWorldTime".equals(message.getAction()))
            timeDifference = changeTimeZone(timeArg);
        message.getArg().put("TimeDifference", String.valueOf(timeDifference));
        return new Message(11, "updateView", message.getArg());
    }

    /**
     * @param newTimeZone difference of previous time zone and current time zone
     * @return negative value of difference
     * @brief if the type of time change is updateWorldTime,
     */
    private long changeTimeZone(long newTimeZone) {
        this.timeZone = newTimeZone;
        this.systemTime += newTimeZone;
        return -newTimeZone;
    }

    /**
     * @param newTime new time value to overwrite
     * @return difference of previous time and current time
     * @brief if the type of time change is updateSystemTime,
     */
    private long changeSystemTime(long newTime) {
        long difference = this.systemTime - newTime;
        this.systemTime = newTime;
        return difference;
    }

    /**
     * @param timeUnit Pulse Interval
     * @brief Sends a signal with the system time to the entire system every unit time
     */
    @Override
    public void makePulse(long timeUnit) {
        systemTime += timeUnit;
        mainController.broadcast(systemTime);
    }
}