package org.ooad_dws4;

import java.util.HashMap;

public class DDayMode extends Mode{
    private DDay[] ddays;
    private HashMap<String, String> arg;

    public DDayMode() {
        ddays = new DDay[4];
        for(int i=0;i<4;i++) {
            ddays[i] = new DDay();
        }
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
    private int currentDday=0;
    private int field=0;
    private long value=0;


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
     *   change stsate default
     */
    public void saveDday() {
        // TODO implement here

        changeState(0);

    }

    /**
     *    change dday index
     */
    public void changeDdayIndex() {
        // TODO implement here

        currentDday= (currentDday++)%4;


    }

    /**
     *    chage dday state edit*/
    public void enterDdayEdit() {
        // TODO implement here
        changeState(1);
    }

    /**
     *  toggle on / off
     */
    public void toggleDdayActivation() {
        // TODO implement here
        ddays[currentDday].changeState();


    }

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
                case 2:
                    toggleDdayActivation();
                case 3:
                    changeDdayIndex();   // -
                case 4:
                    changeDdayIndex();   // +
                case 5:
                    enterDdayEdit();


            }

        }else if(this.state==1){
            switch (event){
                case 1:
                case 5:
                    saveDday();

                case 2:
                    changeField();
                case 3:
                    changeValue();  // -
                case 4:
                    changeValue();   // +
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