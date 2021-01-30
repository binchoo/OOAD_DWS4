package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Mode {
    protected boolean isActivate;
    protected int state;
    protected String modeName;
    protected Calendar calendar;

    public Mode() {
        calendar = Calendar.getInstance();
        state = 0;
    }
    // public abstract Message toggleModeActivation();

    public int getState() {
        return this.state;
    }

    public boolean getIsActivate() {
        return this.isActivate;
    }

    public String getModeName() {
        return this.modeName;
    }

    public boolean toggleModeActivation() {
        this.isActivate = !this.isActivate;
        return this.isActivate;
    }

    public abstract Message getModeData();

    public abstract Message modeModify(int event);

    public abstract Message update(long systemTime);

    public abstract Message update(long systemTime, boolean currentMode);

}