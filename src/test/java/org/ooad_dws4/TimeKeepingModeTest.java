package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeKeepingModeTest {
    @Test
    public void changeTimeTest() {
        TimeKeepingMode t = new TimeKeepingMode();
        Message returnMsg = t.modeModify(5);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, 0, Integer.toString(0));
        assertEquals(arg, returnMsg.getArg());
    }

    @Test
    public void changeFieldTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(2);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, 0, Integer.toString(1));
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueIncreaseTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(4);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateSystemTimeArg(arg, 5, Integer.toString(6));
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueBoundTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateSystemTimeArg(arg, 5, Integer.toString(0));
        assertEquals(arg, returnMsg.getArg());
    }
    @Test
    public void changeValueDecreaseTest(){
        TimeKeepingMode t = new TimeKeepingMode();
        t.modeModify(5);
        Message returnMsg = t.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateSystemTimeArg(arg, 5, Integer.toString(4));
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
        t.modeModify(4);
        t.modeModify(2);
        Message returnMsg = t.modeModify(5);

    }
    @Test
    void updateTest() {
    }

    @Test
    void getModeDataTest() {
    }

    @Test
    void toggleModeActivationTest() {
    }

    private void makeUpdateSystemTimeArg(HashMap<String, String> arg, long systemTime, String blink){

    }
    private void makeUpdateViewArg(HashMap<String, String> arg, long systemTime, String blink){ //f
        if(systemTime == -1)
        {
            arg.put("0", "");
            arg.put("1", ""); /* should be added in mode manager */
            arg.put("3", "");
            arg.put("4", "");
            arg.put("blink", blink);
            return;
        }
        arg.put("0", Long.toString(systemTime));
        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", Long.toString(systemTime));
        arg.put("4", Long.toString(systemTime));
        arg.put("blink", blink);
    }
}