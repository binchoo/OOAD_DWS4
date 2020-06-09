package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AlarmMode extends Mode {
    private Alarm[] alarms;
    private int currentAlarmIndex;
    private int changingAlarmIndex;
    private int field;
    private long systemTime;
    private boolean timeSync;
    private Date date;

    public AlarmMode(boolean isActivation) {
        this.alarms = new Alarm[4];
        for (int i = 0; i < 4; i++)
            this.alarms[i] = new Alarm();
        this.state = 0;
        this.currentAlarmIndex = 0;
        this.changingAlarmIndex = this.currentAlarmIndex;
        this.field = 0;
        this.systemTime = 0;
        this.isActivate = isActivation;
        this.modeName = "ALARM";
        this.timeSync = true;
        date = new Date(this.alarms[currentAlarmIndex].getAlarmData());
    }

    /* system operation */
    private Message changeAlarmIndex(int value) {
        changeIndex(value);
        date.setTime(alarms[currentAlarmIndex].getAlarmData());
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);
    }

    /* system operation */
    private Message editAlarm() {
        HashMap<String, String> arg = new HashMap<>();
        if(getStateName()=="OFF") {
            changeState(1);
            makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), Integer.toString(field + 3));
        }else makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);
    }

    /* system operation */
    private Message saveAlarm() {
        changeState(0);
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);
    }

    private void changeIndex(int value) {
        if (this.currentAlarmIndex == 0 && value == -1) this.currentAlarmIndex = 3;
        else this.currentAlarmIndex = (this.currentAlarmIndex + value) % 4;
    }

    /* system operation */
    private Message toggleAlarmActivation() {
        alarms[currentAlarmIndex].toggleActivation();
        HashMap<String, String> arg = new HashMap<>();
        long corrector = this.alarms[currentAlarmIndex].getAlarmData() - systemTime >= 0 ? 0 : 1000 * 60 * 60 * 24;
        makeUpdateAlarmEventArg(arg, this.alarms[currentAlarmIndex].getAlarmData() - systemTime + corrector);
        return new Message(22, "updateAlarmEvent", arg);
        /*makeUpdateViewArg(arg, this.alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);*/
    }

    /* system operation */
    private Message changeField() {
        this.field = (this.field + 1) % 2;
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), Integer.toString(field + 3));
        return new Message(11, "updateView", arg);
    }

    /* system operation */
    private Message changeValue(int value) {
        calendar.setTime(date);
        if (this.field == 0) calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + value);
        else calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + value);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        alarms[currentAlarmIndex].setAlarmData(date.getTime());
        long alarmTime = alarms[currentAlarmIndex].getAlarmData();
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, alarmTime, Long.toString(field + 3));
        return new Message(11, "updateView", arg);
    }

    /*private void toggleActivation(){}  should be deleted. */
    private String getStateName() {
        if (this.state == 0) /* in state default */
            if (alarms[currentAlarmIndex].getState()) return " ON";
            else return "OFF";
        return "EDT";
    }

    /*private void makeUpdateViewArg(HashMap<String, String> arg, long alarmTime, String blink){ //f

        arg.put("0", getStateName());
        arg.put("1", null); *//* should be added in mode manager *//*
        arg.put("3", Long.toString(alarmTime));
        arg.put("4", Long.toString(alarmTime));
        arg.put("blink", blink);
    }*/
    @Override
    public Message getModeData() {
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, this.alarms[currentAlarmIndex].getAlarmData(), null);
        return new Message(11, "updateView", arg);
    }

    //    //@Override
//    public Message toggleModeActivation() {
//        HashMap<String, String> arg = new HashMap<String, String>();
//        this.isActivate = !this.isActivate;
//        return new Message(11, "updateView", arg);
//    }
    @Override
    /* 5 -> 2 -> 3|4 -> 1|5*/
    public Message modeModify(int event) {
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
            if (this.alarms[currentAlarmIndex].getState()) return null;
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


    private void changeState(int state) {
        this.state = state;
    }

    @Override
    public Message update(long systemTime) {
        if(timeSync) {
            for(int i=0; i < alarms.length; i++)
                alarms[i].setAlarmData(systemTime);
            date.setTime(systemTime);
            timeSync = false;
        }
        this.systemTime = systemTime;
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        if(timeSync) {
            for(int i=0; i < alarms.length; i++)
                alarms[i].setAlarmData(systemTime);
            date.setTime(systemTime);
            timeSync = false;
        }
        this.systemTime = systemTime;
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, alarms[currentAlarmIndex].getAlarmData(), null);
        arg.remove("blink");
        return new Message(11, "updateView", arg);
    }

    /* personally added */
    public Alarm[] getAlarms() {
        return this.alarms;
    }

    private void makeUpdateViewArg(HashMap<String, String> arg, long alarmTime, String blink) { //f
        String argData[] = makeTimeSet(alarmTime);
        arg.put("0", getStateName());
//        arg.put("1", null);
        arg.put("3", argData[3] + "|" + argData[4] + " " + (currentAlarmIndex + 1));
        arg.put("4", "  ALARM   ");
        arg.put("blink", blink);
    }

    private void makeUpdateAlarmEventArg(HashMap<String, String> arg, long alarmTime) {
        if (alarms[currentAlarmIndex].getState()) {
            arg.put("0", "ringing");
            arg.put("1", Long.toString(alarmTime));
        } else arg.put("0", "off");
        arg.put("2", Integer.toString(320 + currentAlarmIndex + 1));
    }

    private String[] makeTimeSet(long time) {
        Date tmpDate = new Date(time);
        String a[] = new String[7];
        a[0] = new SimpleDateFormat("yyyy").format(tmpDate);
        a[1] = new SimpleDateFormat("MM").format(tmpDate);
        a[2] = new SimpleDateFormat("dd").format(tmpDate);
        a[3] = new SimpleDateFormat("HH").format(tmpDate);
        a[4] = new SimpleDateFormat("mm").format(tmpDate);
        a[5] = new SimpleDateFormat("ss").format(tmpDate);
        a[6] = new SimpleDateFormat("EEE", new Locale("en", "US")).format(tmpDate).toUpperCase();
        return a;
    }
    /* personally added */
}