package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DDayMode extends Mode {

    private DDay[] ddays;
    private HashMap<String, String> arg;
    private Calendar calendar;
    private int currentIndex;
    private int field = 0;
    private long value = 0;
    private long systemTime;
    private final long aDay = 86400000;

    public DDayMode(boolean isActivation) {
        Date date = new Date();
        ddays = new DDay[4];
        for (int i = 0; i < 4; i++)
            ddays[i] = new DDay(date.getTime());
        this.calendar = Calendar.getInstance();
        this.isActivate = isActivation;
        this.modeName = "D-DAY";
        this.systemTime = 0;
        currentIndex = 0;
    }

    @Override
    public Message getModeData() {
        return makeView(currentIndex);
    }

    /*
     * @Override public Message toggleModeActivation() { return null; }
     */

    private void changeState(String state) {
        if ("DEFAULT".equals(state))
            this.state = 0;
        else if ("EDIT".equals(state))
            this.state = 1;
    }

    @Override
    public Message update(long systemTime) {
        this.systemTime = systemTime;
        HashMap<String, String> arg = new HashMap<>();
        arg.put("1", makeDDayCount(this.systemTime, ddays[currentIndex].getTime()));
        if (!ddays[currentIndex].getState()) return null;
        return new Message(30, "1", arg);
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        if (!currentMode)
            return null;
        this.systemTime = systemTime;
        return makeView(currentIndex);
    }

    @Override
    public Message modeModify(int event) {
        if (this.state == 0) {
            if (event == 2)
                return toggleDdayActivation();
            else if (event == 3 || event == 4)
                return changeCurrentIndex(event == 4 ? 1 : -1);
            else if (event == 5)
                return enterDdayEdit();
        } else if (this.state == 1) {
            if (event == 1 || event == 5)
                return saveDday();
            else if (event == 2)
                return changeField();
            else if (event == 3 || event == 4)
                return changeValue(event == 4 ? 1 : -1);
        }
        return null;
    }

//    public Message countActiveMode(int event) {
//        if (event != 3 && event != 4)
//            return null;
//        int sign = event == 4 ? 1 : -1;
//        for (int i = 0; i < ddays.length; i++) {
//            currentIndex += sign;
//            if (currentIndex > ddays.length - 1)
//                currentIndex = 0;
//            else if (currentIndex < 0)
//                currentIndex = ddays.length - 1;
//            if (ddays[currentIndex].getState())
//                break;
//        }
//        return makeView(currentIndex);
//    }

    private Message changeField() {
        Message message = makeView(currentIndex);
        field++;
        field %= 3;
        message.getArg().put("blink", Integer.toString(field));
        return message;
    }

    private Message changeValue(int sign) {
        long rawTime = ddays[currentIndex].getTime();
        Date date = new Date(rawTime);
        this.calendar.setTime(date);
        if (field == 0)
            calendar.add(Calendar.YEAR, sign);
        else if (field == 1)
            calendar.add(Calendar.MONTH, sign);
        else
            calendar.add(Calendar.DATE, sign);
        ddays[currentIndex].setTime(calendar.getTime().getTime());
        return makeView(currentIndex);
    }

    private Message saveDday() {
        changeState("DEFAULT");
        field = 0;
        Message message = makeView(currentIndex);
        message.getArg().put("blink", null);
        return message;
    }

    private Message changeCurrentIndex(int sign) {
        currentIndex += sign;
        if (currentIndex > ddays.length - 1)
            currentIndex = 0;
        else if (currentIndex < 0)
            currentIndex = ddays.length - 1;
        return makeView(currentIndex);
    }

    private Message enterDdayEdit() {
        changeState("EDIT");
        Message message = makeView(currentIndex);
        message.getArg().put("blink", "0");
        return message;
    }

    private Message toggleDdayActivation() {
        ddays[currentIndex].changeState();
        return makeView(currentIndex);
    }

    private String makeViewString(long rawSec) {
        char sign = rawSec >= 0 ? '+' : '-';
        long sec = Math.abs(rawSec);
        int day = (int) (sec / (1000 * 60 * 60 * 24)); // day = 1234 / 0123 / 0012 / 0001 / 0000 ...
        if (day > 9999)
            day = 9999;
        int nonZero = (int) Math.log10(day) + 1;
        if (day == 0)
            nonZero = 1;
        String str = "";
        str += currentIndex + 1;
        str += sign;
        for (int i = 0; i < 4 - nonZero; i++)
            str += ' ';
        str += String.valueOf(day);
        return str;
    }

    private String makeDDayCount(long targetTime, long baseTime) {
        long difference = targetTime - baseTime;
        String sign = difference < 0 ? "-" : "+";
        difference = Math.abs(difference);
        int day = (int) (difference / aDay);
        day = Math.min(day, 9999);
        char charDay[] = Integer.toString(day).toCharArray();
        char result[] = "    ".toCharArray();
        for (int i = 0; i < charDay.length; i++)
            result[result.length - charDay.length + i] = charDay[i];
        return sign + (currentIndex + 1) + new String(result);
    }

    private String makeDateForm(long time) {
        Date date = new Date(time);
        String year = new SimpleDateFormat("yyyy").format(date);
        String month = new SimpleDateFormat("MM").format(date);
        String day = new SimpleDateFormat("dd").format(date);
        return year + "-" + month + "-" + day;
    }

    private Message makeView(int index) {
        HashMap<String, String> arg = new HashMap<>();
        String state;
        if (this.state == 1)
            state = "EDT";
        else if (ddays[index].getState())
            state = " ON";
        else
            state = "OFF";
        arg.put("0", state);
        if (ddays[index].getState())
            arg.put("1", makeDDayCount(systemTime, ddays[index].getTime()));
        else
            arg.put("1", " " + (currentIndex + 1) + " OFF");
        arg.put("3", "D-DAY " + (index + 1));
        arg.put("4", makeDateForm(ddays[index].getTime()));
        return new Message(11, "updateView", arg);
    }
}