package org.ooad_dws4;

import java.util.HashMap;

/**
 *
 */
public class TimerMode extends Mode{

    /**
     *
     */
    private Timer timer;

    private int field=0;
    private long value=0;
    private HashMap <String, String> arg;

    public TimerMode() {

        this.timer = new Timer();
        this.state=0;
        this.field=-1;
        isActivate=true;
    }

    @Override
    public Message getModeData() {
        return null;
    }

    @Override
    public Message toggleModeActivation() {
        return null;
    }

    /**
     *
     */
    public void changeField() {
        // TODO implement here

        field++;
        field%=3;

    }

    /**
     *
     */
    public void changeValue() {
        // TODO implement here
        if(field==0){
            value++;
        }
        else if(field==1){
            value+=60;
        }
        else{
            value+=3600;
        }
    }

    /**
     *
     */
    public void resetTimer() {
        // TODO implement here
        timer.reset();

    }

    /**
     *
     */
    public void startTimer() {
        // TODO implement here
        // change state running
        changeState(2);
    }

    /**
     *
     */
    public void changeTimerTime() {
        // TODO implement here
        //change state edit
        changeState(1);
    }

    /**
     *
     */
    public void saveTimer(long value) {
        // TODO implement here
        timer.setDeadlineData(value);
        changeState(0);
    }

    /**
     *
     */
    public void pauseTimer() {
        // TODO implement here
        //change state pause
        changeState(3);
    }

    /**
     *
     */
    public void resumeTimer() {
        // TODO implement here
        //change state running
        changeState(2);
    }

    /**
     *
     */
    public void changeState(int state) {
        // TODO implement here
        this.state =state;

    }

    @Override
    public Message update(long systemTime) {
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {
        return null;
    }

    public Message modeModify(int event) {
        // TODO implement here

        HashMap<String, String> arg = new HashMap<String, String>();

        if(this.state==0){
            switch (event){
                case 1:
                    //change mode
                case 3:
                    startTimer();
                case 5:
                    changeTimerTime();

            }

        }else if(this.state==1){
            switch (event){
                case 1:
                case 5:
                    saveTimer(value);

                case 2:
                    changeField();
                case 3:
                    changeValue();  // -
                case 4:
                    changeValue();   // +
                default: break;
            }

        }else if(this.state==2){   // when running
            switch (event){
                case 1:
                    //mode change
                case 3:
                    pauseTimer();
                default: break;
            }
        }else if(this.state==3){    // when pause
            switch (event){
                case 1:
                    // mode change
                case 2:
                    resetTimer();
                case 3:
                    resumeTimer();
                default: break;
            }

        }
        makeUpdateViewArg(arg, 1, null);
        Message msg = new Message(11,"updateView",null);

        return msg;
    }

    public Message update() {
        // TODO implement here
        //state   0:default    1:edit 2: running 3: pause
        long sendtime = timer.getDeadlineData() - 1;
        timer.setDeadlineData(sendtime);

        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg, 1, null);
        Message msg = new Message(11,"updateView",null);
        return msg;
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

}