package org.ooad_dws4;

import org.ooad_dws4.View.LCD.LCDPanel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LCDAdapter implements LCD {

    private static LCDPanel lcdPanel;

    public void linkObject(final LCDPanel lcdPanel){
        this.lcdPanel = lcdPanel;
    }

    public void update(final Map<String, String> arg) {
        final Iterator<Map.Entry<String, String>> iterator = arg.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, String> entry = iterator.next();
            final String key = entry.getKey();
            final String value = entry.getValue();
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