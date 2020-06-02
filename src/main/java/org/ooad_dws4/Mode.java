package org.ooad_dws4;

import java.util.Calendar;

public abstract class Mode extends DWSObject {

    protected boolean isActivate;
    protected int state;
    protected String modeName;
    protected Calendar cal;

    public int getState() {
        return this.state;
    }

    public boolean getIsActivate() {
        return this.isActivate;
    }

    public String getModeName() {
        return this.modeName;
    }

    public abstract Message getModeData();

    public boolean toggleModeActivation() {
        this.isActivate = !this.isActivate;
        return this.isActivate;
    }

    public abstract Message modeModify(int event);

    public abstract void changeState(int state);

    public abstract Message update(long systemTime);

    public abstract Message update(long systemTime, boolean currentMode);

    public abstract Message saveActivation();

    public abstract boolean receiveMessage(Message msg);

}