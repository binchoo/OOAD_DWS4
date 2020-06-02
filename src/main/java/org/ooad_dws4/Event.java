package org.ooad_dws4;
/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief Default unit for Event Scheduler
 */
public class Event {

    /**
     * @brief Where is this Message Come from?
     */
    private int id;

    /**
     * @brief When is this Message Occur?
     */
    private long deadline;

    /**
     *@brief What is this Message?
     */
    private Message message;

    /**
     * Default constructor
     */
    public Event(int id, Message message, long deadline) {
        this.id = id;
        this.message = message;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    /**
     * @param difference
     * @brief add difference to the deadline
     */
    public void differenceDeadline(long difference) {
        this.deadline += difference;
    }

    /**
     * @return Is the deadline over?
     */
    public boolean checkDeadline() {
        return this.deadline <= 0;
    }

    public String getEventType() {
        return this.message.getAction();
    }

    public Message getMessage() {
        return this.message;
    }

    public void decreaseDeadline() {
        this.deadline -= Clock.timeUnit;
    }

}