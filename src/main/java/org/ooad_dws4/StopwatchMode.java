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


    public StopwatchMode() {
        this.stopwatch = new Stopwatch();
    }
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

    public Message modeModify(int event) {
        // TODO implement here
        if(event==5||state==1){
            arg= new HashMap<String, String>();

            arg.put("0","EDT");
            arg.put("1","");
            arg.put("2","");
            arg.put("3",Integer.toString(10));
            arg.put("4","\\\\\\Timer\\\\");

            Message msg = new Message(11,"updateView",arg);
        }

        else if(event==2||state==1){
            arg= new HashMap<String, String>();

            arg.put("0","EDT");
            arg.put("1","");
            arg.put("2","");
            arg.put("3",Integer.toString(10));
            arg.put("4","\\\\\\Timer\\\\");

            Message msg = new Message(11,"updateView",arg);

        }

        else if((event==3||event==4)||state==1){

            arg= new HashMap<String, String>();

            arg.put("0","EDT");
            arg.put("1","");
            arg.put("2","");
            arg.put("3",Integer.toString(10));
            arg.put("4","\\\\\\Timer\\\\");

            Message msg = new Message(11,"updateView",arg);
        }
        return null;
    }

    public Message update() {
        // TODO implement here
        Message msg = new Message(11,"update",null);
        return msg;
    }

}