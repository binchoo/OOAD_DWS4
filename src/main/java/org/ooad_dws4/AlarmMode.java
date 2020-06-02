package org.ooad_dws4;

import java.util.HashMap;

public class AlarmMode extends Mode {
    private Alarm[] alarms;
    private int currentAlarmIndex;
    private int field;

    public AlarmMode(boolean isActivation) {
        this.alarms = new Alarm[4];
        for (int i = 0; i < 4; i++) {
            this.alarms[i] = new Alarm();
        }
        this.currentAlarmIndex = 0;
        this.field = 0;
        this.isActivate = isActivation;
        this.modeName = "ALARM";
    }

    public Message changeAlarmIndex(int value) {
        if (this.currentAlarmIndex == 0 && value == -1) {
            this.currentAlarmIndex = 3;
            return null;
        }
        this.currentAlarmIndex = (this.currentAlarmIndex + value) % 4;
        return null;
    }

    public Message editAlarm() {
        return null;
    }

    public Message saveAlarm() {
        return null;
    }

    /*public void changeIndex() {

    }*/

    public Message toggleAlarmActivation() {
        alarms[currentAlarmIndex].toggleActivation();
        return null;
    }

    public Message changeField() {
        this.field = (this.field + 1) % 3;
        return null;
    }

    public Message changeValue(int value) {
        return null;
    }

    /*private void toggleActivation(){}  should be deleted. */
    private String getStateName() {
        if (this.state == 0) /* in state default */ {
            if (alarms[currentAlarmIndex].getState() == true) {
                return "_ON";
            } else {
                return "OFF";
            }
        }
        return "EDT";
    }

    private void makeUpdateViewArg(HashMap<String, String> arg, long alarmTime, String blink) { //f
        arg.put("0", getStateName());
        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", Long.toString(alarmTime));
        arg.put("4", Long.toString(alarmTime));
        arg.put("blink", blink);
    }

    public Message getModeData() {
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, this.alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);
    }

//    @Override
//    public Message toggleModeActivation() {
//        HashMap<String, String> arg = new HashMap<String, String>();
//        this.isActivate = !this.isActivate;
//        return new Message(11, "updateView", arg);
//    }

    @Override
    public Message modeModify(int event) {
        HashMap<String, String> arg = new HashMap<String, String>();
        if (this.state == 0) {
            switch (event) {
                case 2:
                    return toggleAlarmActivation();
                case 3:
                    return changeAlarmIndex(-1);
                case 4:
                    return changeAlarmIndex(1);
                case 5:
                    return editAlarm();
            }
        } else {
            switch (event) {
                case 1:
                case 5:
                    return saveAlarm();
                case 2:
                    return changeField();
                case 3:
                    return changeValue(-1);
                case 4:
                    return changeValue(1);
            }
        }
        return null;
    }

    @Override
    public void changeState(int state) {
        this.state = (this.state + 1) % 2;
    }

    @Override
    public Message update(long systemTime) {
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        return null;
    }

    @Override
    public Message saveActivation() {
        return null;
    }

    @Override
    public boolean receiveMessage(Message msg) {
        return false;
    }

    public int getCurrentAlarmIndex() {
        return currentAlarmIndex;
    }
}