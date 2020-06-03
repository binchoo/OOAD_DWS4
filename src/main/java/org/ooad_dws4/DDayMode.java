package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DDayMode extends Mode {

    private DDay[] ddays;
    private HashMap<String, String> arg;
    private int currentDday = 0;
    private int field = 0;
    private long value = 0;
    private boolean isActivate;
    private long systemTime;
    private final long aDay = 86400000;

    public DDayMode(boolean isActivation) {
        ddays = new DDay[4];
        for (int i = 0; i < 4; i++) {
            ddays[i] = new DDay();
        }
        this.isActivate = isActivation;
        this.modeName = "D-DAY";
        this.systemTime = 0;
    }

    public Message getModeData() {
        return null;
    }


    @Override
    public void changeState(int state) {
        this.state = state;
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
    public boolean receiveMessage(Message msg) {
        return false;
    }

    @Override
    public Message modeModify(int event) {
        if (this.state == 0) {
            switch (event) {
                case 2:
                    toggleDdayActivation();
                    break;
                case 3:
                case 4:
                    changeDdayIndex(event == 4 ? 1 : -1);   // -
                    long timeDif = ddays[currentDday].getmTime() - this.systemTime;
                    return new Message(11, "updateView", new HashMap<String, String>() {{
                        put("1", makeViewString(timeDif))
                    }});
                break;
                case 5:
                    enterDdayEdit();
                    break;
            }

        } else if (this.state == 1) {
            switch (event) {
                case 1:
                case 5:
                    saveDday();
                    break;
                case 2:
                    changeField();
                    break;
                case 3:
                case 4:
                    changeValue(event == 4 ? 1 : -1);
                    break;
            }

        }
        return null;
    }

    public boolean toggleModeActivation() {
        return true;
    }

    public void changeField() {
        field++;
        field %= 3;
    }

    public void changeValue(int sign) {
        if (field == 0) {
            value += sign;
        } else if (field == 1) {
            value += 60 * sign;
        } else {
            value += 3600 * sign;
        }
    }

    public void saveDday() {
        changeState(0);
    }

    public void changeDdayIndex(int updown) {
        currentDday = (currentDday += updown) % 4;
    }

    public void enterDdayEdit() {
        changeState(1);
    }

    public void toggleDdayActivation() {
        ddays[currentDday].changeState();
    }


    public String makeViewString(long sec) {
        char sign = sec >= 0 ? '+' : '-';
        sec = Math.abs(sec);
        int day = (int) (sec / (1000 * 60 * 60 * 24)); // day = 1234 / 0123 / 0012 / 0001 / 0000 ...
        if (day > 9999) day = 9999;
        int nonZero = (int) Math.log10(day) + 1;
        if (day == 0) nonZero = 1;
        String str = "";
        str += currentDday + 1;
        str += sign;
        for (int i = 0; i < 4 - nonZero; i++) str += ' ';
        str += String.valueOf(day);
        return str;
    }

    public String makeDDayCount(long targetTime, long baseTime) {
        long difference = targetTime - baseTime;
        char sign = difference < 0 ? '-' : '+';
        difference = Math.abs(difference);
        int day = (int) (difference / aDay);
        day = Math.min(day, 9999);
        String dayForm = Integer.toString(day);

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
        arg.put("0", this.state == 0 ? "   " : "EDT");
        arg.put("1");
        arg.put("3", "D-DAY " + index);
        arg.put("4", makeDateForm(ddays[index].getmTime()));
        return null;
    }
}