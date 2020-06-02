package org.ooad_dws4;
import java.util.HashMap;
public class TimeKeepingMode extends Mode{
    private TimeKeeping timekeeping;
    private int field ;
    private int valueChangeTracking[];
    public TimeKeepingMode() {
        this.timekeeping = new TimeKeeping(0);
        this.state = 0;
        this.field = -1;
        this.valueChangeTracking = new int[6];
        for(int i=0; i<6; i++)
            valueChangeTracking[i] = 0;
        isActivate = true;
        this.modeName = "WATCH";
    }

    /* 5 -> 2 -> 3|4 -> 1|5 */
    @Override
    public Message modeModify(int event){
        if(this.state == 0){ /* default state */
            switch(event){
                case 5: /* change state to edit mode */
                    return changeTime();
            }
        }else if(this.state==1){ /* edit state */
            switch(event){
                case 1: /* change state to default mode */
                case 5:
                    return saveTime();
                case 2:
                    return changeField();
                case 3:
                    return changeValue(-1);
                case 4:
                    return changeValue(1);
            }
        }else return null;
        return null;
    }

    /* system operation */
    private Message changeField() {
        field = (field + 1) % 7;
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timeData, Integer.toString(field));
        return new Message(11, "updateView", arg);
    } //f

    /* system operation */
    private Message changeValue(int value) {
        timekeeping.setTimeData(timekeeping.getTimeData() + value);
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateSystemTimeArg(arg, timeData, Integer.toString(field));
        return new Message(21, "updateSystemTime", arg);
    }

    /* system operation */
    private Message changeTime() {
        changeState((this.state+1) % 2);
        HashMap<String, String> arg = new HashMap<String, String>();
        long timeData = timekeeping.getTimeData();
        this.field++;
        makeUpdateViewArg(arg, timeData, Integer.toString(this.field));
        return new Message(11, "updateView", arg);
    }

    /* system operation */
    private Message saveTime() {
        changeState((this.state+1) % 2);
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timeData, null);
        this.field = -1;
        return new Message(11, "updateView", arg);
    }
    @Override
    public void changeState(int state) {
        this.state = state;
    }

    /* for 6. View Time*/
    /* need to modify function name */
    @Override
    public Message update(long systemTime){ //f
        timekeeping.setTimeData(systemTime);
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        if(systemTime % 1000 == 0)
            makeUpdateViewArg(arg, systemTime, null);
        else makeUpdateViewArg(arg, -1, null);
        return new Message(11, "updateView", arg);
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

    @Override
    public Message getModeData(){ //f
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timekeeping.getTimeData(), null);
        return new Message(11,"updateView", arg);
    }


    /* personally added */
    private void makeUpdateSystemTimeArg(HashMap<String, String> arg, long systemTime, String blink){

    }
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