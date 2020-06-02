package org.ooad_dws4;

public class Alarm {
    private boolean state;
    private long time;

    public Alarm() {
        this.state = false;
        this.time = 0;
    }

    public void changeState() {
        // TODO implement here
    }
    public boolean toggleAlarmActivation(){
        state = !state;
        return state;
    }
    public long getAlarmData() {
        return time;
    }

    public boolean toggleActivation() {
        this.state = !this.state;
        return this.state;
    }

    public void changeValue() {
        // TODO implement here
    }

    public boolean getState(){
        return this.state;
    }
}