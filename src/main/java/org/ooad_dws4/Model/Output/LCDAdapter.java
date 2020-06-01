package org.ooad_dws4.Model.Output;

import org.ooad_dws4.View.LCD.LCDPanel;

import java.util.HashMap;

public class LCDAdapter implements LCD {

    public LCDAdapter() {
    }

    public void linkObject(LCDPanel lcdPanel){
        this.lcdPanel = lcdPanel;
    }

    private LCDPanel lcdPanel;

    @Override
    public boolean update(HashMap<String, String> arg) {
        // TODO implement here
        return false;
    }

}