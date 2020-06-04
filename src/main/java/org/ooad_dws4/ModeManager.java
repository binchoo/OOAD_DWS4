package org.ooad_dws4;

import java.util.HashMap;

/**
 * @author Kelvin Kwak (lunox273@gmail.com)
 * @brief Central Bride from DWS
 */
public class ModeManager {

    /**
     * @brief Modes for real functions
     */
    private Mode[] modes;

    /**
     * @brief Is the system in edit mode?
     */
    private boolean isEditState;

    /**
     * @brief Mode displayed on the screen
     */
    private int currentMode;

    /**
     * @brief Default mode of the system
     */
    private int defaultMode;

    /**
     * @brief D-Day data displayed on LCD1
     */
    private String ddayData;

    /**
     * @brief Number of activation modes
     */
    private int activationCount;

    /**
     * @brief Number of maximum activation modes
     */
    private int maxActivationCount;

    /**
     * @brief Index of the mode being edited in active mode
     */
    private int activationModeIndex;

    /**
     * @brief Default constructor
     */
    public ModeManager() {
        setModeObject();
        this.currentMode = 0;
        this.defaultMode = 0;
        this.ddayData = "      ";
        this.maxActivationCount = 4;
        this.activationModeIndex = this.defaultMode;
        isEditState = false;
    }

    /**
     * @brief Set each function modes
     */
    private void setModeObject() {
        this.modes = new Mode[6];
        this.modes[0] = new TimeKeepingMode();
        this.modes[1] = new WoldTimeMode(false);
        this.modes[2] = new AlarmMode(false);
        this.modes[3] = new TimerMode(true);
        this.modes[4] = new StopwatchMode(true);
        this.modes[5] = new DDayMode(true);
        this.activationCount = 4;
    }

    /**
     * @param systemTime Current System time from TimeRunner
     * @return Message for Update LCD View
     * @brief Update operation for each unit time for modes
     */
    public Message broadcast(long systemTime) {
        Message outputMessage;
        for (int i = 0; i < modes.length; i++) {
            if (i == currentMode)
                continue;
            if (!modes[i].getIsActivate())
                continue;
            Message temp = modes[i].update(systemTime);
            if (temp != null)
                if (i == 5 && temp.getArg().containsKey("1"))
                    ddayData = temp.getArg().get("1");
        }
        outputMessage = modes[currentMode].update(systemTime, true);
        outputMessage.getArg().put("1", ddayData);
        if (isEditState)
            return null;
        return outputMessage;
    }

    /**
     * @param event Button press event (1~8)
     * @return Message that feedback for each button press event
     * @brief Do action suitable for each button press event
     */
    public Message modeModify(int event) {
        if (isEditState) {
            return modeActivationControl(event);
        } else {
            boolean isNotEditMode = modes[currentMode].getState() != 1;
            if (currentMode == defaultMode) {
                if (event == 6)
                    return editModeActivation();
                if (isNotEditMode) {
                    if (event == 2)
                        return new Message(10, "toggleMute", null);
                    else if (event == 3 || event == 4) {
                        if (!modes[5].getIsActivate())
                            return null;
                        String temp = modes[5].modeModify(event).getArg().get("1");
                        if (temp != null)
                            ddayData = temp;
                        HashMap<String, String> map = new HashMap<>();
                        map.put("1", ddayData);
                        return new Message(11, "updateView", map);
                    }
                }
                if (event == 8) {
                    HashMap<String, String> map = new HashMap<>();
                    return new Message(10, "toggleMute", map);
                }
            }
            if (event == 1 && isNotEditMode) {
                changeModeIndex();
                return changeMode(currentMode);
            }
            Message message = modes[currentMode].modeModify(event);
            if (message == null) return null;
            if (currentMode == 5 && message.getArg().containsKey("1"))
                ddayData = message.getArg().get("1");
            return message;
        }
    }

    /**
     * @param message SwitchDefaultScreen Message
     * @return Return default mode's updateView Message
     * @brief If the current mode is not in edit state, change the mode to the
     * default screen
     */
    public Message showDefaultScreen(Message message) {
        if (message.getDestination() != 30)
            return null;
        if (!"SwitchDefaultScreen".equals(message.getAction()))
            return null;
        if (modes[currentMode].getState() == 1)
            return null;
        currentMode = defaultMode;
        return changeMode(defaultMode);
    }

    /**
     * @param index Mode Index
     * @return Message for updateView data from mode
     * @brief Returns data to display the LCD in the mode suitable for the index
     */
    private Message changeMode(int index) {
        // return makeEditModeView(index);
        return modes[index].getModeData();
    }

    /**
     * @brief Change current mode to next active mode
     */
    private void changeModeIndex() {
        currentMode += 1;
        if (currentMode > 5)
            currentMode = currentMode % modes.length;
        if (!modes[currentMode].getIsActivate())
            changeModeIndex();
    }

    /**
     * @param event Button press event (1~8)
     * @return Message that feedback for each button press event
     * @brief A collection of method for editing the activation mode
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

    /**
     * @return Current activation mode
     * @brief Recalculate the number of currently active modes
     */
    private int countActiveMode() {
        int active = 0;
        for (Mode mode : this.modes)
            if (mode.isActivate)
                active++;
        return active;
    }

    /**
     * @return updateView message that is current(default) mode's view
     * @brief Submit the edited mode activation and return to the default screen if
     * Alarm mode is deactivation, add removeAlarmAll Action to Message if
     * DDay mode is deactivation, clear ddayData property
     */
    private Message saveActivation() {
        if (activationCount < 4)
            return null;
        this.isEditState = false;
        Message message = changeMode(currentMode);
        if (!modes[2].getIsActivate())
            message.getArg().put("Action", "removeAlarmAll");
        if (!modes[5].getIsActivate())
            ddayData = "      ";
        return message;
    }

    /**
     * @return updateView message that is the mode activation edit view
     * @brief Enter edit mode activation
     */
    private Message editModeActivation() {
        isEditState = true;
        activationModeIndex = defaultMode;
        return makeEditModeView(activationModeIndex);
    }

    /**
     * @return updateView message that is the mode activation edit view
     * @brief Edit mode activation. Cancel if the active mode is greater than the
     * maxActivationCount.
     */
    private Message changeEditTargetActivation() {
        if (activationModeIndex == defaultMode)
            return null;
        modes[activationModeIndex].toggleModeActivation();
        activationCount = countActiveMode();
        if (activationCount > 4) {
            modes[activationModeIndex].toggleModeActivation();
            activationCount--;
        }
        return makeEditModeView(activationModeIndex);
    }

    /**
     * @param event Button press event (1~8)
     * @return updateView message that is the mode activation edit view
     * @brief Change the mode-activated editing index to cycle
     */
    private Message changeModeIndex(int event) {
        activationModeIndex += event == 4 ? 1 : -1;
        if (activationModeIndex > 5)
            activationModeIndex = activationModeIndex % modes.length;
        else if (activationModeIndex < 0)
            activationModeIndex = modes.length - 1;
        return makeEditModeView(activationModeIndex);
    }

    /**
     * @param modeNumber
     * @return updateView message that is the mode activation edit view
     * @brief Returns the updateView message that is the mode activation edit view
     */
    private Message makeEditModeView(int modeNumber) {
        HashMap<String, String> arg = new HashMap<>();
        arg.put("0", modes[modeNumber].getIsActivate() ? " ON" : "OFF");
        arg.put("1", "   " + activationCount + "/" + maxActivationCount);
        arg.put("3", " MODE  ");
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