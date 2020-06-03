package org.ooad_dws4;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* active parameter , system time */
public class TimeKeepingMode extends Mode{
    private TimeKeeping timekeeping;
    private int field ;
    private Date date;
    public TimeKeepingMode() {
        this.timekeeping = new TimeKeeping(0);
        this.state = 0;
        this.field = -1;
        this.isActivate = true;
        date = new Date(0);
        this.modeName = "TIMEKEEPER";
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
        field = (field + 1) % 6;
        long timeData = timekeeping.getTimeData();
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, timeData, Integer.toString(field));
        return new Message(11, "updateView", arg);
    } //f

    /* system operation */
    private Message changeValue(int value) {
//        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch(this.field){
            case 0:
                cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + value);
                break;
            case 1:
                cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + value);
                break;
            case 2:
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + value);
                break;
            case 3:
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + value);
                break;
            case 4:
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + value);
                break;
            case 5:
                cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + value);
                break;
        }
        date = cal.getTime();
        timekeeping.setTimeData(date.getTime());
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
        if (systemTime % 1000 == 0)
            makeUpdateViewArg(arg, timeData, null);
        else makeUpdateViewArg(arg, -1, null);
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        if(currentMode) {
            timekeeping.setTimeData(systemTime);
            long timeData = timekeeping.getTimeData();
            HashMap<String, String> arg = new HashMap<String, String>();
            if (systemTime % 1000 == 0)
                makeUpdateViewArg(arg, timeData, null);
            else makeUpdateViewArg(arg, -1, null);
            return new Message(11, "updateView", arg);
        }
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

    public TimeKeeping getTimekeeping() {
        return this.timekeeping;
    }

    private String[] makeTimeSet(long time){
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

    private void makeUpdateSystemTimeArg(HashMap<String, String> arg, long systemTime, String blink){
        String argData[] = makeTimeSet(systemTime);
        arg.put("0", argData[6]);
        /*arg.put("1", null); *//* should be added in mode manager */
        arg.put("3", argData[3]+"|"+argData[4]+argData[5]);
        arg.put("4", argData[0]+"-"+argData[1]+"-"+argData[2]);
        arg.put("blink", blink);
        arg.put("newTime", Long.toString(systemTime));
    }
    private void makeUpdateViewArg(HashMap<String, String> arg, long systemTime, String blink){ //f
        String argData[] = makeTimeSet(systemTime);
        if(systemTime == -1)
        {
            /*arg.put("0", "");
            arg.put("1", ""); *//* should be added in mode manager *//*
            arg.put("3", "");
            arg.put("4", "");
            arg.put("blink", blink);*/
            return;
        }
        arg.put("0", argData[6]);
        /*arg.put("1", null); *//* should be added in mode manager */
        arg.put("3", argData[3]+"|"+argData[4]+argData[5]);
        arg.put("4", argData[0]+"-"+argData[1]+"-"+argData[2]);
        arg.put("blink", blink);
    }
    /* personally added */
}