package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TimerModeTest {

    @Test
    void makeView() {
        TimerMode timerMode = new TimerMode(true);
//        timerMode.makeView().doMessageAction();
    }

    @Test
    void changeField() {

        TimerMode timerMode= new TimerMode(true);
        timerMode.modeModify(5);
        Message returnMessage = timerMode.modeModify(2);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("blink","4");

        assertEquals(returnMessage.getArg(),arg);

    }

    @Test
    void startTimer() {

        TimerMode timerMode= new TimerMode(true);
        Message returnMessage =timerMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "ringing");
        arg.put("1", "300000");
        arg.put("2", "351");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void pauseTimer() {

        TimerMode timerMode= new TimerMode(true);
        timerMode.modeModify(3);
        Message returnMessage =timerMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "off");
        arg.put("2", "351");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void changeTimerTime() {

        TimerMode timerMode= new TimerMode(true);
        Message returnMessage =timerMode.modeModify(5);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "EDT");
        arg.put("3", "00|0000");
        arg.put("4","  TIMER   ");
        arg.put("blink","3");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void saveTimer() {

        TimerMode timerMode= new TimerMode(true);

        timerMode.modeModify(5);
        Message returnMessage =timerMode.modeModify(5);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "OFF");
        arg.put("3", "00|0500");
        arg.put("4","  TIMER   ");
        arg.put("blink",null);

        assertEquals(arg,returnMessage.getArg());
    }
    @Test
    void resetTimer() {

        TimerMode timerMode= new TimerMode(true);

        timerMode.modeModify(3);
        timerMode.modeModify(3);
        Message returnMessage =timerMode.modeModify(2);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "OFF");
        arg.put("3", "00|0000");
        arg.put("4","  TIMER   ");


        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void resumeTimer() {

        TimerMode timerMode= new TimerMode(true);

        timerMode.modeModify(3);
        timerMode.modeModify(3);
        Message returnMessage =timerMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "ringing");
        arg.put("1", "300000");
        arg.put("2", "351");

        assertEquals(arg,returnMessage.getArg());
    }


}