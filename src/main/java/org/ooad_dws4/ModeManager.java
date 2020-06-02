package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 */
public class ModeManager {

    /**
     * Default constructor
     */
    public ModeManager() {
        dateFormat = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSSZ", new Locale("en", "US"));
        setModeObject();
        this.currentMode = this.modes[0];
        this.defaultMode = this.modes[0];
    }

    private void setModeObject() {
        this.modes = new Mode[6];
        this.modes[0] = new TimeKeepingMode();
        this.modes[1] = new WoldTimeMode();
        this.modes[2] = new AlarmMode();
        this.modes[3] = new TimerMode();
        this.modes[4] = new StopwatchMode();
        this.modes[5] = new DDayMode();
    }

    private SimpleDateFormat dateFormat;
    /**
     *
     */
    private Mode[] modes;

    /**
     *
     */
    private Mode currentMode;

    /**
     *
     */
    private Mode defaultMode;

    /**
     * @param systemTime
     */
    public Message broadcast(long systemTime) {
        System.out.println("ModeManager : " + dateFormat.format(new Date(systemTime)));
        Message message;
        Message outputMessage;
//        for (Mode mode : this.modes) {
//            if (mode == this.currentMode)
//                continue;
//            message = mode.update(systemTime);
//        }
//        outputMessage = this.currentMode.update(systemTime);
//        outputMessage.getArg().put("1",);
        return null;
    }

    /**
     * @param event
     * @return
     */
    public Message modeModify(int event) {
        System.out.println("ModeManager : keypress " + event + " received!");
//        return this.modes[currentMode].modeModify(event);
        return null;
    }

    /**
     *
     */
    public void changeMode() {
        // TODO implement here
    }

    /**
     *
     */
    public void enterModeActivationEdit() {
        // TODO implement here
    }

    /**
     *
     */
    public void changeActivationEditTarget() {
        // TODO implement here
    }

    /**
     *
     */
    public void changeModeIndex() {
        // TODO implement here
    }

    /**
     *
     */
    public Message showDefaultScreen(Message message) {
        if (message.getDestination() == 30) {
            if ("SwitchDefaultScreen".equals(message.getAction())) {
                HashMap map = new HashMap<String, String>();
                map.put("do", "Timekeeping");
                return new Message(11, "updateView", map);
            }
        }
        return null;
    }

    /**
     *
     */
    public void editModeActivation() {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean saveActivation() {
        // TODO implement here
        return true;
    }

}