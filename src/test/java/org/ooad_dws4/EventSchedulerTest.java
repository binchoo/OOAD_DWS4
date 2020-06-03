package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EventSchedulerTest {

    @Test
    void broadcast() {
    }

    @Test
    void changeSystemTime() {
    }

    @Test
    void pushEvent() {
        EventScheduler e = new EventScheduler();
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "ringing");
        map.put("1", "4201000");
        map.put("2", "351");
        Message m = new Message(22, "updateAlarmEvent", map);
        Message msg = e.pushEvent(m);
        if(msg != null)
            msg.doMessageAction();
    }

    @Test
    void removeAlarmAll() {
    }

    @Test
    void defaultScreenTimerReset() {
    }

    @Test
    void removeBuzzerOffEvent() {
    }
}