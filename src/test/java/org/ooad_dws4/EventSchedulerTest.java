package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EventSchedulerTest {

    @Test
    void broadcast() {
        Clock.timeUnit = 1000;
        Clock.defaultScreenTime = 600000;
        Clock.buzzerOffTime = 7000;

        EventScheduler e = new EventScheduler();
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "ringing");
        map.put("1", "0");
        map.put("2", "351");
        Message m = new Message(22, "updateAlarmEvent", map);
        Message msg = e.pushEvent(m);
        assertEquals("RUN", msg.getArg().get("0"));
        msg = e.broadcast(10000);
        msg.doMessageAction();
        assertEquals("buzzRinging", msg.getAction());
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
    void removeBuzzerOffEvent() {
        Clock.timeUnit = 1000;
        Clock.defaultScreenTime = 600000;
        Clock.buzzerOffTime = 7000;

        EventScheduler e = new EventScheduler();
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "ringing");
        map.put("1", "0");
        map.put("2", "351");
        Message m = new Message(22, "updateAlarmEvent", map);
        Message msg = e.pushEvent(m);
        assertEquals("RUN", msg.getArg().get("0"));
        msg = e.broadcast(10000);
        msg.doMessageAction();
        assertEquals("buzzRinging", msg.getAction());
        msg = e.removeBuzzerOffEvent();
        assertEquals("buzzOff", msg.getAction());
    }
}