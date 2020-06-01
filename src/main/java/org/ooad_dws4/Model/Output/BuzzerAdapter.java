package org.ooad_dws4.Model.Output;

import org.ooad_dws4.View.Buzzer.BuzzerSound;

import java.util.HashMap;

public class BuzzerAdapter implements Buzzer {

    public BuzzerAdapter() {
    }

    public void linkObject(BuzzerSound buzzerSound){
        this.buzzerSound = buzzerSound;
    }

    private BuzzerSound buzzerSound;

    @Override
    public boolean execute(HashMap<String, String> arg) {
        // TODO implement here
        return false;
    }

}