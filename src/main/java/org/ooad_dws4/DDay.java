package org.ooad_dws4;

import java.util.Date;

public class DDay {

    private boolean state;
    private long time; // millisecond time

    public DDay(long time) {
        this.time = time;
        this.state = false;
    }

    public void changeState() {
        this.state = !this.state;
    }

    public boolean getState() {
        return this.state;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}