package org.ooad_dws4;

import org.ooad_dws4.View.Buzzer.BuzzerSound;

public class BuzzerAdapter implements Buzzer {

    private static BuzzerSound buzzerSound;

    public void linkObject(final BuzzerSound buzzerSound){
        this.buzzerSound = buzzerSound;
    }

    public void execute(final String action) {
        if("beep".equals(action)) buzzerSound.beep();
        else if("buzzOff".equals(action)) buzzerSound.stop();
        else if("buzzRinging".equals(action)) buzzerSound.ring();
    }

}