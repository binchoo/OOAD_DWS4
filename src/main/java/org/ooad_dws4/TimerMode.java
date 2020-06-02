package org.ooad_dws4;

import java.util.HashMap;

/**
 * 
 */
public class TimerMode extends Mode{

    /**
     * Default constructor
     */
    /*public TimerMode() {
    }
    */
    /**
     * 
     */
    private Timer timer;

    private int field=0;
    private long value=0;
    private HashMap <String, String> arg;


    public TimerMode() {

        this.timer = new Timer();
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
    public void saveTimer() {
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