package org.ooad_dws4.Model.Output;

import org.ooad_dws4.View.Buzzer.BuzzerSound;

public class BuzzerAdapter implements Buzzer {

    public BuzzerAdapter() {
    }

    public void linkObject(BuzzerSound buzzerSound){
        this.buzzerSound = buzzerSound;
    }

    private BuzzerSound buzzerSound;

    public void execute(String action) {
        if(action.equals("beep")) buzzerSound.beep();
        else if(action.equals("buzzOff")) buzzerSound.stop();
        else if(action.equals("buzzRinging")) buzzerSound.ring();
    }

}