package org.ooad_dws4;

import java.util.Date;

public class DDay {

    private boolean state;
    private Date date;
    private long mTime; // millisecond time

    public DDay() {
        mTime = 1563032234338L;
    }

    public void changeState() {
        // TODO implement here
        if (state){state=false;}
        else{state= true;}
    }

    public long getDdayData() {
        // TODO implement here
        return 0;
    }

    public long getmTime() {
        return mTime;
    }
}