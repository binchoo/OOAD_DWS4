package org.ooad_dws4;

public abstract class Mode extends DWSObject{

    public Mode() {
    }
    protected boolean isActivate;
    protected int state;

    public abstract Message getModeData();
    public abstract Message toggleModeActivation();

    public void saveModeActivation() {
        // TODO implement here
    }
    public abstract Message modeModify(int event);
    public abstract void changeState(int state);
    public abstract Message update(long systemTime);

    /**
     * @param msg 
     * @return
     */
    public boolean receiveMessage(Message msg) {
        // TODO implement here
        return false;
    }

}