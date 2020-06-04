package org.ooad_dws4;

import java.util.HashMap;

public class TimerMode extends Mode{


    private Timer timer;

    private int field;
    private long value;
    private int hour,minute,second;



    public TimerMode(boolean isActivation) {

        this.timer = new Timer();
        this.state=0;
        this.field=-1;
        this.isActivate =isActivation;
        this.modeName="TIMER";
    }

    @Override
    public Message getModeData() {
        HashMap<String, String> arg = new HashMap<String, String>();
        long timerTime = timer.getDeadlineData();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);
        return new Message(11,"updateView",arg);
    }



    public Message changeField() {
        // TODO implement here

        field++;
        field%=3;
        long timerTime = timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"EDT",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",Integer.toString(5-field));
            // add blink
        return new Message(11,"updateView",arg);
    }

    public Message changeValue(int updown) {
        // TODO implement here
        if((value==0 && updown==-1)||((value > 100*3600*1000 -1000)&&(updown==1))){

            return  new Message(11, "updateView",null);
        }

        if(field==0){
             value+=1000*updown;
        }
        else if(field==1){
            value+=60*1000*updown;
        }
        else{
            value+=3600*1000*updown;
        }

        timer.setDeadlineData(timer.getDeadlineData()+ value);
        long timerTime = timer.getDeadlineData();

        msecTohhmmss(timerTime);

        HashMap<String, String> arg = new HashMap<String, String>();

        makeUpdateViewArg(arg,"EDT",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);

    }


    public Message resetTimer() {

        timer.reset();

        long timerTime = timer.getDeadlineData();
        msecTohhmmss(timerTime);

        HashMap<String, String> arg = new HashMap<String, String>();
        makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);

    }


    private Message startTimer() {

        // change state running
        changeState(2);
        long timerTime =timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();

        arg.put("0","ringing");
        arg.put("1",Long.toString(timer.getDeadlineData()));
        arg.put("2","351");

        return new Message(22,"updateTimerEvent",arg);



    }

    public Message changeTimerTime() {
        //change state edit
        changeState(1);
        long timerTime =timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"EDT",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);

    }


    public Message saveTimer(long value) {
        // TODO implement here
        timer.setDeadlineData(value);
        changeState(0);

        long timerTime =timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);
    }

    public Message pauseTimer() {
        //change state pause
        changeState(3);

        long timerTime =timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"PUS",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);

    }

    public Message resumeTimer() {
        //change state running
        changeState(2);

        long timerTime =timer.getDeadlineData();
        HashMap<String, String> arg = new HashMap<String, String>();
        msecTohhmmss(timerTime);
        makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);

        return new Message(11,"updateView",arg);
    }

    public void changeState(int state) {
        this.state =state;
    }

    @Override
    public Message update(long systemTime) {
        if(state==2) {
            timer.setDeadlineData(timer.getDeadlineData() - 1000);
            long timerTime = timer.getDeadlineData();
        }
        return null;
    }

    @Override
    public Message update(long systemTime, boolean currentMode) {


        HashMap<String, String> arg = new HashMap<String, String>();
        if (state==2){
            timer.setDeadlineData(timer.getDeadlineData()-1000);
            long timerTime = timer.getDeadlineData();
            msecTohhmmss(timerTime);
            //makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);
            arg.put("3",Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second));
        }
        else{

            //makeUpdateViewArg(arg,"   ",null,Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second),"  TIMER   ",null);
            arg.put("3",Integer.toString(hour)+"|"+Integer.toString(minute)+Integer.toString(second));
        }

        return new Message(11,"updateView",arg);
    }
    public boolean receiveMessage(Message msg) {
        return false;
    }

    public Message modeModify(int event) {


        HashMap<String, String> arg = new HashMap<String, String>();

        if(this.state==0){
            switch (event){
                //case 1:
                    //change mode
                case 3:
                    return startTimer();
                case 5:
                    return changeTimerTime();

            }

        }else if(this.state==1){
            switch (event){
                case 1:
                case 5:
                    return saveTimer(value);

                case 2:
                    return changeField();
                case 3:
                    return changeValue(-1);  // -
                case 4:
                    return changeValue(1);   // +
                default: break;
            }

        }else if(this.state==2){   // when running
            switch (event){
                //case 1:
                    //mode change
                case 3:
                    return pauseTimer();
                default: break;
            }
        }else if(this.state==3){    // when pause
            switch (event){
                //case 1:
                    // mode change
                case 2:
                    return resetTimer();
                case 3:
                    return resumeTimer();
                default: break;
            }

        }else return null;

        //Message msg = new Message(11,"updateView",null);

        return null;
    }


    private void makeUpdateViewArg(HashMap<String, String> arg,String ar0,String ar1,String ar3, String ar4 , String blink){ //f

        arg.put("0", ar0);
        arg.put("1", null); // should be added in mode manager
        arg.put("3", ar3);
        arg.put("4", ar4);
        arg.put("blink", blink);
    }

    public void msecTohhmmss(long timerTime) {
        this.second = (int) (timerTime / 1000) % 60;
        this.minute = (int) ((timerTime) / (1000 * 60) % 60);
        this.hour = (int) ((timerTime) / (1000 * 60 * 60) % 24);

    }


}