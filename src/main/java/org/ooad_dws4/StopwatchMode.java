package org.ooad_dws4;

import java.util.HashMap;

/**
 *
 */
public class StopwatchMode extends Mode {

    /**
     * Default constructor
     */
    /*public StopwatchMode() {
    }

     */

    /**
     *
     */
    private Stopwatch stopwatch;
    private HashMap <String, String> arg;


    public StopwatchMode(boolean isActivation) {
        this.isActivate = isActivation;
        this.stopwatch = new Stopwatch();
        this.modeName = "STOPWATCH";
    }

    @Override
    public Message getModeData() {
        return null;
    }

/*    @Override
    public Message toggleModeActivation() {
        return null;
    }*/

    /**
     *
     */
    public void runStopwatch() {
        // TODO implement here
        //change state running
        changeState(2);

        stopwatch.getStopwatchData();

    }

    /**
     *
     */
    public void pauseStopwatch() {
        // TODO implement here
        //change state pause
        changeState(3);
    }

    /**
     *
     */
    public void resetStopwatch() {
        // TODO implement here
        stopwatch.reset();
        changeState(0);
    }

    /**
     *
     */
    public void resumeStopwatch() {
        // TODO implement here
        //change state running
        changeState(2);
    }

    public void changeState(int state) {
        // TODO implement here
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

    public Message modeModify(int event) {
        // TODO implement here
        HashMap<String, String> arg = new HashMap<String, String>();

        if(this.state==0){
            switch (event){
                case 1:
                    //change mode
                case 3:
                    runStopwatch();
                default: break;


            }

        }else if(this.state==2){   // when running
            switch (event){
                case 1:
                    //mode change
                case 3:
                    pauseStopwatch();
                default: break;
            }
        }else if(this.state==3){    // when pause
            switch (event){
                case 1:
                    // mode change
                case 2:
                    resetStopwatch();
                case 3:
                    resumeStopwatch();
                default: break;
            }

        }
        return null;
    }

    public Message update() {
        // TODO implement here
        Message msg = new Message(11,"update",null);
        return msg;
    }

}