package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeKeepingModeTest {
    @Test
    public void changeTimeTest() {
        TimeKeepingMode t = new TimeKeepingMode();
        Message returnMsg = t.modeModify(5);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "MON");
//        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", "09|0000");
        arg.put("4", "1971-01-11");
        arg.put("blink", "0");
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    public void changeFieldTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(2);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "MON");
//        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", "09|0000");
        arg.put("4", "1971-01-11");
        arg.put("blink", "1");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueIncreaseTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(4);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "TUE");
//        arg.put("1", null);
        arg.put("3", "09|0000");
        arg.put("4", "1972-01-11");
        arg.put("blink", "0");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueBoundTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "SUN");
//        arg.put("1", null);
        arg.put("3", "09|0000");
        arg.put("4", "1970-01-11");
        arg.put("blink", "0");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueDecreaseTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        t.modeModify(4); /* Increase year */
        t.modeModify(2); /* Change Field(to MONTH) */
        t.modeModify(3); /* Decrease Month */
        t.modeModify(2);
        t.modeModify(2);
        Message returnMsg = t.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "SAT");
//        arg.put("1", null);
        arg.put("3", "08|0000");
        arg.put("4", "1971-12-11");
        arg.put("blink", "3");
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeStatTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.changeState(1);
        assertEquals(t.state, 1);
    }

    @Test
    public void saveTimeTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        t.modeModify(2);
        t.modeModify(4);
        Message returnMsg = t.modeModify(5);
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "THU");
        arg.put("3", "09|0000");
        arg.put("4","1971-02-11");
        arg.put("newTime", "35078400000");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void updateCurrentTest() {
        TimeKeepingMode t = new TimeKeepingMode();
        Message returnMsg = t.update(10000, true);
        HashMap<String, String > arg = new HashMap<>();
        arg.put("0", "THU");
//        arg.put("1", null);
        arg.put("3", "09|0010");
        arg.put("4", "1970-01-01");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    void updateNoCurrentTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.update(20000, true);
        assertEquals(20000, t.getTimekeeping().getTimeData());
    }

    @Test
    void getModeDataTest() {
        TimeKeepingMode t = new TimeKeepingMode();
        Message returnMsg = t.getModeData();
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", "MON");
//        arg.put("1", null);
        arg.put("3", "09|0000");
        arg.put("4", "1971-01-11");
        arg.put("blink", null);
        assertEquals(arg, returnMsg.getArg());
    }
}