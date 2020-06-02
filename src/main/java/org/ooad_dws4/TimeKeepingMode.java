package org.ooad_dws4;

import java.util.HashMap;

public class TimeKeepingMode extends Mode{
    private TimeKeeping timekeeping;
    /* personally added */
    private int field ;
    /*private int blink;*/
    /* personally added */

    public TimeKeepingMode(long systemTime) {
        this.timekeeping = new TimeKeeping(systemTime);
        this.state = 0;
        this.field = -1;
        /*this.blink = 0;*/
        isActivate = true;
    }

    public Message modeModify(int event){ //f
        long systemTime = 0;
        HashMap<String, String> arg = new HashMap<String, String>();
        if(this.state == 0){ /* default state */
            switch(event){
                case 5:
                    changeState(this.state);
                    systemTime = timekeeping.getTimeData();
                    return changeTime(systemTime);
            }
        }else if(this.state==1){ /* edit state */
            switch(event){
                case 1:
                case 5:
                    systemTime = timekeeping.getTimeData();
                    return saveTime(systemTime);
                case 2:
                    systemTime = timekeeping.getTimeData();
                    return changeField(systemTime);
                case 3:
                    return changeValue(-1);
                case 4:
                    return changeValue(1);
                default: break;
            }
        }else return null;
        makeUpdateViewArg(arg, systemTime, null);
        if(this.state==1 && event == 2) makeUpdateViewArg(arg, systemTime, Integer.toString(this.field));
        return new Message(11, "updateView", arg);
    }

    private Message changeField(long systemTime) {
        field = (field + 1) % 7;
        return null;
    } //f

    /* seq.7 need to be deleted */
    public Message changeValue(long systemTime) {
        return null;
    }


    @Override
    public void changeState(int state) {
        this.state = (this.state + 1) % 2;
    }

    /*public void saveModeActivation(){} //f*/

    /* for 6. View Time*/
    /* need to modify function name */
    public Message update(long systemTime){ //f
        timekeeping.setTimeData(systemTime);
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        if(systemTime % 1000 == 0)
            makeUpdateViewArg(arg, systemTime, null);
        else makeUpdateViewArg(arg, -1, null);
        return new Message(11, "updateView", arg);
    }
    public Message getModeData(){ //f
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timekeeping.getTimeData(), null);
        return new Message(11,"updateView", arg);
    }

    @Override
    public Message toggleModeActivation() {
        return null;
    }


    public Message changeTime(long systemTime) {
        return null;
    }


    public Message saveTime(long systemTime) {
        changeState(this.state);
        return null;
    }

    /* personally added */
    private void makeUpdateViewArg(HashMap<String, String> arg, long systemTime, String blink){ //f
        if(systemTime == -1)
        {
            arg.put("0", "");
            arg.put("1", ""); /* should be added in mode manager */
            arg.put("3", "");
            arg.put("4", "");
            arg.put("blink", blink);
            return;
        }
        arg.put("0", Long.toString(systemTime));
        arg.put("1", null); /* should be added in mode manager */
        arg.put("3", Long.toString(systemTime));
        arg.put("4", Long.toString(systemTime));
        arg.put("blink", blink);
    }
    /* personally added */
}