package org.ooad_dws4;

public class City {
    private boolean state;
    private int timeZone;

    public City(boolean state, int timeZone) {
        this.state = state;
        this.timeZone = timeZone;
    }

    public void changeState() {
        // TODO implement here
    }

    public int getTimeZoneData() {
        return this.timeZone;
    }

    public int getOffset(int tz){
        return (timeZone - tz);
    }

}