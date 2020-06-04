package org.ooad_dws4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class WoldTimeMode extends Mode {
    private City[] cities;
    private int timeZoneIndex;
//    private int currentTimeZone;
    private long systemTime;
    private int changingIndex;
    private boolean systemTimeUpdateFlag;

    public WoldTimeMode(boolean isActivation) {
        this.cities = new City[4];
        this.cities[0] = new City(true, 9, "SEL"); /* Seoul : GMT+9 */
        this.cities[1] = new City(false, 2, "PAR"); /* Paris : GMT+2 */
        this.cities[2] = new City(false, 1, "LON"); /* London: GMT+1 */
        this.cities[3] = new City(false, -4, "NYC"); /* NY : GMT-4 */
        this.timeZoneIndex = 0;
        this.changingIndex = timeZoneIndex;
//        this.currentTimeZone = cities[timeZoneIndex].getTimeZoneData();
        this.systemTimeUpdateFlag = true;
        /*
         * NY : GMT-4 London: GMT+1 Paris : GMT+2 Seoul : GMT+9
         */
        this.isActivate = isActivation;
        this.modeName = "WORLD TIME";
    }

    private void changeCityIndex(int value) {
        if (this.changingIndex == 0 && value == -1) {
            changingIndex = 3;
            return;
        }
        this.changingIndex = (this.changingIndex + value) % 4;
    }

    private int calcOffsetDif(int currentTimeZone, int changedTimeZone) {
        return changedTimeZone - currentTimeZone;
    }


    @Override
    public Message getModeData() {
        int currentTimeZone = cities[timeZoneIndex].getTimeZoneData();
        int changedTimeZone = cities[changingIndex].getTimeZoneData();
        int offsetDiff = calcOffsetDif(currentTimeZone, changedTimeZone);
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, systemTime + (offsetDiff * 1000 * 60 * 60), null);
        return new Message(11, "updateView", arg);
    }

    @Override
    public Message modeModify(int event) {
        switch (event) {
            case 2:
                return setCity();
            case 3:
                return changeCity(-1);
            case 4:
                return changeCity(1);
        }
        return null;
    }

    /* system operation */
    private Message setCity() {
        int offsetDiff = calcOffsetDif(cities[timeZoneIndex].getTimeZoneData(),
                cities[changingIndex].getTimeZoneData());
        HashMap<String, String> arg = new HashMap<>();
        arg.put("newTime", Integer.toString(offsetDiff * 1000 * 60 * 60));
        this.systemTime += offsetDiff * 1000 * 60 * 60;
        this.timeZoneIndex = this.changingIndex;
//        this.currentTimeZone = this.cities[timeZoneIndex].getTimeZoneData();
        this.systemTimeUpdateFlag = true;
        for (int i = 0; i < 4; i++)
            cities[i].changeState(i == timeZoneIndex);
        return new Message(21, "updateWorldTime", arg);
    }

    /* system operation */
    private Message changeCity(int value) {
        this.systemTimeUpdateFlag = false;
        int currentTimeZone = cities[timeZoneIndex].getTimeZoneData();
        changeCityIndex(value);
        int changedTimeZone = cities[changingIndex].getTimeZoneData();
        int offsetDiff = calcOffsetDif(currentTimeZone, changedTimeZone);
        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, systemTime + (offsetDiff * 1000 * 60 * 60), null);
        return new Message(11, "updateView", arg);
    }

    @Override
    public Message update(long systemTime) {
        this.systemTime = systemTime;
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        if (this.systemTimeUpdateFlag)
            this.systemTime = systemTime;
        int currentTimeZone = cities[timeZoneIndex].getTimeZoneData();
        int changedTimeZone = cities[changingIndex].getTimeZoneData();
        int offsetDiff = calcOffsetDif(currentTimeZone, changedTimeZone);
        HashMap<String, String> arg = new HashMap<>();
        makeUpdateViewArg(arg, systemTime + (offsetDiff * 1000 * 60 * 60), null);
        arg.remove("blink");
        return new Message(11, "updateView", arg);
    }

    /* personally added */
    public long getSystemTime() {
        return this.systemTime;
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

    private void makeUpdateViewArg(HashMap<String, String> arg, long systemTime, String blink) { // f
        String argData[] = makeTimeSet(systemTime);
        arg.put("0", cities[changingIndex].getName());
        /* arg.put("1", null); *//* should be added in mode manager */
        if (timeZoneIndex == changingIndex)
            arg.put("3", argData[3] + "|" + argData[4] + "TZ");
        else
            arg.put("3", argData[3] + "|" + argData[4] + "  ");
        arg.put("4", "WORLD TIME");
        arg.put("blink", null);
    }
    /* personally added */
}