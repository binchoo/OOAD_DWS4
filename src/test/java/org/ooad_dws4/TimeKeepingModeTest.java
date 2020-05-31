package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeKeepingModeTest {
    @Test
    public void toggleModeActivationTest(){
        TimeKeepingMode t = new TimeKeepingMode(5);
        t.toggleModeActivation();
        assertTrue(t.isActivate);
    }
    @Test
    public void changeStatTest(){
        TimeKeepingMode t = new TimeKeepingMode(5);
        t.changeState(1);
        assertEquals(t.state, 1);
    }
    @Test
    public void modeModifyDefaultStateDestTest(){
        TimeKeepingMode t = new TimeKeepingMode(5);
        Message returnMsg = t.modeModify(5);
        assertEquals(returnMsg.getDestination(), 11);
    }
    @Test
    public void modeModifyDefaultStateActionTest(){
        TimeKeepingMode t = new TimeKeepingMode(5);
        Message returnMsg = t.modeModify(5);
        assertEquals(returnMsg.getAction(), "updateView");
    }
    @Test
    public void modeModifyDefaultStateArgTest(){
        TimeKeepingMode t = new TimeKeepingMode(5);
        Message returnMsg = t.modeModify(5);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "5");
        arg.put("1", null);
        arg.put("3", "5");
        arg.put("4", "5");
        arg.put("blink", null);
        assertEquals(returnMsg.getArg(), arg);
    }
    @Test
    public void modeModifyEditStateTest(){
        TimeKeepingMode t = new TimeKeepingMode(4);
        t.state = 1;
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "4");
        arg.put("1", null);
        arg.put("3", "4");
        arg.put("4", "4");
        arg.put("blink", null);
        Message returnMsg = t.modeModify(5);
        assertEquals(returnMsg.getArg(), arg);
    }

    @Test
    public void modeModifychangeFieldTest(){
        TimeKeepingMode t = new TimeKeepingMode(4);
        t.state = 1;
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "4");
        arg.put("1", null);
        arg.put("3", "4");
        arg.put("4", "4");
        arg.put("blink", "0");
        Message returnMsg = t.modeModify(2);
        assertEquals(returnMsg.getArg(), arg);
    }
    @Test
    public void getModeDataTest(){
        TimeKeepingMode t = new TimeKeepingMode(4);
        HashMap<String, String> arg = new HashMap<String, String>();
        Message returnMsg = t.getModeData();
        arg.put("0", "4");
        arg.put("1", null);
        arg.put("3", "4");
        arg.put("4", "4");
        arg.put("blink", null);
        assertEquals(returnMsg.getArg(), arg);
    }
}