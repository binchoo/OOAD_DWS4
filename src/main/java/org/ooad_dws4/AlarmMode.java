package org.ooad_dws4;

public class AlarmMode extends Mode{
    private Alarm[] alarms;
    private int currentAlarmIndex;

    public AlarmMode() {
        this.alarms = new Alarm[4];
        this.currentAlarmIndex = 0;
    }

    public void changeAlarmIndex() {
        // TODO implement here
    }

    public void editAlarm() {
        // TODO implement here
    }

    public void saveAlarm() {
        // TODO implement here
    }

    public void changeIndex() {
        // TODO implement here
    }

    public void toggleAlarmActivation() {
        // TODO implement here
    }

    public void changeField() {
        // TODO implement here
    }

    public void changeValue() {
        // TODO implement here
    }

    private void toggleActivation(){}
    public Message getModeData(){
        return null;
    }

    @Override
    public void toggleModeActivation() {

    }

    @Override
    public Message modeModify(int event) {
        return null;
    }

    @Override
    public void changeState(int state) {

    }

}