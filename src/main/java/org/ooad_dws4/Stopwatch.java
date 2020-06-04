package org.ooad_dws4;

/**
 * 
 */
public class Stopwatch {

    private long stopWatchTime;

    public Stopwatch() {
    }


    private long stopwatchTime;



    public void changeState() {
        // TODO implement here
    }


    public long getStopwatchData() {
        // TODO implement here
        return stopWatchTime;
    }

    public void setStopwatchData(long time){

        this.stopwatchTime =time;
    }


    public void reset() {
        // TODO implement here

        stopWatchTime=0;
    }

}