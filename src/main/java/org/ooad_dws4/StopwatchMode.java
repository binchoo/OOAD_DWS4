package org.ooad_dws4;

import java.util.HashMap;

public class StopwatchMode extends Mode {

    private Stopwatch stopwatch;
    // private HashMap <String, String> arg;
    private int hour, minute, second;

    public StopwatchMode(boolean isActivation) {
        this.state = 0;
        this.stopwatch = new Stopwatch();
        this.isActivate = isActivation;
        this.modeName = "STOPWATCH";
    }

    @Override
    public Message getModeData() {
        HashMap<String, String> arg = new HashMap<String, String>();
        long stopwatchtime = stopwatch.getStopwatchData();
        msecTohhmmss(stopwatchtime);
        makeUpdateViewArg(arg, "   ", null,
                Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH", null);
        return new Message(11, "updateView", arg);
    }

    // @Override
    public boolean receiveMessage(Message msg) {
        return false;
    }

    public Message runStopwatch() {
        // TODO implement here

        changeState(2);

        stopwatch.getStopwatchData();

        HashMap<String, String> arg = new HashMap<String, String>();
        long stopwatchtime = stopwatch.getStopwatchData();
        msecTohhmmss(stopwatchtime);
        makeUpdateViewArg(arg, "RUN", null,
                Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH", null);

        return new Message(11, "updateView", arg);

    }

    /**
     *
     */
    public Message pauseStopwatch() {

        // change state pause
        changeState(3);
        HashMap<String, String> arg = new HashMap<String, String>();
        long stopwatchtime = stopwatch.getStopwatchData();
        msecTohhmmss(stopwatchtime);
        makeUpdateViewArg(arg, "PUS", null,
                Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH", null);
        return new Message(11, "updateView", arg);
    }

    /**
     *
     */
    public Message resetStopwatch() {

        stopwatch.reset();
        changeState(0);
        HashMap<String, String> arg = new HashMap<String, String>();
        long stopwatchtime = stopwatch.getStopwatchData();
        msecTohhmmss(stopwatchtime);
        makeUpdateViewArg(arg, "   ", null,
                Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH", null);

        return new Message(11, "updateView", arg);
    }

    /**
     *
     */
    public Message resumeStopwatch() {

        // change state running
        changeState(2);
        HashMap<String, String> arg = new HashMap<String, String>();

        long stopwatchtime = stopwatch.getStopwatchData();
        msecTohhmmss(stopwatchtime);
        makeUpdateViewArg(arg, "RUN", null,
                Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH", null);

        return new Message(11, "updateView", arg);
    }

    public void changeState(int state) {
        this.state = state;
    }

    @Override
    public Message update(long systemTime) {
        if (state == 2) {
            stopwatch.setStopwatchData(stopwatch.getStopwatchData() + 1000);

        }
        HashMap<String, String> arg = new HashMap<String, String>();

        return null;

    }

    @Override
    public Message update(long systemTime, boolean currentMode) {

        HashMap<String, String> arg = new HashMap<String, String>();

        if (state == 2) {
            stopwatch.setStopwatchData(stopwatch.getStopwatchData() + 1000);
            long stopwatchtime = stopwatch.getStopwatchData();
            msecTohhmmss(stopwatchtime);
            makeUpdateViewArg(arg, "   ", null,
                    Integer.toString(hour) + "|" + Integer.toString(minute) + Integer.toString(second), "STOPWATCH",
                    null);
        }

        return new Message(11, "updateView", arg);
    }


    public Message modeModify(int event) {
        // TODO implement here
        HashMap<String, String> arg = new HashMap<String, String>();

        if (this.state == 0) {
            switch (event) {
                // case 1:
                // change mode
                case 3:
                    return runStopwatch();
                default:
                    break;

            }

        } else if (this.state == 2) { // when running
            switch (event) {

                case 3:
                    return pauseStopwatch();
                default:
                    break;
            }
        } else if (this.state == 3) { // when pause
            switch (event) {

                case 2:
                    return resetStopwatch();
                case 3:
                    return resumeStopwatch();
                default:
                    break;
            }

        } else
            return null;
        return null;
    }

    private void makeUpdateViewArg(HashMap<String, String> arg, String ar0, String ar1, String ar3, String ar4,
            String blink) { // f

        arg.put("0", ar0);
        arg.put("1", null); // should be added in mode manager
        arg.put("3", ar3);
        arg.put("4", ar4);
        arg.put("blink", blink);
    }

    public void msecTohhmmss(long timerTime) {
        this.second = (int) (timerTime / 1000) % 60;
        this.minute = (int) ((timerTime) / (1000 * 60) % 60);
        this.hour = (int) ((timerTime) / (1000 * 60 * 60) % 24);

    }

}