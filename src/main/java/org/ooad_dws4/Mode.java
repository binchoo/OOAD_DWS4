package org.ooad_dws4;

import java.util.Calendar;

public abstract class Mode extends DWSObject{
    protected boolean isActivate;
    protected int state;
    protected Calendar cal;
    public Mode() {
        cal = Calendar.getInstance();
    }
    public abstract Message getModeData();
//    public abstract Message toggleModeActivation();
    public abstract Message modeModify(int event);
    public abstract void changeState(int state);
    public abstract Message update(long systemTime);
    public abstract Message update(long systemTime, boolean currentMode);
    public Message saveActivation() {
        return null;
    }
    public boolean receiveMessage(Message msg) {
        return false;
    }

}