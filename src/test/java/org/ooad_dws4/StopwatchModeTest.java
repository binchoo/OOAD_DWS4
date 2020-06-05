package org.ooad_dws4;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StopwatchModeTest {

    @Test
    void getModeData() {
        StopwatchMode stopwatchMode= new StopwatchMode(true);
        stopwatchMode.modeModify(3);
        stopwatchMode.modeModify(3);
        Message returnMessage = stopwatchMode.getModeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "PUS");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void runStopwatch() {
        StopwatchMode stopwatchMode= new StopwatchMode(true);

        Message returnMessage =stopwatchMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "RUN");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void pauseStopwatch(){
        StopwatchMode stopwatchMode= new StopwatchMode(true);

        stopwatchMode.modeModify(3);
        Message returnMessage =stopwatchMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "PUS");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void resetStopwatch() {
        StopwatchMode stopwatchMode= new StopwatchMode(true);

        stopwatchMode.modeModify(3);
        Message returnMessage =stopwatchMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "PUS");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void resumeStopwatch() {
        StopwatchMode stopwatchMode= new StopwatchMode(true);

        stopwatchMode.modeModify(3);
        Message returnMessage =stopwatchMode.modeModify(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "PUS");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }

    @Test
    void update() {
        StopwatchMode stopwatchMode= new StopwatchMode(true);

        stopwatchMode.modeModify(3);
        Message returnMessage =stopwatchMode.update(1,true);
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", "RUN");
        arg.put("3", "00|0000");
        arg.put("4", " STOPWATCH");

        assertEquals(arg,returnMessage.getArg());
    }
}