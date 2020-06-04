package org.ooad_dws4;

/**
 *
 */
public class Stopwatch {

    private long stopwatchTime;
    private long maxValue;

    public Stopwatch(long maxValue) {
        this.stopwatchTime = 0L;
        this.maxValue = maxValue;
    }


    public void changeState() {
    }


    public long getStopwatchData() {
        return stopwatchTime;
    }

    public void runStopwatch() {
        stopwatchTime = stopwatchTime + Clock.timeUnit;
        stopwatchTime = Math.min(stopwatchTime, maxValue);
    }

    public void setStopwatchData(long time) {
        this.stopwatchTime = time;
    }


    public void reset() {
        // TODO implement here
        stopwatchTime = 0;
    }

}