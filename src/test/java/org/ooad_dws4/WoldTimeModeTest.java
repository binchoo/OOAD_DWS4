package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WoldTimeModeTest {

    @Test
    void getModeDataTest() {
    }

    @Test
    void changeCityTest() {
        WoldTimeMode w = new WoldTimeMode(true);
        Message returnMsg = w.modeModify(4);
        HashMap<String , String> arg = new HashMap<>();
        arg.put("0", "PAR");
//        arg.put("1", null);
        arg.put("3", "02|00  ");
        arg.put("4", "WORLD TIME");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void setCityTest(){
        WoldTimeMode w = new WoldTimeMode(true);
        w.modeModify(4);
        w.modeModify(2);
        w.modeModify(4);
        Message returnMsg = w.modeModify(2);
        HashMap<String, String > arg = new HashMap<>();
        arg.put("0", Long.toString(-1 * 1000 * 60 * 60));
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void systemTimeTest(){
        WoldTimeMode w = new WoldTimeMode(true);
        w.modeModify(4);
        w.modeModify(2);
        w.modeModify(4);
        w.modeModify(2);
        w.modeModify(4);
        w.modeModify(2);
        assertEquals(-46800000L, w.getSystemTime());
    }
    @Test
    void updateCurrentTest() {
        WoldTimeMode w = new WoldTimeMode(true);
        Message returnMsg = w.update(1000 * 60 * 3, true);
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "SEL");
//        arg.put("1", null);
        arg.put("3", "09|03TZ");
        arg.put("4", "WORLD TIME");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    void updateNoCurrentTest() {
    }
}