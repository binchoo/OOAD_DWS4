package org.ooad_dws4;

import java.util.HashMap;

public class StopwatchMode extends Mode {

    private Stopwatch stopwatch;
    // private HashMap <String, String> arg;
    private int hour, minute, second;
    private long maxValue = 359999000L;

    public StopwatchMode(boolean isActivation) {
        this.state = 0;
        this.stopwatch = new Stopwatch(maxValue);
        this.isActivate = isActivation;
        this.modeName = "STOPWATCH";
    }

    @Override
    public Message getModeData() {
        msecTohhmmss(stopwatch.getStopwatchData());
        return makeView();
    }


    private Message runStopwatch() {
        changeState("RUN");
        msecTohhmmss(stopwatch.getStopwatchData());
        return makeView();
    }

    /**
     *
     */
    private Message pauseStopwatch() {
        changeState("PAUSE");
        msecTohhmmss(stopwatch.getStopwatchData());
        return makeView();
    }

    /**
     *
     */
    private Message resetStopwatch() {
        changeState("DEFAULT");
        stopwatch.reset();
        msecTohhmmss(stopwatch.getStopwatchData());
        return makeView();
    }

    /**
     *
     */
    private Message resumeStopwatch() {
        return runStopwatch();
    }

    @Override
    public Message update(long systemTime) {
        if (state == 2)
            this.stopwatch.runStopwatch();
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        if (currentMode)
            update(systemTime);
        msecTohhmmss(stopwatch.getStopwatchData());
        return makeView();
    }

    @Override
    public Message modeModify(int event) {
        if (this.state == 0) {
            if (event == 3)
                return runStopwatch();
        } else if (this.state == 2) { // when running
            if (event == 3)
                return pauseStopwatch();
        } else if (this.state == 3) { // when pause
            if (event == 2)
                return resetStopwatch();
            else if (event == 3)
                return resumeStopwatch();
        }
        return null;
    }

    private void msecTohhmmss(long timerTime) {
        this.second = (int) (timerTime / 1000) % 60;
        this.minute = (int) ((timerTime) / (1000 * 60) % 60);
        this.hour = (int) ((timerTime) / (1000 * 60 * 60));
    }


    private void changeState(String state) {
        if ("DEFAULT".equals(state))
            this.state = 0;
        else if ("RUN".equals(state))
            this.state = 2;
        else if ("PAUSE".equals(state))
            this.state = 3;
    }

    private Message makeView() {
        HashMap<String, String> arg = new HashMap<>();
        String state;
        if (this.state == 2)
            state = "RUN";
        else if (this.state == 3)
            state = "PUS";
        else
            state = "OFF";
        arg.put("0", state);
        arg.put("3", makeTimeForm(this.hour, this.minute, this.second));
        arg.put("4", " STOPWATCH");
        return new Message(11, "updateView", arg);
    }

    private String makeTimeForm(int hour, int minute, int second) {
        char result[] = "00|0000".toCharArray();
        char hourChar[] = Integer.toString(hour).toCharArray();
        char minChar[] = Integer.toString(minute).toCharArray();
        char secChar[] = Integer.toString(second).toCharArray();
        for (int i = 0; i < hourChar.length; i++)
            result[2 - hourChar.length + i] = hourChar[i];
        for (int i = 0; i < minChar.length; i++)
            result[5 - minChar.length + i] = minChar[i];
        for (int i = 0; i < secChar.length; i++)
            result[7 - secChar.length + i] = secChar[i];
        return new String(result);
    }
}