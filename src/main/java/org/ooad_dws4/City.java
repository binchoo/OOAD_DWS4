package org.ooad_dws4;

public class City {
    private boolean state;
    private int timeZone;
    private String name;

    public City(boolean state, int timeZone, String name) {
        this.state = state;
        this.timeZone = timeZone;
        this.name = name;
    }

    public void changeState(boolean state) {
        this.state = state;
    }
    public String getName(){
        return name;
    }
    public int getTimeZoneData() {
        return this.timeZone;
    }

    public int getOffset(int currentTz){
        return (timeZone - currentTz);
    }

}