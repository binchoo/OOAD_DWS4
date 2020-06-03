package org.ooad_dws4;

import org.ooad_dws4.View.LCD.LCDPanel;

import java.util.HashMap;
import java.util.Set;

public class LCDAdapter implements LCD {

    private LCDPanel lcdPanel;

    public LCDAdapter() {
    }

    public void linkObject(LCDPanel lcdPanel){
        this.lcdPanel = lcdPanel;
    }

    public void update(HashMap<String, String> arg) {
        Set<String> keys = arg.keySet();
        for (String key : keys) {
            if(key.equals("blink")){
                if(arg.get("blink")==null){
                    this.lcdPanel.stopBlink(0,26);
                }
                else{
                    this.lcdPanel.blink(Integer.parseInt(arg.get("blink")));
                }
            }
            else{
                this.lcdPanel.changeLCD(Integer.parseInt(key),arg.get(key));
            }
        }
    }

}