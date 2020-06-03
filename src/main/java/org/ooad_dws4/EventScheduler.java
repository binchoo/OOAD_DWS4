package org.ooad_dws4;

import java.util.*;

/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief Central Bride from DWS
 */
public class EventScheduler extends DWSObject {
    /**
     * @brief The objects that MainController can use
     */
    private MainController mainController;

    /**
     * @brief Event Queue for Event Scheduling.
     */
    private LinkedList<Event> eventQueue;
    /**
     * @brief Sub Queue for Management Alarm Event
     */
    private LinkedList<Event> alarmEventQueue;
    /**
     * @brief Reference for Drive Timeout Default Screen Event
     */
    private Event defaultScreenTimer;
    /**
     * @brief Reference for BuzzOff Event Overwrite
     */
    private Event buzzOffTimer;
    /**
     * @brief Milli second of a day.
     */
    private final long aDay = 86400000;

    /**
     * @brief Push SwitchDefaultScreen event in default constructor.
     */
    public EventScheduler() {
        this.alarmEventQueue = new LinkedList<Event>();
        this.eventQueue = new LinkedList<Event>();
        this.defaultScreenTimer = new Event(22, new Message(30, "SwitchDefaultScreen", null), Clock.defaultScreenTime);
        setEvent(this.defaultScreenTimer);
    }

    /**
     * @param mainController MainController Object   Object Number 20
     * @brief Make Reference Link with other Objects
     */
    public void linkObjects(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @param systemTime
     * @return If there is an event that reaches the deadline return Message, or Null
     * @brief decrease Deadline and check for deadline events.
     */
    public Message broadcast(long systemTime) {
        decreaseDeadline();
        assert this.eventQueue.peekFirst() != null;
        if (this.eventQueue.peekFirst().checkDeadline()) {
            if ("SwitchDefaultScreen".equals(this.eventQueue.peekFirst().getEventType())) {
                return getMessageAndReset();
            } else {
                Message message = getMessage();
                if ("buzzRinging".equals(message.getAction()))
                    makeBuzzOffEvent();
                else if ("buzzOff".equals(message.getAction()))
                    this.buzzOffTimer = null;
                return message;
            }
        }
        return null;
    }

    /**
     * @brief Change alarm time according to new system time
     */
    public void changeSystemTime(long difference) {
        for (Event event : this.alarmEventQueue) {
            event.differenceDeadline(difference);
            if (event.getDeadline() < 0)
                event.differenceDeadline(this.aDay);
        }
        sortEventQueue();
    }

    /**
     * @return updateView : LCD 0 " ON" or "RUN"
     * @brief Pass message from mainController
     */
    public Message pushEvent(Message message) {
        String action = message.getAction();
        if (!("updateTimerEvent".equals(action) || "updateAlarmEvent".equals(action)))
            return null;
        if ("ringing".equals(message.getArg().get("0"))) {
            if (!setEvent(makeBuzzEvent(message), "updateAlarmEvent".equals(action)))
                return null;
            String lcd0 = message.getArg().get("2").equals("351") ? "RUN" : " ON";
            HashMap<String, String> map = new HashMap<>();
            map.put("0", lcd0);
            return new Message(11, "updateView", map);
        } else
            removeEvent(Integer.parseInt(message.getArg().get("2")));
        return null;
    }

    /**
     * @brief Clear Alarm Event from eventQueue
     */
    public void removeAlarmAll() {
        System.out.println("Remove Alarm All");
        for (Event event : this.alarmEventQueue)
            this.eventQueue.remove(event);
        this.alarmEventQueue.clear();
        sortEventQueue();
    }

    /**
     * @brief Reset SwitchDefaultScreen Event deadline
     */
    public void defaultScreenTimerReset() {
        this.defaultScreenTimer.setDeadline(Clock.defaultScreenTime);
        sortEventQueue();
    }

    /**
     * @brief Sort eventQueue by deadline
     */
    private void sortEventQueue() {
        Collections.sort(eventQueue, (event1, event2) -> {
            return (int) (event1.getDeadline() - event2.getDeadline());
        });
    }

    /**
     * @brief Decrease Deadline each unit time
     */
    private void decreaseDeadline() {
        System.out.print("Event Queue - ");
        for (Event event : this.eventQueue) {
            event.decreaseDeadline();
            System.out.print("[" + event.getId() + ":" + event.getDeadline() + ":" + event.getEventType() + "] - ");
        }
        System.out.print("END\n");
    }

    /**
     * @brief Remove Event from eventQueue
     */
    private boolean removeEvent(int target) {
        Event event = findEvent(target);
        if (event == null) return false;
        this.eventQueue.remove(event);
        return true;
    }

    /**
     * @brief Find Event from eventQueue
     */
    private Event findEvent(int target) {
        for (Event event : this.eventQueue)
            if (event.getId() == target)
                return event;
        return null;
    }

    /**
     * @brief Add new Event to eventQueue
     */
    private boolean setEvent(Event event) {
        if (event == null) return false;
        this.eventQueue.add(event);
        sortEventQueue();
        return true;
    }

    /**
     * @brief if the event is from alarm, add to alarmEventQueue either.
     */
    private boolean setEvent(Event event, boolean isAlarm) {
        if (!setEvent(event)) return false;
        if (isAlarm)
            this.alarmEventQueue.add(event);
        return true;
    }

    /**
     * @brief Generates a ringing event when an alarm or timer message is received.
     */
    private Event makeBuzzEvent(Message message) {
        Message buzzRing = new Message(11, "buzzRinging", null);
        HashMap<String, String> arg = message.getArg();
        try {
            return new Event(Integer.parseInt(arg.get("2")), buzzRing, Long.parseLong(arg.get("1")));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * @brief When the BuzzRinging event occurs, a BuzzOff event is created and push in the eventQueue.
     */
    private void makeBuzzOffEvent() {
        if (this.buzzOffTimer == null) {
            Message buzzRing = new Message(11, "buzzOff", null);
            this.buzzOffTimer = new Event(-1, buzzRing, Clock.buzzerOffTime);
            setEvent(this.buzzOffTimer);
        } else
            this.buzzOffTimer.setDeadline(Clock.buzzerOffTime);
        sortEventQueue();
    }

    /**
     * @return SwitchDefaultScreen Message
     * @brief Only use SwitchDefaultScreen Event
     */
    private Message getMessageAndReset() {
        Message message = this.defaultScreenTimer.getMessage();
        defaultScreenTimerReset();
        return message;
    }

    /**
     * @return Universal Message especially Buzzer Event
     * @brief Remove Event from Queue and return the Message
     */
    private Message getMessage() {
        return this.eventQueue.remove().getMessage();
    }

    /**
     * @return BuzzOff Message or null(No Ringing)
     * @brief Turn off the buzzer and remove buzzOff Event
     */
    public Message removeBuzzerOffEvent() {
        if (this.eventQueue.remove(this.buzzOffTimer)) {
            Message buzzerOffMessage = this.buzzOffTimer.getMessage();
            this.buzzOffTimer = null;
            sortEventQueue();
            return buzzerOffMessage;
        } else
            return null;
    }


}