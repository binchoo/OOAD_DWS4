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
     *
     */
    private SimpleDateFormat dateFormat;
    /**
     *
     */
    private Mode[] modes;

    private boolean isEditState;

    /**
     *
     */
    private int currentMode;

    /**
     *
     */
    private int defaultMode;

    private Message ddayData;

    /**
     *
     */
    private int activationCount;

    private int activationModeIndex;

    /**
     * Default constructor
     */
    public ModeManager() {
        dateFormat = new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSSZ", new Locale("en", "US"));
        setModeObject();
        this.currentMode = 0;
        this.defaultMode = 0;
        this.activationModeIndex = this.defaultMode;
        isEditState = false;
    }

    private void setModeObject() {
        this.modes = new Mode[6];
        this.modes[0] = new TimeKeepingMode();
        this.modes[1] = new WoldTimeMode(false);
        this.modes[2] = new AlarmMode(true);
        this.modes[3] = new TimerMode(true);
        this.modes[4] = new StopwatchMode(false);
        this.modes[5] = new DDayMode(true);
        this.activationCount = 4;
    }


    /**
     * @param systemTime
     */
    public Message broadcast(long systemTime) {
        System.out.println("ModeManager : " + dateFormat.format(new Date(systemTime)));
        Message message;
        Message outputMessage;
        for (int i = 0; i < modes.length; i++) {
            if (i == currentMode) continue;
            Message temp = modes[i].update(systemTime, false);
            if (temp != null) message = temp;
        }
        outputMessage = modes[currentMode].update(systemTime, true);
//        outputMessage.getArg().put("1",)
        if (isEditState) return null;
        return outputMessage;
    }

    /**
     * @param event
     * @return
     */
    public Message modeModify(int event) {
        System.out.println("ModeManager : keypress " + event + " received!");

        if (isEditState) {
            return modeActivationControl(event);
        } else {
            if (currentMode == defaultMode) {
                if (event == 6)
                    return editModeActivation();
                if (event == 3 || event == 4) {
                    ddayData = modes[5].modeModify(event);
//                    return new Message(11, "ddayData", );
                }
            }

            if (event == 1) {
                changeModeIndex();
                return changeMode(currentMode);
            }
            return modes[currentMode].modeModify(event);
        }
    }

    /**
     *
     */
    private Message changeMode(int index) {
        return makeEditModeView(index);
        //return modes[index].getModeData();
    }


    /**
     *
     */
    private void changeModeIndex() {
        currentMode += 1;
        if (currentMode > 5)
            currentMode = currentMode % modes.length;
        if (!modes[currentMode].getIsActivate())
            changeModeIndex();
    }

    /**
     *
     */
    public Message showDefaultScreen(Message message) {
        if (message.getDestination() != 30) return null;
        if (!"SwitchDefaultScreen".equals(message.getAction())) return null;
        if (modes[currentMode].getState() == 1) return null;
        return changeMode(defaultMode);
    }

    /**
     *
     */
    private Message modeActivationControl(int event) {
        if (event == 3 || event == 4)
            return changeModeIndex(event);
        else if (event == 2)
            return changeEditTargetActivation();
        else if (event == 1 || event == 5)
            return saveActivation();
        return null;
    }

    private int countActiveMode() {
        int active = 0;
        for (Mode mode : this.modes)
            if (mode.isActivate) active++;
        return active;
    }

    private Message saveActivation() {
        this.isEditState = false;
        if (activationCount < 4) return null;
        return changeMode(currentMode);
    }

    private Message editModeActivation() {
        isEditState = true;
        activationModeIndex = defaultMode;
        return makeEditModeView(activationModeIndex);
    }

    private Message changeEditTargetActivation() {
        if (activationModeIndex == defaultMode) return null;
        modes[activationModeIndex].toggleModeActivation();
        activationCount = countActiveMode();
        if (activationCount > 4)
            modes[activationModeIndex].toggleModeActivation();
        return makeEditModeView(activationModeIndex);
    }

    private Message changeModeIndex(int event) {
        activationModeIndex += event == 4 ? 1 : -1;
        if (activationModeIndex > 5)
            activationModeIndex = activationModeIndex % modes.length;
        else if (activationModeIndex < 0)
            activationModeIndex = modes.length - 1;
        return makeEditModeView(activationModeIndex);
    }



    private Message makeEditModeView(int modeNumber) {
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", modes[modeNumber].getIsActivate() ? " ON" : "OFF");
        arg.put("1", "  " + activationCount + "/4");
        arg.put("3", " MODE");
        String modeName = modes[modeNumber].getModeName();
        switch (modeName.length()) {
            case 1:
                modeName = "    " + modeName + "     ";
                break;
            case 2:
                modeName = "    " + modeName + "    ";
                break;
            case 3:
                modeName = "   " + modeName + "    ";
                break;
            case 4:
                modeName = "  " + modeName + "    ";
                break;
            case 5:
                modeName = "  " + modeName + "   ";
                break;
            case 6:
                modeName = " " + modeName + "   ";
                break;
            case 7:
                modeName = " " + modeName + "  ";
                break;
            case 8:
                modeName = " " + modeName + " ";
                break;
            case 9:
                modeName = " " + modeName;
                break;
            default:
                modeName = modeName.substring(0, 10);
        }
        arg.put("4", modeName);
        return new Message(11, "updateView", arg);
    }

}