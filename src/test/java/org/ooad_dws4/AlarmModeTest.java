package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AlarmModeTest {

    @Test
    void changeAlarmIndexTest() {
        AlarmMode a = new AlarmMode();
        a.changeAlarmIndex(1);
        a.changeAlarmIndex(1);
        a.changeAlarmIndex(1);
        assertEquals(a.getCurrentAlarmIndex(), 3);
    }

    @Test
    void editAlarmTest() {
    }

    @Test
    void saveAlarmTest() {
    }

    @Test
    void toggleAlarmActivationTest() {
    }

    @Test
    void changeFieldTest() {
    }

    @Test
    void changeValueTest() {
    }

    @Test
    void getModeDataTest() {
        AlarmMode a = new AlarmMode();
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "OFF");
        arg.put("1", null);
        arg.put("3", "0");
        arg.put("4", "0");
        arg.put("blink", null);
        Message m = new Message(11, "updateView", arg);
        assertEquals(a.getModeData().getArg(), arg);
    }

    @Test
    void toggleModeActivationTest() {
        AlarmMode a = new AlarmMode();
        a.isActivate = false;
        a.toggleModeActivation();
        assertTrue(a.isActivate);
    }

    @Test
    void modeModifyTest() {
    }

    @Test
    void changeStateTest() {
        AlarmMode a = new AlarmMode();
        a.state = 1;
        a.changeState(1);
        assertEquals(a.state, 0);
    }
}