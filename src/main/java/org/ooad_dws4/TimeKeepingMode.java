package org.ooad_dws4;

import java.util.HashMap;

public class TimeKeepingMode extends Mode{
    private TimeKeeping timekeeping;

    /* personally added */
    private int field = 0;
    private final boolean active = true;
    private int blink = 0;
    /* personally added */

    public TimeKeepingMode(long systemTime) {
        this.timekeeping = new TimeKeeping(systemTime);
        this.state = 0;
        isActivate = true;
    }

    private void changeField() {
        field = (field + 1) % 7;
    } //f

    /* seq.7 need to be deleted */
    /*public void changeValue(long value) {
        timekeeping.changeValue(value);
    }*/

    /* personally added */
    private void makeUpdateViewArg(HashMap<String, String> arg, long systemTime, String blink){ //f
        arg.put("0", Long.toString(systemTime));
        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", Long.toString(systemTime));
        arg.put("4", Long.toString(systemTime));
        arg.put("blink", blink);
    }
    /* personally added */
    public Message modeModify(int event){ //f
        long systemTime = 0;
        HashMap<String, String> arg = new HashMap<String, String>();
        /*
            this.state = state of timekeeping mode
            0 : default
            1 : edit
        */
        if(this.state == 0){ /* default state */
            switch(event){
                case 5:
                    changeState(this.state);
                    systemTime = timekeeping.getTimeData();
                    break;
                default: break;
            }
        }else if(this.state==1){ /* edit state */
            switch(event){
                case 1:
                    changeState(this.state);
                    systemTime = timekeeping.getTimeData();
                    break;
                case 2:
                    changeField();
                    systemTime = timekeeping.getTimeData();
                    break;
                case 3:
                    systemTime = timekeeping.changeValue(-1);
                    break;
                case 4:
                    systemTime = timekeeping.changeValue(1);
                    break;
                case 5:
                    changeState(this.state);
                    systemTime = timekeeping.getTimeData();
                    break;
                default: break;
            }
        }else return null;
        makeUpdateViewArg(arg, systemTime, null);
        if(this.state==1 && event == 2) makeUpdateViewArg(arg, systemTime, Integer.toString(blink++));
        return new Message(11, "updateView", arg);
    }

    @Override
    public void changeState(int state) {
        this.state = (this.state + 1) % 2;
    }

    /*public void saveModeActivation(){} //f*/

    /* for 6. View Time*/
    /* need to modify function name */
    public Message modeModify(long systemTime){ //f
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        arg.put("0", Long.toString(systemTime));
        arg.put("1", null);
        arg.put("3", Long.toString(systemTime));
        arg.put("4", Long.toString(systemTime));
        arg.put("blink", null);
        return new Message(11, "updateView", arg);
    }
    public Message getModeData(){ //f
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timekeeping.getTimeData(), null);
        return new Message(11,"updateView", arg);
    }

    @Override
    public void toggleModeActivation() {
        return;
    }

    /*@Deprecated
    public void changeTime() {
        *//* should be deleted? *//*
    }*/

    /*@Deprecated
    public void saveTime() {
        *//* should be deleted? *//*
    }*/
}