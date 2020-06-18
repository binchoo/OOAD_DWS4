package org.ooad_dws4;

import org.ooad_dws4.View.LCD.LCDPanel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LCDAdapter implements LCD {

    private LCDPanel lcdPanel;

    public LCDAdapter() {
    }

    public void linkObject(LCDPanel lcdPanel){
        this.lcdPanel = lcdPanel;
    }

    public void update(HashMap<String, String> arg) {
        Iterator<Map.Entry<String, String>> iterator = arg.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.equals("blink")){
                if(value==null){
                    this.lcdPanel.stopBlink(10,26);
                }
                else{
                    this.lcdPanel.blink(Integer.parseInt(value));
                }
            }
            else{
                this.lcdPanel.changeLCD(Integer.parseInt(key),value);
            }
        }
    }

}