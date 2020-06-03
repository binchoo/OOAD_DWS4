package org.ooad_dws4;

import org.ooad_dws4.View.Buzzer.BuzzerSound;

public class BuzzerAdapter implements Buzzer {

    private BuzzerSound buzzerSound;

    public BuzzerAdapter() {
    }

    public void linkObject(BuzzerSound buzzerSound){
        this.buzzerSound = buzzerSound;
    }

    public void execute(String action) {
        if(action.equals("beep")) buzzerSound.beep();
        else if(action.equals("buzzOff")) buzzerSound.stop();
        else if(action.equals("buzzRinging")) buzzerSound.ring();
    }

}