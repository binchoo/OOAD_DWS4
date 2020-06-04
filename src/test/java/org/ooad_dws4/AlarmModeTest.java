package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AlarmModeTest {

    @Test
    void changeAlarmIndexIncreaseTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String > arg = new HashMap<>();
        a.modeModify(3);
        a.modeModify(3);
        Message returnMsg = a.modeModify(3);
        arg.put("0", "OFF");
//        arg.put("1", null);
        arg.put("3", "00|00 2");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void changeAlarmIndexDecreaseTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String > arg = new HashMap<>();
        a.modeModify(4);
        a.modeModify(4);
        Message returnMsg = a.modeModify(4);
        arg.put("0", "OFF");
//        arg.put("1", null);
        arg.put("3", "00|00 4");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void editAlarmTest() {
        AlarmMode a = new AlarmMode(true);
        Message returnMsg = a.modeModify(5);
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0","EDT");
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", "3");
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void saveAlarmTest() {
        AlarmMode a = new AlarmMode(true);
        a.modeModify(5);
        HashMap<String, String> arg = new HashMap<>();
        Message returnMsg = a.modeModify(5);
        arg.put("0", "OFF");
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void alarmOnTest() {
        AlarmMode a = new AlarmMode(true);
        Message returnMsg = a.modeModify(2);
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "ringing");
        arg.put("1", Long.toString(a.getAlarms()[0].getAlarmData()));
        arg.put("2", "321");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void alarmOffTest(){
        AlarmMode a = new AlarmMode(true);
        a.modeModify(2);
        Message returnMsg = a.modeModify(2);
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "off");
        arg.put("2", "321");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void ONStateTest(){
        AlarmMode a = new AlarmMode(true);
        a.modeModify(2);
        Message returnMsg = a.getModeData();
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", " ON");
//        arg.put("1", null);
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void OFFStateTest(){
        AlarmMode a = new AlarmMode(true);
        a.modeModify(2);
        a.modeModify(2);
        Message returnMsg = a.getModeData();
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "OFF");
//        arg.put("1", null);
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void changeFieldTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String> arg = new HashMap<>();
        a.modeModify(5);
        Message returnMsg = a.modeModify(2);
        arg.put("0", "EDT");
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", "4");
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void changeValueIncreaseTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String> arg = new HashMap<String, String>();
        a.modeModify(5);
        a.modeModify(2);
        Message returnMsg = a.modeModify(4);
        arg.put("0", "EDT");
        arg.put("3", "00|01 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        Message m = new Message(11, "updateView", arg);
        assertEquals(arg, a.getModeData().getArg());
    }
    @Test
    void changeValueDecreaseTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String> arg = new HashMap<String, String>();
        a.modeModify(5);
        a.modeModify(2);
        Message returnMsg = a.modeModify(3);
        arg.put("0", "EDT");
        arg.put("3", "23|59 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        Message m = new Message(11, "updateView", arg);
        assertEquals(arg, a.getModeData().getArg());
    }
    @Test
    void getModeDataTest() {
        AlarmMode a = new AlarmMode(true);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "OFF");
//        arg.put("1", null);
        arg.put("3", "00|00 1");
        arg.put("4", "  ALARM   ");
        arg.put("blink", null);
        Message m = new Message(11, "updateView", arg);
        assertEquals(arg, a.getModeData().getArg());
    }

    /*@Test
    void toggleModeActivationTest() {
        AlarmMode a = new AlarmMode(true);
        a.isActivate = false;
        a.toggleModeActivation();
        assertTrue(a.isActivate);
    }*/


    @Test
    void changeStateTest() {
        AlarmMode a = new AlarmMode(true);

        assertEquals(0, a.state);
    }
}