package org.ooad_dws4;

public class TimeKeeping {
    private long time;
    public TimeKeeping(long systemTime) {
        this.time = systemTime;
    }

    public void changeState() {
        // TODO implement here
    }

    /* finished */
    public long getTimeData() {
        return time;
    }
    public void setTimeData(long systemTime){
        this.time = systemTime >= 0 ? systemTime : 0;
    }
    public long changeValue(int value) {
        time += value;
        return time;
    }

}